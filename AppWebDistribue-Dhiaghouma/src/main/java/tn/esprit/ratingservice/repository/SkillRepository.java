package tn.esprit.ratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.ratingservice.model.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByCategory(String category);

    List<Skill> findByProficiencyLevelIgnoreCase(String proficiencyLevel);

    List<Skill> findByExperienceYearsGreaterThanEqual(int experienceYears);
}
