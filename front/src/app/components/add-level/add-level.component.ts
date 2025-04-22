import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute } from '@angular/router';
import {  UserService1 } from '../../services/user1.service';

@Component({
  selector: 'app-add-level',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './add-level.component.html',
  styleUrls: ['./add-level.component.css']
})
export class AddLevelComponent {
  userId!: number;
  level = {
    title: '',
    description: ''
  };

  constructor(private route: ActivatedRoute, private userService: UserService1) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.userId = id ? +id : NaN;
      console.log('ID récupéré depuis l’URL :', this.userId);
    });
  }

onSubmit() {
  if (this.level.title && this.level.description) {
    this.userService.assignBadge(this.userId, this.level.title, this.level.description)
      .subscribe(
        response => {
          console.log('Badge attribué avec succès!', response);
        },
        error => {
          console.error('Erreur lors de l\'affectation du badge', error);
        }
      );
  } else {
    console.error('Veuillez remplir tous les champs');
  }
}
}
