import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore, type Activity } from '../data/activity.store';

@Component({
 selector: 'app-confirmar',
 imports: [RouterLink, MatButtonModule, MatSnackBarModule],
 template: `
  <div class="modal-card">
   <h1>¿Borrar toda la serie?</h1>
   <p>Se quita «{{ act?.name }}» de todos los días.</p>
   <div class="actions">
    <a mat-button [routerLink]="['/actividad', id]">No, volver</a>
    <button mat-flat-button color="primary" type="button" (click)="ok()">Sí, borrar serie</button>
   </div>
   <p>
    <button mat-button type="button" (click)="pausar()">
     {{ act?.paused ? 'Reanudar serie' : 'Pausar serie' }}
    </button>
   </p>
  </div>
 `,
})
export class ConfirmarPage implements OnInit {
 private readonly store = inject(ActivityStore);
 private readonly route = inject(ActivatedRoute);
 router = inject(Router);
 snack = inject(MatSnackBar);
 private readonly destroyRef = inject(DestroyRef);

 id = 0;
 act: Activity | undefined;

 ngOnInit(): void {
  this.route.paramMap.pipe(takeUntilDestroyed(this.destroyRef)).subscribe((params) => {
   this.id = Number(params.get('id'));
   this.act = this.store.byId(this.id);
  });
 }

 ok() {
  this.store.borrar(this.id);
  this.snack.open('Serie borrada', 'Ok', { duration: 3500 });
  this.router.navigateByUrl('/');
 }

 pausar() {
  this.store.pausar(this.id);
  this.snack.open(this.act?.paused ? 'Serie reanudada' : 'Serie pausada', 'Ok', { duration: 2000 });
  this.router.navigateByUrl('/');
 }
}