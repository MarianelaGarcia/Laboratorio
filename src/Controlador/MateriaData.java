/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo2
 */
public class MateriaData {

    private Connection connection;

    public MateriaData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
            mensaje("Conectado a la base de datos");
        } catch (SQLException ex) {
            mensaje("Error al obtener la conexion");
        }
    }

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void guardarMateria(Materia materia) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO materia VALUES (NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre_Materia());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setId_Materia(rs.getInt(1));
                mensaje("Se ha guardado la materia " + materia.getNombre_Materia() + " correctamente");
            } else {
                mensaje("No se ha guardado la materia " + materia.getNombre_Materia() + " correctamente");
            }
        } catch (SQLException ex) {
            mensaje("Error al guardar la materia: " + ex.getMessage());
        }
    }

    public List<Materia> obtenerMaterias() {
        List<Materia> lista = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM materia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setNombre_materia(rs.getString("nombre_materia"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                lista.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al obtener las materias: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return lista;
    }

    public Materia buscarMateria(int id_materia) {
        Materia m = new Materia();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM materia WHERE id_materia=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m.setId_Materia(rs.getInt("id_materia"));
                m.setNombre_materia(rs.getString("nombre_materia"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar la materia con id_materia: " + m.getId_materia() + ". Error: " + ex.getMessage());
        }
        return m;
    }

    public void borrarMateriaFisico(int id_materia) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM materia WHERE id_materia = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_materia);
            if(ps.executeUpdate()==1){
                mensaje("Materia eliminada");
            } else {
                mensaje("La materia no se encuentra en la base de datos.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al eliminar la materia con id_materia: " + id_materia + ". Error: " + ex.getMessage());
        }
    }

    public void borrarMateriaLogico(int id_materia) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE materia SET estado = false WHERE id_materia = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_materia);
            if(ps.executeUpdate()==1){
                mensaje("Materia dada de baja");
            } else {
                mensaje("La materia no se encuentra en la base de datos.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al dar de baja la materia con id_materia: " + id_materia + ". Error: " + ex.getMessage());
        }
    }

    public void actualizarMateria(Materia materia) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE materia SET nombre_materia = ?, año = ? WHERE id_materia = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre_Materia());
            ps.setInt(2, materia.getAño());
            ps.setInt(3, materia.getId_materia());
            if(ps.executeUpdate()==1){
                mensaje("Materia con id_materia: " + materia.getId_materia() + " actualizada correctamente.");
            } else {
                mensaje("Materia con id_materia: " + materia.getId_materia() + " no pudo ser actualizada correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al actualizar la materia con id_materia: " + materia.getId_materia() + ". Error: " + ex.getMessage());
        }
    }
}
