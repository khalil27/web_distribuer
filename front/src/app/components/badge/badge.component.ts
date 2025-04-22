import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { GamificationService } from '../../services/gamification.service';

export interface Badge {
  id: number;
  userId: number;
  title: string;
  description: string;
  dateAwarded: string;
  icon?: string;
  type?: string;
  earned?: boolean;
  earnedDate?: string;
}

@Component({
  selector: 'app-badge',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, MatButtonModule],
  templateUrl: './badge.component.html',
  styleUrl: './badge.component.css'
})

export class BadgeComponent implements OnInit {
  badges: any[] = [];

  iconMap: { [title: string]: { icon: string, type: string } } = {
    'Niveau 1': { icon: 'swap_horiz', type: 'bronze' },
    'Niveau 4': { icon: 'school', type: 'silver' },
    'Niveau 7': { icon: 'emoji_events', type: 'gold' },
    // ajoute d'autres si tu veux étendre
  };

  constructor(private gamificationService: GamificationService) {}

  ngOnInit(): void {
    this.gamificationService.getBadges().subscribe(apiBadges => {
      this.badges = apiBadges.map(badge => {
        const mapping = this.iconMap[badge.title] || { icon: 'star', type: 'bronze' };
        return {
          ...badge,
          icon: mapping.icon,
          type: mapping.type,
          earned: true,
          earnedDate: new Date(badge.dateAwarded).toDateString()
        };
      });
    });
  }

  getBadgeTypeLabel(type: string): string {
    return type.charAt(0).toUpperCase() + type.slice(1);
  }

  getEarnedPercentage(): number {
    const earnedBadges = this.badges.filter(badge => badge.earned).length;
    return Math.round((earnedBadges / this.badges.length) * 100);
  }
}
