import { Component } from '@angular/core';
import { Router , RouterOutlet ,RouterLink } from '@angular/router';
import { UserService,User } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-home',
  standalone: true,
  imports: [CommonModule ,RouterOutlet,RouterLink],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {
  userId: string | null = null;
  userName: string | null = null;
  totalUsers: number = 0; // Example data
  activeBookings: number = 0; // Example data
  availableVehicles: number = 0; // Example data

  constructor(private router: Router , private userService: UserService) { }

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId');
    this.userName = localStorage.getItem('userName');
     // Example: Fetch data from the service to display
     this.fetchStatistics();
  }

  fetchStatistics(): void {
    // Replace with actual data fetching logic
    this.totalUsers = 100;
    this.activeBookings = 30;
    this.availableVehicles = 50;
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
