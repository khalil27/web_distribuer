import { Component, OnInit } from '@angular/core';
import { Router ,RouterLink } from '@angular/router';
import { UserService,User } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css'],
  standalone: true,
  imports: [CommonModule,RouterLink]
})
export class ClientHomeComponent implements OnInit {
  userId: string | null = null;
  userName: string | null = null;

  constructor(private router: Router , private userService: UserService) { }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.userName = localStorage.getItem('userName');
  }

  logout(): void {
    const userId = this.userId;
    if (userId) {
      this.userService.logout(Number(userId)).subscribe(() => {
        localStorage.removeItem('userId');
        localStorage.removeItem('userName');
        this.router.navigate(['/']);
      });
    } else {
      localStorage.removeItem('userId');
      localStorage.removeItem('userName');
      this.router.navigate(['/']);
    }
  }
}
