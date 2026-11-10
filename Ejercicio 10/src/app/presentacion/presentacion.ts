import { Component } from '@angular/core';

@Component({
  imports: [],
  selector: 'app-presentacion',
  styleUrl: './presentacion.css',
  templateUrl: './presentacion.html',
})
//Typescript - JavaScript orientado a objetos
export class Presentacion {

  nombre:string="Fernanda Salazar Quiñonez";
  edad:number=21; 
  carrera:string="Ingeniería en Sistemas Computacionales";
  semestre:string="7mo Semestre";
  institucion:string="UPIIZ - IPN";
  correo:string="fsalazarq2000@alumno.ipn.mx";
  mensaje:string = "Soy estudiante de Ingeniería en Sistemas Computacionales. Me interesa el desarrollo de software y las nuevas tecnologías.";

  habilidades: string[] = [
    'Java',
    'Python',
    'HTML',
    'CSS',
    'JavaScript',
    'Bases de datos'
  ];

  idiomas: string[] = [
    'Español',
    'Inglés',
    'Chino mandarín'
  ];

  intereses: string[] = [
  'Dibujo y pintura',
  'Lectura de libros distópicos',
  'Viajar y conocer nuevas culturas',
  'Desarrollo de aplicaciones móviles y web'
  ]

}
