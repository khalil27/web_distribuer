package tn.esprit.ratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.ratingservice.model.Rating;
import tn.esprit.ratingservice.model.Skill;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByUserId(Long userId);

    // Updated to work with the Skill object, not Long skillId
    List<Rating> findBySkill(Skill skill);

    // Updated to work with the Skill object
    Optional<Rating> findByUserIdAndSkill(Long userId, Skill skill);

    // Updated JPQL to use r.skill.id
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.skill.id = :skillId")
    Double getAverageRatingForSkill(@Param("skillId") Long skillId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.skill.id = :skillId")
    Integer getTotalRatingsForSkill(@Param("skillId") Long skillId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.skill.id = :skillId AND r.score = :score")
    Integer countBySkillIdAndScore(@Param("skillId") Long skillId, @Param("score") Integer score);
}
