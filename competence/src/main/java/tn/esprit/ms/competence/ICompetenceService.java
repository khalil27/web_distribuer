package tn.esprit.ms.competence;

import java.util.List;

public interface ICompetenceService {
    public List<Competence> getAll();

    public Competence addCompetence(Competence c);

    public Competence updateCompetence(int id, Competence newCompetence);

    public String deleteCompetence(int id);

    public List<Competence> getCompetencesByCategorie(String categorie);

    public List<Competence> getCompetencesByNiveauMinimum(Integer niveau);
}