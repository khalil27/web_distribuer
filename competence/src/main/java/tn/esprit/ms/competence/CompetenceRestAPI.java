package tn.esprit.ms.competence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
@CrossOrigin(origins = "http://localhost:4200")
public class CompetenceRestAPI {

    private String title = "Hello From MS COMPETENCE";

    @RequestMapping("/hello")
    public String sayHello() {
        return title;
    }

    @Autowired
    private ICompetenceService competenceService;

    @GetMapping
    public ResponseEntity<List<Competence>> getAll() {
        return new ResponseEntity<List<Competence>>(competenceService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Competence> createCompetence(@RequestBody Competence competence) {
        return new ResponseEntity<>(competenceService.addCompetence(competence), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Competence> updateCompetence(@PathVariable(value = "id") int id, @RequestBody Competence competence) {
        return new ResponseEntity<>(competenceService.updateCompetence(id, competence), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCompetence(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(competenceService.deleteCompetence(id), HttpStatus.OK);
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Competence>> getCompetencesByCategorie(@PathVariable String categorie) {
        return new ResponseEntity<>(competenceService.getCompetencesByCategorie(categorie), HttpStatus.OK);
    }

    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Competence>> getCompetencesByNiveau(@PathVariable Integer niveau) {
        return new ResponseEntity<>(competenceService.getCompetencesByNiveauMinimum(niveau), HttpStatus.OK);
    }
}