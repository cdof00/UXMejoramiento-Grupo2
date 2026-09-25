import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivityStore } from '../data/activity.store';

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
export class ConfirmarPage {
 store = inject(ActivityStore);
 route = inject(ActivatedRoute);
 router = inject(Router);
 snack = inject(MatSnackBar);
 id = Number(this.route.snapshot.paramMap.get('id'));
 act = this.store.byId(this.id);

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