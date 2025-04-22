import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { UserService1 } from '../../services/user1.service';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  activities: any[] = [];

  constructor(private userService: UserService1) {}

  ngOnInit() {
    // Récupérer toutes les activités pour tous les utilisateurs
    this.userService.getAllActivities().subscribe(data => {
      this.activities = data;
    });
  }
}
