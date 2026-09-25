import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore } from '../data/activity.store';

@Component({
  selector: 'app-aviso',
  imports: [RouterLink, MatButtonModule, MatIconModule, MatSnackBarModule],
  template: `
    <div class="notify-card">
      <mat-icon>alarm</mat-icon>
      <small>Semana</small>
      <h1>{{ act?.name || 'Actividad' }}</h1>
      <p>{{ hora }} · ahora</p>
      <p>{{ act?.desc }}</p>
      <div class="actions">
        <button mat-button type="button" (click)="cerrar()">Cerrar</button>
        <button mat-button type="button" (click)="posponer()">En 5 min</button>
        <a mat-flat-button color="primary" routerLink="/">Abrir</a>
      </div>
    </div>
  `,
})
export class AvisoPage {
  store = inject(ActivityStore);
  route = inject(ActivatedRoute);
  router = inject(Router);
  snack = inject(MatSnackBar);
  act = this.store.byId(Number(this.route.snapshot.paramMap.get('id')));
  hora = this.act ? Object.values(this.act.hours)[0] : '08:00';

  cerrar() {
    this.snack.open('Aviso cerrado', 'Ok', { duration: 1800 });
    this.router.navigateByUrl('/');
  }

  posponer() {
    this.snack.open('En 5 min vuelve a aparecer', 'Ok', { duration: 1800 });
    this.router.navigateByUrl('/');
  }
}