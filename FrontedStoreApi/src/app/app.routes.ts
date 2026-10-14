import { Routes } from '@angular/router';
import { ProdcutosComponent } from './productos/prodcutos.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './Services/aut.guard';

export const routes: Routes = [
{ path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'productos', component: ProdcutosComponent, 
    canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' }
];
