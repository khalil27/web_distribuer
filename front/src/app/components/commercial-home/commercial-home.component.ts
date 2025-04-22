import { Component } from '@angular/core';
import { Router,RouterLink } from '@angular/router';
import { UserService,User } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';


@Component({
  selector: 'app-commercial-home',
  standalone: true,
  imports: [RouterLink,CommonModule,HeaderComponent],
  templateUrl: './commercial-home.component.html',
  styleUrl: './commercial-home.component.css'
})
export class CommercialHomeComponent {
  userId: string | null = null;
  userName: string | null = null;

  constructor(private router: Router , private userService: UserService) { }

  ngOnInit(): void {
    /*this.userId = localStorage.getItem('userId');
    this.userName = localStorage.getItem('userName');*/
  }

  /*logout(): void {
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
  }*/

}
