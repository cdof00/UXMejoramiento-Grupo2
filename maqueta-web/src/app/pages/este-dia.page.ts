import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore, DAYS, type DayIndex } from '../data/activity.store';

@Component({
  selector: 'app-este-dia',
  imports: [FormsModule, RouterLink, MatButtonModule, MatFormFieldModule, MatInputModule, MatSnackBarModule],
  template: `
    <div class="modal-card">
      @if (act) {
        <p>{{ dayLabel }}</p>
        <h1>¿Qué quieres cambiar?</h1>
        <button class="choice on" type="button" (click)="modo = 'dia'">
          <b>Solo este {{ dayLabel }}</b>
          <span>El resto de días se queda igual</span>
        </button>
        <a class="choice" [routerLink]="['/actividad', act.id]">
          <b>Toda la serie</b>
          <span>Abre editar actividad</span>
        </a>

        @if (modo === 'dia') {
          <h2>{{ act.name }}</h2>
          <mat-form-field appearance="outline" class="field">
            <mat-label>Nueva hora</mat-label>
            <input matInput type="time" [(ngModel)]="hora" />
          </mat-form-field>
          <button mat-stroked-button type="button" (click)="omitir()">Omitir este día (no suena)</button>
          <div class="actions">
            <a mat-button routerLink="/">Cancelar</a>
            <button mat-flat-button color="primary" type="button" (click)="guardar()">Guardar este día</button>
          </div>
        } @else {
          <div class="actions">
            <a mat-button routerLink="/">Cancelar</a>
          </div>
        }
      }
    </div>
  `,
})
export class EsteDiaPage implements OnInit {
  private readonly store = inject(ActivityStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly snack = inject(MatSnackBar);
  private readonly destroyRef = inject(DestroyRef);

  act = this.store.byId(Number(this.route.snapshot.paramMap.get('id')));
  day = Number(this.route.snapshot.paramMap.get('day')) as DayIndex;
  dayLabel = DAYS[this.day] ? DAYS[this.day].name + ' ' + DAYS[this.day].date : '';
  modo: 'pick' | 'dia' = 'pick';
  hora = '08:00';

  ngOnInit(): void {
    this.route.paramMap.pipe(takeUntilDestroyed(this.destroyRef)).subscribe((params) => {
      const id = Number(params.get('id'));
      const day = Number(params.get('day')) as DayIndex;
      this.act = this.store.byId(id);
      this.day = day;
      this.dayLabel = DAYS[this.day] ? `${DAYS[this.day].name} ${DAYS[this.day].date}` : '';
      this.modo = 'pick';
      if (this.act) {
        const o = this.store.occ(this.act, this.day);
        this.hora = o.time || '08:00';
      }
    });
  }

  omitir() {
    if (!this.act) return;
    this.store.excepcion(this.act.id, this.day, { skip: true });
    this.snack.open('Este ' + DAYS[this.day].name + ' no suena', 'Cerrar', { duration: 2500 });
    this.router.navigateByUrl('/');
  }

  guardar() {
    if (!this.act) return;
    this.store.excepcion(this.act.id, this.day, { time: this.hora });
    this.snack.open('Hora cambiada solo para este día', 'Cerrar', { duration: 2500 });
    this.router.navigateByUrl('/');
  }
}