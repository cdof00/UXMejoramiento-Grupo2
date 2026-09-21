import { Routes } from '@angular/router';
import { SemanaPage } from './pages/semana.page';

export const routes: Routes = [
  { path: '', component: SemanaPage, title: 'Semana' },
  { path: '**', redirectTo: '' },
];
