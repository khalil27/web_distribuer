/*import { Routes } from '@angular/router';*/
import { HomeComponent } from './components/home/home.component';
import { UtilisateurListComponent } from './components/utilisateur-list/utilisateur-list.component';
import { Router, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { UserRegistrationComponent } from './components/user-registration/user-registration.component';
import { ClientHomeComponent } from './components/client-home/client-home.component';
import { LoginComponent } from './components/login/login.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { CommercialHomeComponent } from './components/commercial-home/commercial-home.component';
import { CompetenceListComponent } from './components/competence/competence-list/competence-list.component';
import { CompetenceFormComponent } from './components/competence/competence-form/competence-form.component';
import { CompetenceDetailComponent } from './components/competence/competence-detail/competence-detail.component';
import { SkillsComponent } from './components/skills-component/skills-component.component';
import { MyRatingsComponent } from './components/my-ratings-component/my-ratings-component.component';




export const routes: Routes = [
    { path: '', component: HomeComponent,},
    { path: 'utilisateur-list', component: UtilisateurListComponent},
    { path: 'user-registration', component: UserRegistrationComponent},
    { path: 'login', component: LoginComponent},
    { path: 'client-home', component: ClientHomeComponent },
    { path: 'admin-home', component: AdminHomeComponent },
    { path: 'commercial-home', component: CommercialHomeComponent },
    { path: 'competences', component: CompetenceListComponent },
    { path: 'competences/new', component: CompetenceFormComponent },
    { path: 'competences/edit/:id', component: CompetenceFormComponent },
    { path: 'competences/:id', component: CompetenceDetailComponent },
    { path: 'skill_comp', component: SkillsComponent },
  { path: 'my-ratings', component: MyRatingsComponent },
  {path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard.component')
      .then(m => m.DashboardComponent)
  },
  {
    path: 'badges',
    loadComponent: () => import('./components/badge/badge.component')
      .then(m => m.BadgeComponent)
  },
  {
    path: 'leaderboard',
    loadComponent: () => import('./components/leaderboard/leaderboard.component')
      .then(m => m.LeaderboardComponent)
  },
  {
    path: 'history',
    loadComponent: () => import('./components/history/history.component')
      .then(m => m.HistoryComponent)
  },
  
  {
    path: 'add-level/:id',
    loadComponent: () => import('./components/add-level/add-level.component').then(m => m.AddLevelComponent)
  },
    { path: '**', redirectTo: '',pathMatch:'full' }
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}

