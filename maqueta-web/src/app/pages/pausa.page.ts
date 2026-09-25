import { Component, OnDestroy, inject } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
 selector: 'app-pausa',
 imports: [MatButtonModule, MatIconModule, MatSnackBarModule],
 template: `
  <div class="break-ov">
   <mat-icon class="break-icon">self_improvement</mat-icon>
   <p class="break-kicker">Pausa de pantalla</p>
   <h1>Levántate 5 minutos</h1>
   <div class="timer">{{ reloj }}</div>
   <p>Break de pantalla</p>
   <div class="row" style="justify-content:center">
    <button mat-stroked-button type="button" (click)="masCinco()">Estoy en llamada (+5)</button>
    <button mat-button type="button" (click)="salir('Pausa saltada')">Saltar esta pausa</button>
    <button mat-flat-button color="primary" type="button" (click)="salir('Listo. Otros 30 min de trabajo')">Ya me paré</button>
   </div>
  </div>
 `,
})
export class PausaPage implements OnDestroy {
 router = inject(Router);
 snack = inject(MatSnackBar);
 segundos = 300;
 private t = setInterval(() => {
  if (this.segundos <= 0) {
   this.salir('Se acabaron los 5 min');
   return;
  }
  this.segundos--;
 }, 1000);

 get reloj() {
  const m = String(Math.floor(this.segundos / 60)).padStart(2, '0');
  const s = String(this.segundos % 60).padStart(2, '0');
  return m + ':' + s;
 }

 masCinco() {
  this.segundos += 300;
  this.snack.open('+5 min a esta pausa', 'Ok', { duration: 2000 });
 }

 salir(msg: string) {
  clearInterval(this.t);
  this.snack.open(msg, 'Ok', { duration: 2400 });
  this.router.navigateByUrl('/');
 }

 ngOnDestroy() {
  clearInterval(this.t);
 }
}