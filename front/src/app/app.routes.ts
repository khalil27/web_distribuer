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
    { path: '**', redirectTo: '',pathMatch:'full' }
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}

