import { Injectable, signal } from '@angular/core';

export type DayIndex = 0 | 1 | 2 | 3 | 4 | 5 | 6;

export interface Activity {
  id: number;
  name: string;
  desc: string;
  color: string;
  type: 'alarm' | 'break';
  hours: Partial<Record<DayIndex, string>>;
  sync: boolean;
  yieldCalendar: boolean;
  paused: boolean;
  exceptions: Record<string, { skip?: boolean; time?: string }>;
}

export const DAYS = [
  { i: 0 as DayIndex, short: 'Lun', name: 'lunes', date: 7 },
  { i: 1 as DayIndex, short: 'Mar', name: 'martes', date: 8 },
  { i: 2 as DayIndex, short: 'Mié', name: 'miércoles', date: 9 },
  { i: 3 as DayIndex, short: 'Jue', name: 'jueves', date: 10 },
  { i: 4 as DayIndex, short: 'Vie', name: 'viernes', date: 11 },
  { i: 5 as DayIndex, short: 'Sáb', name: 'sábado', date: 12 },
  { i: 6 as DayIndex, short: 'Dom', name: 'domingo', date: 13 },
];

export const COLORES = [
  '#7B6CFF',
  '#A78BFA',
  '#6EE7B7',
  '#C4B5FD',
  '#818CF8',
  '#5B4B8A',
  '#9F7AEA',
  '#34D399',
];

@Injectable({ providedIn: 'root' })
export class ActivityStore {
  private nextId = 4;

  lista = signal<Activity[]>([
    {
      id: 1,
      name: 'Clase de métodos',
      desc: 'Métodos de investigación',
      color: COLORES[0],
      type: 'alarm',
      hours: { 0: '08:00', 2: '08:00', 3: '10:00' },
      sync: true,
      yieldCalendar: false,
      paused: false,
      exceptions: {},
    },
    {
      id: 2,
      name: 'Pastilla',
      desc: 'Toma de la noche',
      color: COLORES[1],
      type: 'alarm',
      hours: { 0: '21:00', 1: '21:00', 2: '21:00', 3: '21:00', 4: '21:00' },
      sync: true,
      yieldCalendar: false,
      paused: false,
      exceptions: {},
    },
    {
      id: 3,
      name: 'Break de pantalla',
      desc: '30 de trabajo + 5 de pausa',
      color: COLORES[2],
      type: 'break',
      hours: { 0: '09:00', 1: '09:00', 2: '09:00', 3: '09:00', 4: '09:00' },
      sync: false,
      yieldCalendar: true,
      paused: false,
      exceptions: {},
    },
  ]);

  meetings = [{ day: 3 as DayIndex, start: '10:00', end: '11:00', title: 'Reunión de equipo' }];

  byId(id: number) {
    return this.lista().find((a) => a.id === id);
  }

  occ(act: Activity, day: DayIndex) {
    if (act.paused) {
      return { kind: 'paused' as const, time: act.hours[day] || null };
    }
    const ex = act.exceptions[String(day)];
    if (ex?.skip) return { kind: 'skipped' as const, time: act.hours[day] || null };
    if (ex?.time) return { kind: 'moved' as const, time: ex.time };
    if (act.hours[day]) return { kind: 'on' as const, time: act.hours[day]! };
    return { kind: 'off' as const, time: null };
  }

  guardar(data: Omit<Activity, 'id' | 'exceptions' | 'paused'> & { id?: number }) {
    if (data.id) {
      this.lista.update((arr) => arr.map((a) => (a.id === data.id ? { ...a, ...data } : a)));
      return data.id;
    }
    const id = this.nextId++;
    this.lista.update((arr) => [...arr, { ...data, id, paused: false, exceptions: {} }]);
    return id;
  }

  borrar(id: number) {
    this.lista.update((arr) => arr.filter((a) => a.id !== id));
  }

  pausar(id: number) {
    this.lista.update((arr) => arr.map((a) => (a.id === id ? { ...a, paused: !a.paused } : a)));
  }

  excepcion(id: number, day: DayIndex, value: { skip?: boolean; time?: string }) {
    this.lista.update((arr) =>
      arr.map((a) => (a.id === id ? { ...a, exceptions: { ...a.exceptions, [String(day)]: value } } : a)),
    );
  }
}