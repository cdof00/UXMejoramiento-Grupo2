import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore, DAYS, type Activity, type DayIndex } from '../data/activity.store';

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
export class AvisoPage implements OnInit {
  private readonly store = inject(ActivityStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly snack = inject(MatSnackBar);
  private readonly destroyRef = inject(DestroyRef);

  act: Activity | undefined;
  hora = '08:00';

  ngOnInit(): void {
    this.route.paramMap.pipe(takeUntilDestroyed(this.destroyRef)).subscribe((params) => {
      const id = Number(params.get('id'));
      this.act = this.store.byId(id);
      const today: DayIndex = 0;
      const o = this.act ? this.store.occ(this.act, today) : null;
      this.hora = o?.time || Object.values(this.act?.hours ?? {})[0] || '08:00';
    });
  }

  cerrar() {
    this.snack.open('Aviso cerrado', 'Ok', { duration: 1800 });
    this.router.navigateByUrl('/');
  }

  posponer() {
    this.snack.open('En 5 min vuelve a aparecer', 'Ok', { duration: 1800 });
    this.router.navigateByUrl('/');
  }
}