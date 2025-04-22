import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { GamificationService, UserProgress } from '../../services/gamification.service';

@Component({
  selector: 'app-leaderboard',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule],
  templateUrl: './leaderboard.component.html',
  styleUrl: './leaderboard.component.css',

})
export class LeaderboardComponent implements OnInit {
  displayedColumns: string[] = ['id', 'userId', 'points', 'Level'];
  users: UserProgress[] = [];

  constructor(private gamificationService: GamificationService) {}

  ngOnInit(): void {
    this.gamificationService.getLeaderboard().subscribe(data => {
      this.users = data;
      console.log('Leaderboard data:', this.users);
    });
  }
}
