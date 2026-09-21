import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore, DAYS, type Activity, type DayIndex } from '../data/activity.store';

@Component({
  selector: 'app-semana',
  imports: [MatButtonModule, MatIconModule, MatSnackBarModule],
  template: `
    <div class="shell">
      <header class="top">
        <div class="brand">
          <h1>Semana</h1>
          <p>Una actividad, distintos horarios</p>
        </div>
        <div class="week-nav">
          <button mat-icon-button (click)="aviso('En el prototipo solo está esta semana')">
            <mat-icon>chevron_left</mat-icon>
          </button>
          <div>
            <strong>7 — 13 septiembre</strong><br />
            Hoy, lunes 7 · 09:18 en el PC
          </div>
          <button mat-icon-button (click)="aviso('En el prototipo solo está esta semana')">
            <mat-icon>chevron_right</mat-icon>
          </button>
        </div>
        <div class="top-actions">
          <button mat-flat-button color="primary" type="button" (click)="aviso('Siguiente: el formulario de crear')">
            <mat-icon>add</mat-icon>
            Crear
          </button>
        </div>
      </header>

      <section class="now">
        <mat-icon>desktop_windows</mat-icon>
        <div>
          <strong>Break de pantalla · faltan 12 min para la pausa</strong>
          <small>Aviso a los 2 min · overlay a los 0 · 30 de trabajo + 5 de pausa</small>
        </div>
      </section>

      <div class="layout">
        <aside class="rail">
          <h2>Actividades</h2>
          <p class="hint">Clic en el nombre = editar. Clic en el bloque = este día.</p>
          @for (act of store.lista(); track act.id) {
            <button class="li" type="button" (click)="aviso('Siguiente: editar ' + act.name)">
              <span class="dot" [style.background]="act.color"></span>
              <div>
                <b>{{ act.name }}</b>
                <small>{{ etiqueta(act) }}</small>
              </div>
            </button>
          }
        </aside>
        <div class="grid">
          @for (d of days; track d.i) {
            <div class="col" [class.today]="d.i === 0">
              <div class="col-head">
                <strong>{{ d.short }} {{ d.date }}</strong>
                @if (d.i === 0) { <em>HOY</em> }
              </div>
              @for (m of reuniones(d.i); track m.title) {
                <button class="meet" type="button" (click)="aviso('Pausa omitida: ' + m.title)">
                  Calendar · {{ m.start }} {{ m.title }}
                </button>
              }
              @for (act of store.lista(); track act.id) {
                @if (store.occ(act, d.i); as o) {
                  @if (o.time || o.kind === 'skipped') {
                    <button class="block" [style.background]="act.color" type="button"
                      (click)="aviso(act.name + ' · ' + o.time)">
                      <small>{{ o.time }}</small>
                      <b>{{ act.name }}</b>
                    </button>
                  }
                }
              }
            </div>
          }
        </div>
      </div>
    </div>
  `,
})
export class SemanaPage {
  store = inject(ActivityStore);
  snack = inject(MatSnackBar);
  days = DAYS;

  reuniones(day: DayIndex) {
    return this.store.meetings.filter((m) => m.day === day);
  }

  etiqueta(act: Activity) {
    if (act.type === 'break') return '30 + 5 · solo PC';
    return act.sync ? 'PC y celular' : 'solo PC';
  }

  aviso(msg: string) {
    this.snack.open(msg, 'Cerrar', { duration: 2500 });
  }
}