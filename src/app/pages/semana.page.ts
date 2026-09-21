import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore, DAYS, type Activity, type DayIndex } from '../data/activity.store';

@Component({
  selector: 'app-semana',
  imports: [RouterLink, RouterOutlet, MatButtonModule, MatIconModule, MatMenuModule, MatSnackBarModule],
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
          <button mat-icon-button [matMenuTriggerFor]="menu">
            <mat-icon>more_vert</mat-icon>
          </button>
          <mat-menu #menu="matMenu">
            <button mat-menu-item (click)="vacio = !vacio">{{ vacio ? 'Restaurar demo' : 'Ver lienzo vacío' }}</button>
          </mat-menu>
          <a mat-flat-button color="primary" routerLink="/actividad">
            <mat-icon>add</mat-icon>
            Crear
          </a>
        </div>
      </header>

      <section class="now">
        <mat-icon>desktop_windows</mat-icon>
        <div>
          <strong>Break de pantalla · faltan 12 min para la pausa</strong>
          <small>Aviso a los 2 min · overlay a los 0 · 30 de trabajo + 5 de pausa</small>
        </div>
      </section>

      @if (vacio) {
        <div class="empty">
          <p>No tiene actividades</p>
          <a mat-flat-button color="primary" routerLink="/actividad">
            <mat-icon>add</mat-icon>
            Crear
          </a>
        </div>
      } @else {
        <div class="layout">
          <aside class="rail">
            <h2>Actividades</h2>
            <p class="hint">Clic en el nombre = editar la serie. Clic en el bloque del día = este día.</p>
            @for (act of store.lista(); track act.id) {
              <button class="li" [routerLink]="['/actividad', act.id]">
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
                  @if (d.i === 0) {
                    <em>HOY</em>
                  }
                </div>
                @for (m of reuniones(d.i); track m.title) {
                  <button class="meet" type="button" (click)="aviso('Pausa omitida: ' + m.title + ' está en Calendar')">
                    Calendar · {{ m.start }} {{ m.title }}
                  </button>
                }
                @for (act of store.lista(); track act.id) {
                  @if (store.occ(act, d.i); as o) {
                    @if (o.time || o.kind === 'skipped') {
                      <button
                        class="block"
                        [class.paused]="o.kind === 'paused'"
                        [class.skipped]="o.kind === 'skipped'"
                        [style.background]="act.color"
                        type="button"
                        (click)="abrirDia(act, d.i)">
                        <small>{{ o.time || '—' }}</small>
                        <b>{{ act.name }}</b>
                        <small>{{ act.type === 'break' ? '30 + 5 · no va al celular' : act.sync ? 'también celular' : 'solo PC' }}</small>
                        @if (o.kind === 'moved') { <span class="tag">este día</span> }
                        @if (o.kind === 'skipped') { <span class="tag">omitido</span> }
                        @if (o.kind === 'paused') { <span class="tag">pausada</span> }
                      </button>
                    }
                  }
                }
              </div>
            }
          </div>
        </div>
      }

      @if (conFondo()) {
        <div class="scrim" (click)="cerrar()"></div>
      }
      <router-outlet />
    </div>
  `,
})
export class SemanaPage {
  store = inject(ActivityStore);
  router = inject(Router);
  snack = inject(MatSnackBar);
  days = DAYS;
  vacio = false;

  conFondo() {
    const u = this.router.url;
    return u.startsWith('/actividad') || u.startsWith('/este-dia');
  }

  cerrar() {
    this.router.navigateByUrl('/');
  }

  reuniones(day: DayIndex) {
    return this.store.meetings.filter((m) => m.day === day);
  }

  abrirDia(act: Activity, day: DayIndex) {
    this.router.navigate(['/este-dia', act.id, day]);
  }

  etiqueta(act: Activity) {
    if (act.paused) return 'Pausada';
    if (act.type === 'break') return '30 + 5 · solo PC';
    return act.sync ? 'PC y celular' : 'solo PC';
  }

  aviso(msg: string) {
    this.snack.open(msg, 'Cerrar', { duration: 2800 });
  }
}