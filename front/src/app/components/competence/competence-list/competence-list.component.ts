import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { Competence } from '../../../models/competence.model';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-competence-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSnackBarModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './competence-list.component.html',
  styleUrls: ['./competence-list.component.scss']
})
export class CompetenceListComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'nom', 'description', 'niveau', 'categorie', 'actions'];
  dataSource: MatTableDataSource<Competence>;
  competences: Competence[] = [];
  searchCategorie: string = '';
  searchNiveau: number | null = null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private competenceService: CompetenceService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.dataSource = new MatTableDataSource(this.competences);
  }

  ngOnInit(): void {
    this.loadCompetences();
  }

  ngAfterViewInit(): void {
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  loadCompetences(): void {
    this.competenceService.getAllCompetences().subscribe({
      next: (data) => {
        this.competences = data;
        this.dataSource = new MatTableDataSource(this.competences);
  
        // Assure que le paginator et le sort sont bien reliés après un cycle de changement
        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        });
  
      },
      error: (error) => {
        console.error('Erreur lors du chargement des compétences:', error);
        this.snackBar.open('Erreur lors du chargement des compétences', 'Fermer', {
          duration: 3000
        });
      }
    });
  }
  

  filterByCategorie(): void {
    if (this.searchCategorie) {
      this.competenceService.getCompetencesByCategorie(this.searchCategorie).subscribe({
        next: (data) => {
          this.competences = data;
          this.dataSource = new MatTableDataSource(this.competences);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error: (error) => {
          console.error('Erreur lors du filtrage par catégorie:', error);
          this.snackBar.open('Erreur lors du filtrage par catégorie', 'Fermer', {
            duration: 3000
          });
        }
      });
    } else {
      this.loadCompetences();
    }
  }

  filterByNiveau(): void {
    if (this.searchNiveau) {
      this.competenceService.getCompetencesByNiveau(this.searchNiveau).subscribe({
        next: (data) => {
          this.competences = data;
          this.dataSource = new MatTableDataSource(this.competences);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error: (error) => {
          console.error('Erreur lors du filtrage par niveau:', error);
          this.snackBar.open('Erreur lors du filtrage par niveau', 'Fermer', {
            duration: 3000
          });
        }
      });
    } else {
      this.loadCompetences();
    }
  }

  resetFilters(): void {
    this.searchCategorie = '';
    this.searchNiveau = null;
    this.loadCompetences();
  }

  editCompetence(id: number): void {
    this.router.navigate(['/competences/edit', id]);
  }

  viewCompetence(id: number): void {
    this.router.navigate(['/competences', id]);
  }

  deleteCompetence(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette compétence ?')) {
      this.competenceService.deleteCompetence(id).subscribe({
        next: (response) => {
          console.log('Compétence supprimée avec succès:', response);
          this.snackBar.open('Compétence supprimée avec succès', 'Fermer', {
            duration: 3000
          });
          this.loadCompetences();
        },
        error: (error) => {
          console.error('Erreur lors de la suppression de la compétence:', error);
          this.snackBar.open('Erreur lors de la suppression de la compétence', 'Fermer', {
            duration: 3000
          });
        }
      });
    }
  }
}
