/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectotransversal;

import entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.Conexion;
import java.time.LocalDate;


public class ProyectoTransversal {

   
    
    public static void main(String[] args) {
       Conexion conexion = new Conexion("jdbc:mysql://localhost/universidad","root","");
       AlumnoData alumnoData = new AlumnoData(conexion);
       Alumno alumno = new Alumno(1, "Baroja", "Angel", LocalDate.of(1996,01,21) , true);
//       alumnoData.guardarAlumno(alumno);

       Alumno alumno2 = new Alumno(1, 1, "Baroja", "Angel Ricardo", LocalDate.of(1996,01,21) , true);
//       alumnoData.actualizarAlumno(alumno2);

       Alumno alu = alumnoData.buscarAlumno(2);
        if (alu!=null) {
            System.out.println(alu.toString());
        }
        
        
       Alumno alumno3 = new Alumno( 2, "Arias", "David", LocalDate.of(1994,06,22) , true);
//       alumnoData.guardarAlumno(alumno3);  

        System.out.println("______________________________________");
        for (Alumno alumnos : alumnoData.listaAlumno()) {
            System.out.println(alumnos.toString());
        }
        
//        alumnoData.borradoLogico(3);
//          alumnoData.borradoFisico(3);
        
    }
    
}
