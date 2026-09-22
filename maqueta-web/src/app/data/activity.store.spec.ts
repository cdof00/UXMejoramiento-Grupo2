import { TestBed } from '@angular/core/testing';
import { ActivityStore, COLORES, type Activity } from './activity.store';

describe('ActivityStore', () => {
  let store: ActivityStore;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    store = TestBed.inject(ActivityStore);
  });

  it('debe crear el store con actividades iniciales', () => {
    expect(store.lista().length).toBe(3);
  });

  it('debe encontrar una actividad por id', () => {
    const actividad = store.byId(1);
    expect(actividad).toBeTruthy();
    expect(actividad!.name).toBe('Clase de métodos');
  });

  it('debe retornar undefined para un id inexistente', () => {
    expect(store.byId(999)).toBeUndefined();
  });

  it('debe calcular la ocurrencia correctamente', () => {
    const actividad = store.byId(1)!;
    expect(store.occ(actividad, 0)).toEqual({ kind: 'on', time: '08:00' });
    expect(store.occ(actividad, 1)).toEqual({ kind: 'off', time: null });
  });

  it('debe guardar una nueva actividad', () => {
    const id = store.guardar({
      name: 'Nueva',
      desc: '',
      color: COLORES[0],
      type: 'alarm',
      hours: { 0: '10:00' },
      sync: false,
      yieldCalendar: false,
    });

    const creada = store.byId(id);
    expect(creada).toBeTruthy();
    expect(creada!.name).toBe('Nueva');
    expect(creada!.paused).toBeFalse();
  });

  it('debe actualizar una actividad existente', () => {
    store.guardar({
      id: 1,
      name: 'Clase actualizada',
      desc: '',
      color: COLORES[0],
      type: 'alarm',
      hours: { 0: '08:00' },
      sync: false,
      yieldCalendar: false,
    });

    expect(store.byId(1)!.name).toBe('Clase actualizada');
  });

  it('debe borrar una actividad', () => {
    store.borrar(1);
    expect(store.byId(1)).toBeUndefined();
  });

  it('debe alternar la pausa de una actividad', () => {
    const antes = store.byId(1)!;
    expect(antes.paused).toBeFalse();

    store.pausar(1);
    expect(store.byId(1)!.paused).toBeTrue();

    store.pausar(1);
    expect(store.byId(1)!.paused).toBeFalse();
  });

  it('debe guardar una excepción de omisión', () => {
    store.excepcion(1, 0, { skip: true });
    const actividad = store.byId(1)!;
    expect(store.occ(actividad, 0)).toEqual({ kind: 'skipped', time: '08:00' });
  });

  it('debe guardar una excepción de cambio de hora', () => {
    store.excepcion(1, 0, { time: '12:00' });
    const actividad = store.byId(1)!;
    expect(store.occ(actividad, 0)).toEqual({ kind: 'moved', time: '12:00' });
  });
});
