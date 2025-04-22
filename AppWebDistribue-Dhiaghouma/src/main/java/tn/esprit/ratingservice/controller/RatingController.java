package tn.esprit.ratingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ratingservice.dto.RatingDTO;
import tn.esprit.ratingservice.model.Rating;
import tn.esprit.ratingservice.model.Skill;
import tn.esprit.ratingservice.service.RatingService;
import tn.esprit.ratingservice.repository.SkillRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    private final SkillRepository skillRepository;

    /**
     * Create a new rating
     * Only authenticated users with the 'user' role can create ratings
     */
    @PostMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<Rating> createRating(@Valid @RequestBody RatingDTO ratingRequest) {
        // Get current user ID from Keycloak token
        String userId = getCurrentKeycloakUserId();

        // Set the user ID from the authenticated user if not provided
        if (ratingRequest.getUserId() == null) {
            try {
                ratingRequest.setUserId(Long.parseLong(userId));
            } catch (NumberFormatException e) {
                // Handle the case where Keycloak user ID is not a number
                // This happens if your Keycloak IDs are UUIDs but your app uses Long
                // You might need a user mapping service in a real application
            }
        }

        Rating rating = ratingService.rateSkill(ratingRequest);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    /**
     * Get ratings by user ID
     * A user can see their own ratings, admins can see anyone's ratings
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('user') and #userId == authentication.principal.claims['sub'] or hasRole('admin')")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    /**
     * Get ratings by skill ID
     * Available to authenticated users
     */
    @GetMapping("/skill/{skillId}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Rating>> getRatingsBySkill(@PathVariable Long skillId) {
        Optional<Skill> skill = skillRepository.findById(skillId);
        if (skill.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Rating> ratings = ratingService.getRatingsBySkill(skill.get());
        return ResponseEntity.ok(ratings);
    }

    /**
     * Get average rating for a skill
     * Public endpoint - no authentication required
     */
    @GetMapping("/skill/{skillId}/average")
    public ResponseEntity<Double> getAverageRatingForSkill(@PathVariable Long skillId) {
        Double averageRating = ratingService.getAverageRatingForSkill(skillId);
        return ResponseEntity.ok(averageRating != null ? averageRating : 0.0);
    }

    /**
     * Get total ratings count for a skill
     * Public endpoint - no authentication required
     */
    @GetMapping("/skill/{skillId}/count")
    public ResponseEntity<Integer> getTotalRatingsForSkill(@PathVariable Long skillId) {
        Integer count = ratingService.getTotalRatingsForSkill(skillId);
        return ResponseEntity.ok(count != null ? count : 0);
    }

    /**
     * Helper method to extract the user ID from Keycloak token
     */
    private String getCurrentKeycloakUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            Map<String, Object> claims = jwtToken.getToken().getClaims();
            return (String) claims.get("sub");
        }
        return null;
    }

    /**
     * Helper method to check if current user is admin
     */
    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_admin"));
    }

    /**
     * Admin endpoint to delete a rating
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        boolean deleted = ratingService.deleteRating(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}