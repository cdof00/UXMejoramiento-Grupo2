import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatChipsModule } from '@angular/material/chips';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { COLORES, ActivityStore, DAYS, type ActivityType, type DayIndex } from '../data/activity.store';

type Plantilla = 'clase' | 'pastilla' | 'break';
type Patron = 'custom' | 'lv' | 'odd' | 'every2';

@Component({
  selector: 'app-actividad',
  imports: [
    FormsModule,
    RouterLink,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatChipsModule,
    MatSnackBarModule,
  ],
  template: `
    <aside class="drawer">
      <div class="drawer-head">
        <h2>{{ editId ? 'Editar actividad' : 'Nueva actividad' }}</h2>
        <a mat-icon-button routerLink="/"><mat-icon>close</mat-icon></a>
      </div>
      <p class="muted">{{ editId ? 'Esto cambia todos los días de la serie.' : 'Empieza por el nombre. La hora viene después.' }}</p>

      <p class="label">Plantilla</p>
      <mat-chip-listbox [multiple]="false" [(ngModel)]="plantilla" (ngModelChange)="aplicarPlantilla($event)">
        <mat-chip-option value="clase">Clase</mat-chip-option>
        <mat-chip-option value="pastilla">Pastilla</mat-chip-option>
        <mat-chip-option value="break">Break PC</mat-chip-option>
      </mat-chip-listbox>

      <mat-form-field appearance="outline" class="field">
        <mat-label>Nombre de la actividad</mat-label>
        <input matInput [(ngModel)]="name" maxlength="60" required />
        <mat-hint align="end">{{ name.length }} / 60</mat-hint>
        @if (intento && !name.trim()) {
          <mat-error>Ponle un nombre. Sin nombre no hay objeto.</mat-error>
        }
      </mat-form-field>

      <mat-form-field appearance="outline" class="field">
        <mat-label>Para qué (opcional)</mat-label>
        <input matInput [(ngModel)]="desc" />
        <mat-hint>Se ve al sonar, debajo del nombre.</mat-hint>
      </mat-form-field>

      <mat-form-field appearance="outline" class="field">
        <mat-label>Color</mat-label>
        <mat-select [(ngModel)]="color">
          @for (c of colores; track c) {
            <mat-option [value]="c">
              <span class="dot" [style.background]="c"></span>
            </mat-option>
          }
        </mat-select>
      </mat-form-field>

      @if (tipo !== 'break') {
        <p class="label">Patrón de días</p>
        <mat-chip-listbox [multiple]="false" [(ngModel)]="patron" (ngModelChange)="aplicarPatron($event)">
          <mat-chip-option value="custom">Días sueltos</mat-chip-option>
          <mat-chip-option value="lv">Lun a vie</mat-chip-option>
          <mat-chip-option value="odd">Impares</mat-chip-option>
          <mat-chip-option value="every2">Cada 2 días</mat-chip-option>
        </mat-chip-listbox>
        <p class="label">Horario según el día</p>
        <p class="muted">Deja vacío el día que no aplica.</p>
        @for (d of days; track d.i) {
          <div class="day-row">
            <span>{{ d.short }} {{ d.date }}</span>
            <mat-form-field appearance="outline" class="field" style="margin:0">
              <mat-label>Hora</mat-label>
              <input matInput type="time" [(ngModel)]="hours[d.i]" />
            </mat-form-field>
          </div>
        }
        @if (intento && !tieneHora()) {
          <p class="error">Elige al menos un día y una hora.</p>
        }
        <label class="switch-row">
          <span>
            <span>También suena en el celular</span>
            <small>El celular dispara cuando no estás en el PC.</small>
          </span>
          <mat-slide-toggle [(ngModel)]="sync"></mat-slide-toggle>
        </label>
      } @else {
        <p class="label">Ritmo en este PC</p>
        <div class="rhythm">
          <div class="rhythm-card">
            <span>trabajo</span>
            <strong>30 min</strong>
          </div>
          <mat-icon>add</mat-icon>
          <div class="rhythm-card pause">
            <span>pausa</span>
            <strong>5 min</strong>
          </div>
        </div>
        <p class="muted">Lun a vie · 09:00–13:00 y 14:00–18:00</p>
        <label class="switch-row">
          <span>Si hay reunión en Calendar, no interrumpe</span>
          <mat-slide-toggle [(ngModel)]="cederCal"></mat-slide-toggle>
        </label>
      }

      @if (editId) {
        <div class="drawer-foot">
          <a mat-button [routerLink]="['/este-dia', editId, 0]">
            <mat-icon>today</mat-icon>
            Este día
          </a>
          <a mat-button [routerLink]="tipo === 'break' ? ['/pausa', editId] : ['/aviso', editId]">
            <mat-icon>{{ tipo === 'break' ? 'self_improvement' : 'alarm' }}</mat-icon>
            {{ tipo === 'break' ? 'Iniciar pausa' : 'Ver aviso' }}
          </a>
          <button mat-button type="button" (click)="pausar()">
            <mat-icon>{{ paused ? 'play_arrow' : 'pause' }}</mat-icon>
            {{ paused ? 'Reanudar serie' : 'Pausar serie' }}
          </button>
          <button mat-button type="button" (click)="borrar()">
            <mat-icon>delete</mat-icon>
            Borrar serie
          </button>
        </div>
      }

      <div class="drawer-actions">
        <a mat-button routerLink="/">Cancelar</a>
        <button mat-flat-button color="primary" type="button" (click)="guardar()">Guardar</button>
      </div>
    </aside>
  `,
})
export class ActividadPage implements OnInit {
  private readonly store = inject(ActivityStore);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly snack = inject(MatSnackBar);
  private readonly destroyRef = inject(DestroyRef);

  readonly days = DAYS;
  readonly colores = COLORES;

  editId: number | null = null;
  name = '';
  desc = '';
  color: string = COLORES[0];
  tipo: ActivityType = 'alarm';
  hours: Partial<Record<DayIndex, string>> = {};
  sync = true;
  cederCal = true;
  plantilla: Plantilla | undefined;
  patron: Patron = 'custom';
  intento = false;
  paused = false;

  ngOnInit(): void {
    this.route.paramMap.pipe(takeUntilDestroyed(this.destroyRef)).subscribe((params) => {
      const id = Number(params.get('id'));
      this.cargarActividad(id);
    });
  }

  private cargarActividad(id: number): void {
    if (!id) {
      this.reiniciarFormulario();
      return;
    }

    const actividad = this.store.byId(id);
    if (!actividad) {
      this.router.navigateByUrl('/');
      return;
    }

    this.editId = actividad.id;
    this.name = actividad.name;
    this.desc = actividad.desc;
    this.color = actividad.color;
    this.tipo = actividad.type;
    this.hours = { ...actividad.hours };
    this.sync = actividad.sync;
    this.cederCal = actividad.yieldCalendar;
    this.paused = actividad.paused;
  }

  private reiniciarFormulario(): void {
    this.editId = null;
    this.name = '';
    this.desc = '';
    this.color = COLORES[0];
    this.tipo = 'alarm';
    this.hours = {};
    this.sync = true;
    this.cederCal = true;
    this.plantilla = undefined;
    this.patron = 'custom';
    this.intento = false;
    this.paused = false;
  }

  aplicarPlantilla(id: Plantilla): void {
    if (id === 'clase') {
      this.name = 'Clase de métodos';
      this.hours = { 0: '08:00', 2: '08:00', 3: '10:00' };
      this.tipo = 'alarm';
      this.sync = true;
      this.color = COLORES[0];
    } else if (id === 'pastilla') {
      this.name = 'Pastilla';
      this.hours = { 0: '21:00', 1: '21:00', 2: '21:00', 3: '21:00', 4: '21:00' };
      this.tipo = 'alarm';
      this.sync = true;
      this.color = COLORES[1];
    } else {
      this.name = 'Break de pantalla';
      this.tipo = 'break';
      this.sync = false;
      this.cederCal = true;
      this.color = COLORES[2];
    }
  }

  aplicarPatron(id: Patron): void {
    if (id === 'custom') return;

    const base = Object.values(this.hours).find(Boolean) || '08:00';
    const dias: DayIndex[] =
      id === 'lv' ? [0, 1, 2, 3, 4] : id === 'odd' ? [0, 2, 4] : id === 'every2' ? [0, 2, 4, 6] : [];

    const next: Partial<Record<DayIndex, string>> = {};
    for (const d of dias) {
      next[d] = base;
    }
    this.hours = next;
  }

  tieneHora(): boolean {
    return Object.values(this.hours).some(Boolean);
  }

  pausar(): void {
    if (!this.editId) return;

    this.store.pausar(this.editId);
    this.paused = !this.paused;
    this.snack.open(this.paused ? 'Serie pausada' : 'Serie reanudada', 'Ok', { duration: 2000 });
  }

  borrar(): void {
    if (!this.editId) return;
    this.router.navigate(['/confirmar', this.editId]);
  }

  guardar(): void {
    this.intento = true;
    if (!this.name.trim()) return;
    if (this.tipo !== 'break' && !this.tieneHora()) return;

    const hours = this.tipo === 'break'
      ? { 0: '09:00', 1: '09:00', 2: '09:00', 3: '09:00', 4: '09:00' }
      : this.hours;

    this.store.guardar({
      id: this.editId ?? undefined,
      name: this.name.trim(),
      desc: this.desc,
      color: this.color,
      type: this.tipo,
      hours,
      sync: this.tipo === 'break' ? false : this.sync,
      yieldCalendar: this.tipo === 'break' ? this.cederCal : false,
    });

    this.snack.open(this.editId ? 'Serie actualizada' : 'Actividad creada', 'Cerrar', { duration: 2200 });
    this.router.navigateByUrl('/');
  }
}
