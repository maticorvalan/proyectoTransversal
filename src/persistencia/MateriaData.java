/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Materia;
        
public class MateriaData {
    
    private Connection conexion = null;
    private LocalDate lala;
    
    public MateriaData(Conexion conexion) {
        this.conexion= conexion.buscarConexion();
        
    }
    
  
    
     public void agregarMateria(Materia materia) {
        String query= "INSERT INTO materia (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getDescripción());
            ps.setBoolean(3, materia.isEstado());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                materia.setId_materia(rs.getInt(1));
                System.out.println("Materia agregada con éxito: " + materia);
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar materia: " + e.getMessage());
        }
    }

    public ArrayList<Materia> obtenerMaterias() {
        ArrayList<Materia> materias = new ArrayList();
        String query = "SELECT * FROM materia";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            Materia materia = new Materia(); 
                materia.setId_materia(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setDescripción(rs.getString("descripcion"));
                materia.setEstado(rs.getBoolean("estado"));

                materias.add(materia);
           
               
            }
                
            
        } catch (SQLException e) {
            System.out.println("Error al obtener materias: " + e.getMessage());
        }
        return materias;
    }

    public void actualizarMateria(Materia materia) {
        String query= "UPDATE materia SET nombre = ?, descripcion = ?, estado = ? WHERE idMateria = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getDescripción());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getId_materia());

            ps.executeUpdate();
            System.out.println("Materia actualizada: " + materia);
        } catch (SQLException e) {
            System.out.println("Error al actualizar materia: " + e.getMessage());
        }
    }

    public void borrarMateria(int id) {
        String sql = "DELETE FROM materia WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Materia borrada con ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error al borrar materia: " + e.getMessage());
        }
    }

    public void bajaLogica(int id) {
        String query = "UPDATE materia SET estado = false WHERE idMateria = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Baja lógica aplicada a la materia con ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error en baja lógica: " + e.getMessage());
        }
    }

    public void altaLogica(int id) {
        String query = "UPDATE materia SET activo = 1 WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Alta lógica aplicada a la materia con ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error en alta lógica: " + e.getMessage());
        }
    }
    
}
