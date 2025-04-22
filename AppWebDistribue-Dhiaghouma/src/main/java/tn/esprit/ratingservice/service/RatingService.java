package tn.esprit.ratingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ratingservice.dto.RatingDTO;
import tn.esprit.ratingservice.model.Rating;
import tn.esprit.ratingservice.model.Skill;
import tn.esprit.ratingservice.model.User;
import tn.esprit.ratingservice.repository.RatingRepository;
import tn.esprit.ratingservice.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final SkillRepository skillRepository;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final UserService userService;

    @Transactional
    public Rating rateSkill(RatingDTO ratingRequest) {
        System.out.println("Trying to find skill with ID: " + ratingRequest.getSkillId());
        Skill skill = skillRepository.findById(ratingRequest.getSkillId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found with ID: " + ratingRequest.getSkillId()));
        System.out.println("Found skill: " + skill.getName());

        Optional<Rating> existingRating = ratingRepository.findByUserIdAndSkill(
                ratingRequest.getUserId(), skill);

        Rating rating;
        boolean isNewRating = false;

        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setScore(ratingRequest.getScore());
            rating.setComment(ratingRequest.getComment());
        } else {
            rating = new Rating();
            rating.setUserId(ratingRequest.getUserId());
            rating.setSkill(skill);
            rating.setScore(ratingRequest.getScore());
            rating.setComment(ratingRequest.getComment());
            isNewRating = true;
        }

        try {
            Rating savedRating = ratingRepository.save(rating);

            if (isNewRating) {
                // Get the user object to retrieve the email
                User user = userService.getUserById(ratingRequest.getUserId());

                // Send an email notification about the rating
                emailService.sendRatingEmail(
                        user.getEmail(),                    // The recipient's email
                        skill.getName(),                    // The name of the skill being rated
                        ratingRequest.getScore(),           // The rating score (int)
                        ratingRequest.getComment()          // The comment left by the user
                );

                // Create the rating notification
                notificationService.createRatingNotification(
                        ratingRequest.getSkillOwnerId(),
                        ratingRequest.getUserId(),
                        skill.getId(),
                        ratingRequest.getScore()
                );
            }

            return savedRating;
        } catch (Exception e) {
            // Log the exception details
            throw new RuntimeException("Failed to save rating: " + e.getMessage(), e);
        }
    }


    public List<Rating> getRatingsByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> getRatingsBySkill(Skill skill) {
        return ratingRepository.findBySkill(skill);
    }

    public Double getAverageRatingForSkill(Long skillId) {
        return ratingRepository.getAverageRatingForSkill(skillId);
    }

    public Integer getTotalRatingsForSkill(Long skillId) {
        return ratingRepository.getTotalRatingsForSkill(skillId);
    }
    /**
     * Delete a rating by ID
     * Returns true if deleted successfully, false if rating not found
     */
    public boolean deleteRating(Long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}