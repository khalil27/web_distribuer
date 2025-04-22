package tn.esprit.ms.competence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService implements ICompetenceService {
    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence addCompetence(Competence c) {
        return competenceRepository.save(c);
    }

    @Override
    public Competence updateCompetence(int id, Competence newCompetence) {
        if (competenceRepository.findById(id).isPresent()) {
            Competence existingCompetence = competenceRepository.findById(id).get();
            existingCompetence.setNom(newCompetence.getNom());
            existingCompetence.setDescription(newCompetence.getDescription());
            existingCompetence.setNiveau(newCompetence.getNiveau());
            existingCompetence.setCategorie(newCompetence.getCategorie());

            return competenceRepository.save(existingCompetence);
        } else
            return null;
    }

    @Override
    public String deleteCompetence(int id) {
        if (competenceRepository.findById(id).isPresent()) {
            competenceRepository.deleteById(id);
            return "compétence supprimée";
        } else
            return "compétence non supprimée";
    }

    @Override
    public List<Competence> getCompetencesByCategorie(String categorie) {
        return competenceRepository.findByCategorie(categorie);
    }

    @Override
    public List<Competence> getCompetencesByNiveauMinimum(Integer niveau) {
        return competenceRepository.findByNiveauGreaterThanEqual(niveau);
    }
}