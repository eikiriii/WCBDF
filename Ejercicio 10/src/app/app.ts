import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Presentacion } from './presentacion/presentacion';

@Component({
  imports: [RouterOutlet, Presentacion],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('eje-fsq-10');
}
