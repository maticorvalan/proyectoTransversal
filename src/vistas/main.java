/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vistas;

import entidades.Alumno;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.Conexion;

/**
 *
 * @author Matias
 */
public class main {
    private AlumnoData alumnoData;
    private Conexion conexion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        Alumno alum1 = new Alumno(123456, "Perez", "Jose", fecha, true);
//        new main().conectar(alum1);
//        System.out.println("Alumno: " + alum1.getNombre() + " Guardado con exito");
//        new main().elim(alum1);
//        System.out.println("Alumno: " + alum1.getNombre() + " Fue dado de baja");
        Conexion conexion = new Conexion("jdbc:mysql://localhost/universidad", "root", "");
        AlumnoData alumnoData = new AlumnoData(conexion);
//        Alumno alum2 = new Alumno(7,123456, "Perez", "Jose", fecha, true);
//        alumnoData.actualizarAlumno(alum2);
        alumnoData.borradoFisico(7);
        
        
    }
    void conectar(Alumno alum){
        conexion = new Conexion("jdbc:mysql://localhost/universidad", "root", "");
        alumnoData = new AlumnoData(conexion);
        alumnoData.guardarAlumno(alum);
        Alumno alu = alumnoData.buscarAlumno(alum.getIdAlumno());
        System.out.println("Datos:" + alu);
    }
    void elim(Alumno alum){
        conexion = new Conexion("jdbc:mysql://localhost/universidad", "root", "");
        alumnoData = new AlumnoData(conexion);
        alumnoData.borradoLogico(alum.getIdAlumno());
        Alumno alu = alumnoData.buscarAlumno(alum.getIdAlumno());
        System.out.println("Datos: " + alu);
    }
}
