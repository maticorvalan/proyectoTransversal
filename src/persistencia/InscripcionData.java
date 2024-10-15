/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;


public class InscripcionData {
    private Connection conexion = null;
    private MateriaData md;
    private AlumnoData ad;
    
    public InscripcionData(Conexion conexion){
        this.conexion = conexion.buscarConexion();
        this.md = new MateriaData(conexion);
        this.ad = new AlumnoData(conexion);
    }
    
    //idInscripto en nuestra base de datos no es incremental no genera keys
    public void guardarInscripcion(Inscripcion insc){
        String query ="INSERT INTO inscripcion(idAlumno,idMateria,nota) VALUES(?,?,?)";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,insc.getIdAlumno());
            ps.setInt(2, insc.getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                
                insc.setIdinscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion registrada!");
                
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota){
        String query = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas>0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        
    }
    //PORQUE NO BUSCARMOS LA INSCRIPCION POR ID EN VES DE idAlumno ...
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        String query = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        
    }
    public List<Inscripcion> obtenerInscripciones(){
        ArrayList<Inscripcion> lista = new ArrayList<>();
        String query = "SELECT * FROM inscripcion";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Inscripcion insc = new Inscripcion();
                insc.setIdinscripcion(rs.getInt("idInscripto"));
                Alumno alum = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alum);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                lista.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        
    return lista;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        ArrayList<Inscripcion> lista = new ArrayList<>();
        String query = "SELECT * FROM inscripcion WHERE idAlumno = ?";
        try {
            PreparedStatement ps = conexion.prepareCall(query);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Inscripcion insc = new Inscripcion();
                insc.setIdinscripcion(rs.getInt("idInscripto"));
                Alumno alum = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alum);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                lista.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasCursadas(int idMateria){
        ArrayList<Materia> materias = new ArrayList<>();
        String query = "SELECT inscripcion.idMateria,nombre, año FROM inscripcion,"
                        + " materia WHERE incripciones.idMateria = materia.idMateria"
                        + "AND inscripcion.idAlumno = ?"; //REVISAR-- 
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setDescripción(rs.getString("descripcion"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }

        return materias;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int idAlumno){
        ArrayList<Materia> materias = new ArrayList<>();
        String query = "SELECT * FROM materia WHERE estado = 1 AND idMateria not in "
                        + "(SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";   
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setDescripción(rs.getString("descripcion"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }

        return materias;
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT a.idAlumno, dni, nombre, apellido, fechaNacimiento, estado "
                        + "FROM inscripcion i, alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ?";
        try {
            PreparedStatement   ps = conexion.prepareStatement(query);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Alumno alum = new Alumno();
                alum.setIdAlumno(rs.getInt("idAlumno"));
                alum.setApellido(rs.getString("apellido"));
                alum.setNombre(rs.getString("nombre"));
                alum.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alum.setEstado(rs.getBoolean("estado"));
                alumnos.add(alum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }

        return alumnos;
    }

}
