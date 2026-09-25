import { Routes } from '@angular/router';
import { SemanaPage } from './pages/semana.page';
import { ActividadPage } from './pages/actividad.page';
import { EsteDiaPage } from './pages/este-dia.page';
import { AvisoPage } from './pages/aviso.page';
import { ConfirmarPage } from './pages/confirmar.page';
import { PausaPage } from './pages/pausa.page';

export const routes: Routes = [
  {
    path: '',
    component: SemanaPage,
    title: 'Semana',
    children: [
      { path: 'actividad', component: ActividadPage, title: 'Nueva actividad' },
      { path: 'actividad/:id', component: ActividadPage, title: 'Editar actividad' },
      { path: 'este-dia/:id/:day', component: EsteDiaPage, title: 'Editar día específico' },
      { path: 'aviso/:id', component: AvisoPage, title: 'Aviso' },
      { path: 'confirmar/:id', component: ConfirmarPage, title: 'Confirmar' },
      { path: 'pausa/:id', component: PausaPage, title: 'Pausa' },
    ],
  },
  { path: '**', redirectTo: '' },
];