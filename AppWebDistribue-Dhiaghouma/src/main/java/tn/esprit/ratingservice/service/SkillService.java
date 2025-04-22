package tn.esprit.ratingservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ratingservice.model.Skill;
import tn.esprit.ratingservice.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Optional<Skill> updateSkill(Long id, Skill updatedSkill) {
        Optional<Skill> existingSkill = skillRepository.findById(id);
        if (existingSkill.isPresent()) {
            updatedSkill.setId(id);
            return Optional.of(skillRepository.save(updatedSkill));
        }
        return Optional.empty();
    }

    public void deleteSkill(Long id) {
         skillRepository.deleteById(id);
    }
}
