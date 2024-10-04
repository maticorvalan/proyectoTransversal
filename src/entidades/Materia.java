/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Nicolas
 */
public class Materia {
   private int id_materia;
   private String nombre;
   private  String descripción;
   private boolean estado;

    public Materia(int id_materia, String nombre, String descripción, boolean estado) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.descripción = descripción;
        this.estado = estado;
    }

    public Materia(String nombre, String descripción, boolean estado) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.estado = estado;
    }

    public Materia() {
       
    }
    
  

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override 
    public String toString (){
        return "Materia: ID : " + id_materia + " /Nombre:  " + nombre + " /Descripcion: " + descripción + "/Estado: " + estado;
        
    
}
    
}
