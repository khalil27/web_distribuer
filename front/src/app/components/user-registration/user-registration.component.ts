import { Component } from '@angular/core';
import { NgForm ,FormsModule } from '@angular/forms';
import { UserService, User } from '../../services/user.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';


@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css'],
  standalone: true,
  imports: [CommonModule , FormsModule]
})
export class UserRegistrationComponent {
  user: User = {
    id: null,
    nom: '',
    prenom: '',
    password: '',
    login: '',
    type: 0,
    status: false,
    active: false
  };

  constructor(private userService: UserService, private router: Router) { }

  onSubmit() {
    this.userService.addUser(this.user).subscribe(
      response => {
        console.log('Utilisateur ajouté avec succès', response);
        this.router.navigate(['/']); // Redirect to home or any other page after successful registration
      },
      error => {
        console.error('Erreur lors de l\'ajout de l\'utilisateur', error);
      }
    );
  }
}
