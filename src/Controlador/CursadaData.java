/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cursada;
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
public class CursadaData {

    private Connection connection = null;

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public CursadaData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            mensaje("Error al cargar los drivers: " + ex.getMessage());
        }
    }

    public void guardarCursada(Cursada cursada) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO cursada VALUES (NULL,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getId_alumno());
            ps.setInt(2, cursada.getId_materia());
            ps.setInt(3, cursada.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cursada.setId_cursada(rs.getInt(1));
                mensaje("Se ha guardado correctamente la nota del alumno");
            } else {
                mensaje("No se ha guardado correctamente la nota del alumno");
            }
        } catch (SQLException ex) {
            mensaje("Error al guardar la nota del alumno: " + ex.getMessage());
        }
    }

    public List<Cursada> obtenerCursadas() {
        List<Cursada> lista = new ArrayList<>();
        Cursada cursada;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cursada");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cursada = new Cursada();
                cursada.setId_cursada(rs.getInt("id_cursada"));
                cursada.setId_alumno(rs.getInt("id_alumno"));
                cursada.setId_materia(rs.getInt("id_materia"));
                cursada.setNota(rs.getInt("nota"));
                lista.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al obtener las cursadas: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return lista;
    }

    public List<Cursada> obtenerCursadasXAlumno(int id_alumno) {
        List<Cursada> lista = new ArrayList<>();
        Cursada cursada;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cursada WHERE id_alumno = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cursada = new Cursada();
                cursada.setId_cursada(rs.getInt("id_cursada"));
                cursada.setId_alumno(rs.getInt("id_alumno"));
                cursada.setId_materia(rs.getInt("id_materia"));
                cursada.setNota(rs.getInt("nota"));
                lista.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar al alumno con la id ingresada: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return lista;
    }

    public List<Materia> obtenerMateriasCursadas(int id_alumno) {
        List<Materia> lista = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT materia.id_materia, nombre_materia, año, estado FROM materia, cursada WHERE materia.id_materia = cursada.id_materia and cursada.id_alumno = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setId_Materia(rs.getInt("id_materia"));
                m.setNombre_materia(rs.getString("nombre_materia"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                lista.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar al alumno con la id ingresada: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return lista;
    }

    public List<Materia> obtenerMateriasNOCursadas(int id_materia) {
        List<Materia> noCursada = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM materia WHERE id_materia NOT IN(SELECT materia.id_materia FROM materia, cursada WHERE materia.id_materia = cursada.id_materia AND cursada.id_alumno = ?) ");
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setId_Materia(rs.getInt("id_materia"));
                m.setNombre_materia(rs.getString("nombre_materia"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                noCursada.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al consultar la tabla: " + ex.getMessage());
        }
        if (noCursada.isEmpty()) {
            mensaje("La lista esta vacia.");
        }
        return noCursada;
    }

    public void borrarCursadaDeUnaMateriaDeunAlumno(int id_alumno, int id_materia) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM cursada WHERE id_alumno = ? AND id_materia = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
            if (ps.executeUpdate() == 1) {
                mensaje("La nota del alumno ha sido borrada correctamente");
            } else {
                mensaje("La nota del alumno no ha sido borrada.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al borrar la nota del alumno: " + ex.getMessage());
        }
    }

    public void actualizarNotaCursada(int id_alumno, int id_materia, int nota) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE cursada SET nota = ? WHERE id_alumno = ? AND id_materia = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);
            if (ps.executeUpdate() == 1) {
                mensaje("La nota del alumno ha sido actualizada correctamente.");
            } else {
                mensaje("La nota del alumno ha podido ser actualizada correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la nota de un alumno: " + ex.getMessage());
        }
    }

    private void JOptionPane(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
