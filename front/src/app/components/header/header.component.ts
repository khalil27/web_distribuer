import { Component ,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService, User } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  userId: string | null = null;
  userName: string | null = null;
  userType: number | null = null;

  constructor(private router: Router , private userService: UserService) { }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.userName = localStorage.getItem('userName');
    this.userType = Number(localStorage.getItem('userType')); // Assuming you store the user type in localStorage

  }

  logout(): void {
    const userId = this.userId;
    if (userId) {
      this.userService.logout(Number(userId)).subscribe(() => {
        localStorage.removeItem('userId');
        localStorage.removeItem('userName');
        localStorage.removeItem('userType');
        this.router.navigate(['/']);
      });
    } else {
      localStorage.removeItem('userId');
      localStorage.removeItem('userName');
      localStorage.removeItem('userType');
      this.router.navigate(['/']);
    }
  }
}
