import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';
import { User } from '../../services/user1.service';
import { Router } from '@angular/router';
import {  UserService1 } from '../../services/user1.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, NgChartsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{


  users: User[] = [];

  constructor(private router: Router, private userService: UserService1) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Erreur lors du chargement des utilisateurs', err)
    });
    console.log(this.users);
  }

  navigateToLevelForm(userId: number) {
    this.router.navigate(['/add-level', userId]); // redirige vers /add-level/3, etc.
  }


    lineChartData: ChartConfiguration['data'] = {
      datasets: [
        {
          data: [2000, 2100, 2200, 2400, 2450, 2500], // Example data for points
          label: 'Points',
          fill: true,
          backgroundColor: 'rgba(79, 195, 247, 0.1)',
          borderColor: '#2196f3', // Primary color for the line
          tension: 0.4,
          pointBackgroundColor: '#2196f3',
          pointBorderColor: '#ffffff',
          pointHoverBackgroundColor: '#ffffff',
          pointHoverBorderColor: '#2196f3',
          pointRadius: 4,
          pointHoverRadius: 6
        }
      ],
      labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5', 'Week 6']
    };
  
    // Chart options
    lineChartOptions: ChartConfiguration['options'] = {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          mode: 'index',
          intersect: false,
          backgroundColor: 'rgba(44, 62, 80, 0.9)',
          titleColor: '#ffffff',
          bodyColor: '#ffffff',
          borderColor: 'rgba(255, 255, 255, 0.2)',
          borderWidth: 1,
          padding: 12,
          cornerRadius: 8,
          titleFont: {
            size: 14,
            weight: 'bold'
          },
          callbacks: {
            label: (tooltipItem) => `${tooltipItem.raw} points`
          }
        }
      },
      scales: {
        x: {
          grid: {
            color: 'rgba(0, 0, 0, 0.05)',
          },
          ticks: {
            color: '#718096',
            font: {
              size: 12
            }
          }
        },
        y: {
          grid: {
            color: 'rgba(0, 0, 0, 0.05)',
          },
          ticks: {
            color: '#718096',
            font: {
              size: 12
            },
            callback: (value) => `${value} pts`
          },
          min: 1800
        }
      },
      elements: {
        line: {
          borderWidth: 3
        }
      }
    };
  
    // Plugins configuration
    lineChartPlugins = [];

}
