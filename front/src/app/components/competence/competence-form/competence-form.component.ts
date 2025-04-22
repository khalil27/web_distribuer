import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CompetenceService } from '../../../services/competence.service';
import { Competence } from '../../../models/competence.model';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-competence-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    MatSnackBarModule,
    RouterModule
  ],
  templateUrl: './competence-form.component.html',
  styleUrls: ['./competence-form.component.scss']
})
export class CompetenceFormComponent implements OnInit {
  competenceForm: FormGroup;
  isEditMode: boolean = false;
  competenceId: number = 0; // Initialize with a default value
  categories: string[] = ['Technique', 'Soft Skills', 'Management', 'Langues', 'Autre'];

  // Inject dependencies directly
  private competenceService = inject(CompetenceService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);
  private fb = inject(FormBuilder);

  constructor() {
    this.competenceForm = this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      niveau: [1, [Validators.required, Validators.min(1), Validators.max(5)]],
      categorie: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.competenceId = +params['id'];
        this.loadCompetence(this.competenceId);
      }
    });
  }

  loadCompetence(id: number): void {
    this.competenceService.getCompetenceById(id).subscribe({
      next: (competence) => {
        this.competenceForm.patchValue({
          nom: competence.nom,
          description: competence.description,
          niveau: competence.niveau,
          categorie: competence.categorie
        });
      },
      error: (error) => {
        console.error('Erreur lors du chargement de la compétence:', error);
        this.snackBar.open('Erreur lors du chargement de la compétence', 'Fermer', {
          duration: 3000
        });
        this.router.navigate(['/competences']);
      }
    });
  }

  onSubmit(): void {
    if (this.competenceForm.invalid) {
      return;
    }

    const competence: Competence = this.competenceForm.value;

    if (this.isEditMode) {
      this.competenceService.updateCompetence(this.competenceId, competence).subscribe({
        next: (response) => {
          console.log('Compétence mise à jour avec succès:', response);
          this.snackBar.open('Compétence mise à jour avec succès', 'Fermer', {
            duration: 3000
          });
          this.router.navigate(['/competences']);
        },
        error: (error) => {
          console.error('Erreur lors de la mise à jour de la compétence:', error);
          this.snackBar.open('Erreur lors de la mise à jour de la compétence', 'Fermer', {
            duration: 3000
          });
        }
      });
    } else {
      this.competenceService.createCompetence(competence).subscribe({
        next: (response) => {
          console.log('Compétence créée avec succès:', response);
          this.snackBar.open('Compétence créée avec succès', 'Fermer', {
            duration: 3000
          });
          this.router.navigate(['/competences']);
        },
        error: (error) => {
          console.error('Erreur lors de la création de la compétence:', error);
          this.snackBar.open('Erreur lors de la création de la compétence', 'Fermer', {
            duration: 3000
          });
        }
      });
    }
  }

  resetForm(): void {
    this.competenceForm.reset();
    if (this.isEditMode) {
      this.loadCompetence(this.competenceId);
    }
  }
}
