/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import entidades.Alumno;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoData {

    private Connection conexion = null;
    private LocalDate lala;

    public AlumnoData(Conexion conexion) {
        this.conexion = conexion.buscarConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String query = "INSERT INTO `alumno`(`dni`, `apellido`, `nombre`, `fechaNacimiento`, `estado`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el IdAlumno");
            }
            ps.close();
            System.out.println("Alumno Guardado");
        } catch (SQLException ex) {
            System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS GUARDANDO");
        }

    }

    public void actualizarAlumno(Alumno alumno) {
        String query = "UPDATE `alumno` SET `dni`= ? ,`apellido`= ? ,`nombre`= ? ,`fechaNacimiento`= ? ,`estado`= ? WHERE idAlumno = ? ";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            ps.executeUpdate();

            ps.close();
            System.out.println("Alumno Actualizado");
        } catch (SQLException ex) {
            System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS ACTUALIZANDO");
        }

    }
    
    public Alumno buscarAlumno(int id){
        Alumno alumno=null;
         String query = "SELECT * FROM `alumno` WHERE idAlumno = ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setApellido(rs.getString("nombre"));
                alumno.setFechaNacimiento((rs.getDate("fechaNacimiento")).toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                System.out.println("Alumno Encontrado");
            } else{
                System.out.println("No existe ese alumno");
            }
            ps.close();            
        } catch (SQLException ex) {
            System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS BUSCANDOLO");
        }
        return alumno;
    }
    
     public ArrayList<Alumno> listaAlumno(){
        ArrayList<Alumno> listaAlumno= new ArrayList();
        Alumno alumno=null;
        String query = "SELECT * FROM `alumno`";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento((rs.getDate("fechaNacimiento")).toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                listaAlumno.add(alumno);
            }
            ps.close();
            System.out.println("Lista de Alumnos");
        } catch (SQLException ex) {
            System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS BUSCANDO LA LISTA DE ALUMNOS");
        }
        return listaAlumno;
    }
    
    public void borradoFisico(int id) {
        String query = "DELETE FROM alumno WHERE idAlumno= ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            System.out.println("Alumno Borrado");
        } catch (SQLException ex) {
            System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS BORRANDOLO FISICAMENTE");
        }
    }
    
     public void borradoLogico(int id) {
      String query = "UPDATE `alumno` SET `estado`= false WHERE idAlumno = ? ";
         try {
             PreparedStatement ps = conexion.prepareStatement(query);
             ps.setInt(1, id);
             ps.executeUpdate();
             ps.close();
             System.out.println("Alumno Borrado");
         } catch (SQLException ex) {
             System.out.println("SE PRODUJO UN ERROR CON LA BASE DE DATOS BORRANDOLO LOGICAMENTE");;
         }
    }

}
