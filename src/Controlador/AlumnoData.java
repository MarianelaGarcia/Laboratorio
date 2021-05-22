/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.Date;
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
public class AlumnoData {

    private Connection connection;

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public AlumnoData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
            mensaje("Conectado a la base de datos");

        } catch (SQLException ex) {
            mensaje("Error al obtener la conexion en AlumnoData");
        }
    }

    public void guardarAlumno(Alumno alumno) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO alumno VALUES (NULL, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, Date.valueOf(alumno.getFecha_nac()));
            ps.setInt(4, alumno.getLegajo());
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId_alumno(rs.getInt(1));
                mensaje("Se ha guardado al alumno " + alumno.getNombre() + " correctamente");
            } else {
                mensaje("No se ha guardado al alumno" + alumno.getNombre() + " correctamente");
            }
        } catch (SQLException ex) {
            mensaje("Error al guardar al alumno: " + ex.getMessage());
        }
    }

    public List<Alumno> obtenerAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        Alumno alumno;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM alumno");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                alumno.setLegajo(rs.getInt("legajo"));
                alumno.setEstado(rs.getBoolean("estado"));
                lista.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al obtener los alumnos: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return lista;
    }

    public void borrarAlumnoLogico(int id_alumno) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE alumno SET estado = false WHERE id_alumno = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            if ((ps.executeUpdate() == 1)) {
                mensaje("Alumno borrado correctamente.");
            } else {
                mensaje("Error al borrar el alumno con id_alumno: " + id_alumno + " no pudo ser borrado");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al borrar el alumno con id_alumno: " + id_alumno + ". Error: " + ex.getMessage());
        }
    }

    public void borrarAlumnoFisico(int id_alumno) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM alumno WHERE id_alumno= ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            if ((ps.executeUpdate() == 1)) {
                mensaje("Alumno borrado correctamente.");
            } else {
                mensaje("Error al borrar el alumno con id_alumno: " + id_alumno + " no pudo ser borrado");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al actualizar el alumno con id_alumno: " + id_alumno + ". Error: " + ex.getMessage());
        }
    }

    public void actualizarAlumno(Alumno alumno) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE alumno SET nombre = ?, apellido = ?, fecha_nac = ? WHERE id_alumno = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, Date.valueOf(alumno.getFecha_nac()));
            ps.setInt(4, alumno.getId_alumno());
            if (ps.executeUpdate() == 1) {
                mensaje("Alumno con id_alumno: " + alumno.getId_alumno() + " actualizado correctamente.");
            } else {
                mensaje("Error al actualizar el alumno con id_alumno: " + alumno.getId_alumno() + ", el alumno no se encuentra agregado en la base de datos.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al actualizar el alumno con id_alumno: " + alumno.getId_alumno() + ". Error: " + ex.getMessage());
        }
    }

    public Alumno buscarAlumno(int id_alumno) {
        Alumno alumno = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM alumno WHERE id_alumno = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                alumno.setLegajo(rs.getInt("legajo"));
                alumno.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar el alumno con id_alumno: " + id_alumno + ". Error: " + ex.getMessage());
        }
        return alumno;
    }
}
