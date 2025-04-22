import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent], 
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  userId: string | null = null;
  userName: string | null = null;
  userType: number | null = null;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.userName = localStorage.getItem('userName');
    this.userType = Number(localStorage.getItem('userType')); 
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

  navigateTo(path: string): void {
    this.router.navigate([path]);
  }
}
