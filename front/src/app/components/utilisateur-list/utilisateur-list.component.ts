import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService, User } from '../../services/user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';


@Component({
  selector: 'app-utilisateur-list',
  templateUrl: './utilisateur-list.component.html',
  styleUrls: ['./utilisateur-list.component.css'],
  standalone: true, 
  imports: [CommonModule ,FormsModule,HeaderComponent] // Ensure CommonModule is imported here
})
export class UtilisateurListComponent implements OnInit {
  users: User[] = [];
  isEditModalOpen: boolean = false;
  selectedUser: User | null = null;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(data => {
      this.users = data;
      console.log('Utilisateurs récupérés avec succès:', this.users); // Journalisation des données pour le débogage
    },
    error => {
      console.error('Erreur lors de la récupération des utilisateurs:', error); // Journalisation des erreurs pour le débogage
    }
    );
  }
  goHome(): void {
    this.router.navigate(['/home']);
  }

  openEditModal(user: User): void {
    console.log('Ouverture du modal pour:', user);
    this.selectedUser = { ...user };
    this.isEditModalOpen = true;
  }

  closeEditModal(): void {
    this.isEditModalOpen = false;
    this.selectedUser = null;
  }

  saveChanges(): void {
    if (this.selectedUser) {
      this.userService.updateUser(this.selectedUser).subscribe(
        updatedUser => {
          const index = this.users.findIndex(u => u.id === updatedUser.id);
          if (index !== -1) {
            this.users[index] = updatedUser;
          }
          this.closeEditModal();
        },
        error => {
          console.error('Erreur lors de la mise à jour de l\'utilisateur:', error);
        }
      );
    }
  }

  deleteUser(id: number): void {
    if(id!=null) {
    if (confirm('Êtes-vous sûr de vouloir désactiver cet utilisateur ?')) {
      this.userService.deleteUser(id).subscribe(() => {
        // Mettre à jour l'état de l'utilisateur localement
        this.users = this.users.map(user => 
          user.id === id ? { ...user, active: false } : user
        );
        console.log('Utilisateur désactivé avec succès');
      },
      error => {
        console.error('Erreur lors de la désactivation de l\'utilisateur:', error);
      });
    }
  }
  }
}
