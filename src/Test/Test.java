/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controlador.AlumnoData;
import Controlador.Conexion;
import Controlador.CursadaData;
import Controlador.MateriaData;
import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo2
 */
public class Test {

    public static void main(String[] args) {
        Alumno castor = new Alumno("castor", "ocaña", LocalDate.of(1970, 7, 11), 125, true);
        Alumno cristian = new Alumno("cristian", "nursia", LocalDate.of(1989, 5, 15), 157, true);
        Alumno federico = new Alumno("federico", "alincastro", LocalDate.of(2001, 9, 22), 223, true);
        Materia portugues = new Materia("Portugues", 1, true);
        Materia web1 = new Materia("Web 1", 1, true);
        Materia eda = new Materia("EDA", 1, true);
        try {
            Conexion conexion = new Conexion();
            AlumnoData ad = new AlumnoData(conexion);
            MateriaData md = new MateriaData(conexion);
            CursadaData cd = new CursadaData(conexion);
            ad.guardarAlumno(castor);
            ad.guardarAlumno(federico);
            ad.guardarAlumno(cristian);
            List<Alumno> lista1 = ad.obtenerAlumnos();
            for (Alumno a : lista1) {
                System.out.println(a);
            }
            System.out.println("------------------------------------------------");
            ad.buscarAlumno(castor.getId_alumno());
            ad.buscarAlumno(cristian.getId_alumno());
            ad.buscarAlumno(federico.getId_alumno());
            cristian.setNombre("Carlos");
            cristian.setApellido("Olivera");
            cristian.setEstado(false);
            ad.actualizarAlumno(cristian);
            cristian.toString();
            ad.buscarAlumno(cristian.getId_alumno());
            ad.borrarAlumnoLogico(federico.getId_alumno());
            ad.borrarAlumnoFisico(castor.getId_alumno());
            List<Alumno> lista2 = ad.obtenerAlumnos();
            for (Alumno a : lista2) {
                System.out.println(a);
            }
            System.out.println("------------------------------------------------");
            ad.guardarAlumno(castor);
            md.guardarMateria(eda);
            md.guardarMateria(web1);
            md.guardarMateria(portugues);
            List<Materia> lista3 = md.obtenerMaterias();
            for (Materia m : lista3) {
                System.out.println(m);
            }
            System.out.println("------------------------------------------------");
            md.buscarMateria(portugues.getId_materia());
            md.buscarMateria(web1.getId_materia());
            md.buscarMateria(eda.getId_materia());
            web1.setNombre_materia("Web 2");
            web1.setAño(2);
            web1.setEstado(false);
            md.actualizarMateria(web1);
            md.borrarMateriaLogico(eda.getId_materia());
            md.borrarMateriaFisico(portugues.getId_materia());
            List<Materia> lista4 = md.obtenerMaterias();
            for (Materia m : lista4) {
                System.out.println(m);
            }
            System.out.println("------------------------------------------------");
            md.guardarMateria(portugues);
            Cursada cursada1 = new Cursada(castor.getId_alumno(), portugues.getId_materia(), 5);
            Cursada cursada2 = new Cursada(cristian.getId_alumno(), portugues.getId_materia(), 7);
            Cursada cursada3 = new Cursada(federico.getId_alumno(), portugues.getId_materia(), 6);
            Cursada cursada4 = new Cursada(castor.getId_alumno(), web1.getId_materia(), 6);
            Cursada cursada5 = new Cursada(cristian.getId_alumno(), web1.getId_materia(), 6);
            Cursada cursada6 = new Cursada(castor.getId_alumno(), eda.getId_materia(), 6);
            Cursada cursada7 = new Cursada(federico.getId_alumno(), web1.getId_materia(), 6);
            cd.guardarCursada(cursada1);
            cd.guardarCursada(cursada2);
            cd.guardarCursada(cursada3);
            cd.guardarCursada(cursada4);
            cd.guardarCursada(cursada5);
            cd.guardarCursada(cursada6);
            cd.guardarCursada(cursada7);
            List<Cursada> lista5 = cd.obtenerCursadas();
            for (Cursada c : lista5) {
                System.out.println(c);
            }
              System.out.println("------------------------------------------------");
            List<Cursada> lista7 = cd.obtenerCursadasXAlumno(castor.getId_alumno());
            for (Cursada c : lista7) {
                System.out.println(c);
            }
            System.out.println("------------------------------------------------");
            List<Materia> lista6 = cd.obtenerMateriasCursadas(federico.getId_alumno());
            for (Materia m : lista6) {
                System.out.println(m);
            }
            System.out.println("------------------------------------------------");
            List<Materia> lista8 = cd.obtenerMateriasNOCursadas(cristian.getId_alumno());
            for (Materia m : lista8) {
                System.out.println(m);
            }
            System.out.println("------------------------------------------------");
            cd.borrarCursadaDeUnaMateriaDeunAlumno(cristian.getId_alumno(), portugues.getId_materia());
            cd.actualizarNotaCursada(federico.getId_alumno(), web1.getId_materia(), 9);
        } catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(null, "Error al cargar los drivers: " + cnf.getMessage());
        }
    }
}
