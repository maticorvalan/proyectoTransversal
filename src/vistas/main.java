/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vistas;

import entidades.Alumno;
import entidades.Inscripcion;
import java.time.LocalDate;
import persistencia.AlumnoData;
import persistencia.Conexion;

import entidades.Materia;
import persistencia.InscripcionData;
import persistencia.MateriaData;
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
           // Alumno alum1 = new Alumno(123456, "Perez", "Jose", fecha, true);
//        new main().conectar(alum1);
//        System.out.println("Alumno: " + alum1.getNombre() + " Guardado con exito");
//        new main().elim(alum1);
//        System.out.println("Alumno: " + alum1.getNombre() + " Fue dado de baja");
        //Conexion conexion = new Conexion("jdbc:mysql://localhost/universidad", "root", "");
        //AlumnoData alumnoData = new AlumnoData(conexion);
//        Alumno alum2 = new Alumno(7,123456, "Perez", "Jose", fecha, true);
//        alumnoData.actualizarAlumno(alum2);
//        alumnoData.borradoFisico(7);       
        Menu menu= new Menu();
       menu.setVisible(true);
       
        
       // Materia mat1 =new Materia("Matematica","Logica",true);
        Conexion conexion = new Conexion ("jdbc:mysql://localhost/universidad", "root", "");
        //MateriaData materiaData = new MateriaData(conexion); 
        
    /*   AlumnoData ad = new AlumnoData(conexion);
        MateriaData md = new MateriaData(conexion);
        InscripcionData id = new InscripcionData(conexion);
        Alumno matias = ad.buscarAlumno(8);
        Materia mate = md.buscarMateria(3);
        Inscripcion insc = new Inscripcion(matias, mate, 9);
    //    id.guardarInscripcion(insc);
    //    id.actualizarNota(8, 3, 5);
    //    id.borrarInscripcionMateriaAlumno(8, 3);
        for (Inscripcion inscripcion : id.obtenerInscripciones()) {
            System.out.println("id" + inscripcion.getIdinscripcion());
            System.out.println("Apellido" + inscripcion.getAlumno().getApellido());
            System.out.println("Materia:" + inscripcion.getMateria().getNombre());
        }
        for (Materia materia : id.obtenerMateriasNOCursadas(9)) {
            System.out.println("Nombre: " + materia.getNombre());
        }
   
    */
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
    
    void conectar (Materia mat){
        conexion = new Conexion ("jdbc:mysql://localhost/universidad", "root", "");
         MateriaData materiaData = new MateriaData(conexion);
         materiaData.guardarMateria(mat);
         Materia ma = materiaData.buscarMateria(mat.getId_materia());
         System.out.println("Datos" + ma);
    }
}
