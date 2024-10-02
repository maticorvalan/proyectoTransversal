/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author Nicolas
 */
public class Alumno {
    private int idAlumno;             
    private int año;                 
    private String apellido;         
    private String nombre;              
    private LocalDate fechaNacimiento;  
    private boolean estado;      

    
    public Alumno(int idAlumno, int año, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = idAlumno;
        this.año = año;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Alumno(int año, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.año = año;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Alumno() {
    }
    
    
    

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Alumno : ID : " + idAlumno + " / Año :" + año + " / Apellido : " + apellido + " / Nombre : " + nombre + " / Fecha Nacimiento : " + fechaNacimiento + " / Estado : " + estado ;
    }
  
    
    
           
}
