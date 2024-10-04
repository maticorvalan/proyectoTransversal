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
        private  int  id_inscripto;
        private int id_alumno ; 
        private int id_materia;
        private int nota;

    public Inscripcion(int id_inscripto, int id_alumno, int id_materia, int nota) {
        this.id_inscripto = id_inscripto;
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public int getId_inscripto() {
        return id_inscripto;
    }

    public void setId_inscripto(int id_inscripto) {
        this.id_inscripto = id_inscripto;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
 







}
