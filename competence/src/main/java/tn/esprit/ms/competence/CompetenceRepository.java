package tn.esprit.ms.competence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
    @Query("select c from Competence c where c.categorie like :category")
    public Page<Competence> competenceByCategorie(@Param("category") String cat, Pageable pageable);

    List<Competence> findByCategorie(String categorie);
    List<Competence> findByNiveauGreaterThanEqual(Integer niveau);
}