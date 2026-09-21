import { Routes } from '@angular/router';
import { SemanaPage } from './pages/semana.page';
import { ActividadPage } from './pages/actividad.page';

export const routes: Routes = [
  {
    path: '',
    component: SemanaPage,
    title: 'Semana',
    children: [
      { path: 'actividad', component: ActividadPage, title: 'Nueva actividad' },
      { path: 'actividad/:id', component: ActividadPage, title: 'Editar actividad' },
    ],
  },
  { path: '**', redirectTo: '' },
];