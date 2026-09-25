import { Injectable, signal } from '@angular/core';

export type DayIndex = 0 | 1 | 2 | 3 | 4 | 5 | 6;

export type ActivityType = 'alarm' | 'break';

export type OccurrenceKind = 'on' | 'off' | 'paused' | 'skipped' | 'moved';

export interface ExceptionValue {
  skip?: boolean;
  time?: string;
}

export interface Activity {
  id: number;
  name: string;
  desc: string;
  color: string;
  type: ActivityType;
  hours: Partial<Record<DayIndex, string>>;
  sync: boolean;
  yieldCalendar: boolean;
  paused: boolean;
  exceptions: Record<string, ExceptionValue>;
}

export interface Meeting {
  day: DayIndex;
  start: string;
  end: string;
  title: string;
}

export const DAYS = [
  { i: 0 as DayIndex, short: 'Lun', name: 'lunes', date: 7 },
  { i: 1 as DayIndex, short: 'Mar', name: 'martes', date: 8 },
  { i: 2 as DayIndex, short: 'Mié', name: 'miércoles', date: 9 },
  { i: 3 as DayIndex, short: 'Jue', name: 'jueves', date: 10 },
  { i: 4 as DayIndex, short: 'Vie', name: 'viernes', date: 11 },
  { i: 5 as DayIndex, short: 'Sáb', name: 'sábado', date: 12 },
  { i: 6 as DayIndex, short: 'Dom', name: 'domingo', date: 13 },
] as const;

export const COLORES = [
  '#7B6CFF',
  '#A78BFA',
  '#4EA82A',
  '#C4B5FD',
  '#818CF8',
  '#5B4B8A',
  '#9F7AEA',
  '#34D399',
];

const INITIAL_ACTIVITIES: Activity[] = [
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
];

@Injectable({ providedIn: 'root' })
export class ActivityStore {
  private nextId = Math.max(0, ...INITIAL_ACTIVITIES.map((actividad) => actividad.id)) + 1;

  lista = signal<Activity[]>(INITIAL_ACTIVITIES);

  readonly meetings: readonly Meeting[] = [
    { day: 3 as DayIndex, start: '10:00', end: '11:00', title: 'Reunión de equipo' },
  ];

  byId(id: number): Activity | undefined {
    return this.lista().find((actividad) => actividad.id === id);
  }

  occ(actividad: Activity, day: DayIndex): { kind: OccurrenceKind; time: string | null } {
    if (actividad.paused) {
      return { kind: 'paused', time: actividad.hours[day] ?? null };
    }

    const excepcion = actividad.exceptions[String(day)];
    if (excepcion?.skip) {
      return { kind: 'skipped', time: actividad.hours[day] ?? null };
    }
    if (excepcion?.time) {
      return { kind: 'moved', time: excepcion.time };
    }
    if (actividad.hours[day]) {
      return { kind: 'on', time: actividad.hours[day]! };
    }
    return { kind: 'off', time: null };
  }

  guardar(data: Omit<Activity, 'id' | 'exceptions' | 'paused'> & { id?: number }): number {
    const actividades = this.lista();
    const usados = new Set(
      actividades
        .filter((actividad) => !data.id || actividad.id !== data.id)
        .map((actividad) => actividad.color),
    );
    const color = usados.has(data.color) ? COLORES.find((c) => !usados.has(c)) ?? data.color : data.color;
    const dataConColor = { ...data, color };

    if (data.id) {
      this.lista.update((lista) =>
        lista.map((actividad) => (actividad.id === data.id ? { ...actividad, ...dataConColor } : actividad)),
      );
      return data.id;
    }

    const id = this.nextId++;
    this.lista.update((lista) => [...lista, { ...dataConColor, id, paused: false, exceptions: {} }]);
    return id;
  }

  borrar(id: number): void {
    this.lista.update((actividades) => actividades.filter((actividad) => actividad.id !== id));
  }

  pausar(id: number): void {
    this.lista.update((actividades) =>
      actividades.map((actividad) => (actividad.id === id ? { ...actividad, paused: !actividad.paused } : actividad)),
    );
  }

  excepcion(id: number, day: DayIndex, value: ExceptionValue): void {
    this.lista.update((actividades) =>
      actividades.map((actividad) =>
        actividad.id === id
          ? { ...actividad, exceptions: { ...actividad.exceptions, [String(day)]: value } }
          : actividad,
      ),
    );
  }
}
