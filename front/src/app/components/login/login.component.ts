import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService, User } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class LoginComponent {
  loginData = { login: '', password: '' };
  errorMessage = '';

  constructor(private userService: UserService, private router: Router) { }

  onSubmit(): void {
    this.userService.login(this.loginData.login, this.loginData.password).subscribe(response => {
      if (response && response.id != null) {
        localStorage.setItem('userId', response.id.toString());
        localStorage.setItem('userName', response.nom); // Assurez-vous de stocker le nom de l'utilisateur
        localStorage.setItem('userType', response.type.toString()); // Save the user type
        if (response.type === 0) {
          this.router.navigate(['/admin-home']);
        } else if (response.type === 1) {
          this.router.navigate(['/commercial-home']);
        } else if (response.type === 2) {
          this.router.navigate(['/client-home']);
        }
      } else {
        this.errorMessage = 'Login ou mot de passe incorrect.';
      }
    }, error => {
      this.errorMessage = 'Erreur de connexion. Veuillez réessayer.';
    });
  }
  

  goHome() {
    this.router.navigate(['/']);
  }

  navigateToAdminHome(): void {
    this.router.navigate(['/client-home']);
  }
}
