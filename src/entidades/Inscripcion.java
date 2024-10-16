/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Nicolas
 */
public class Inscripcion {
        private  int  idinscripcion;
        private Alumno alumno; 
        private Materia materia;
        private double nota;

    public Inscripcion(int id_inscripto, Alumno alumno, Materia materia, double nota) {
        this.idinscripcion = id_inscripto;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    public Inscripcion(Alumno alumno, Materia materia, double nota){
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    public Inscripcion(){
        idinscripcion = 0;
        alumno = null;
        materia = null;
        nota = 0;
    }
    public Inscripcion(double nota){
        this.nota = nota;
    }
    public int getIdinscripcion() {
        return idinscripcion;
    }

    public void setIdinscripcion(int idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    public int getIdAlumno(){
        return getAlumno().getIdAlumno();
    }
    public void setIdAlumno(int idAlumno){
        getAlumno().setIdAlumno(idAlumno);
    }
    public int getIdMateria(){
        return getMateria().getId_materia();
    }
    public void setIdMateria(int idMateria){
        getMateria().setId_materia(idMateria);
    }
    }
