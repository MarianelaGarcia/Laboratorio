/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Grupo2
 */
public class Cursada {
    private int id_cursada=-1;
    private int id_alumno;
    private int id_materia;
    private int nota;
    
    public Cursada(int id_alumno, int id_materia, int nota) {
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }
        
    public Cursada(int id_cursada, int id_alumno, int id_materia, int nota) {
        this.id_cursada = id_cursada;
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public Cursada(){
    }

    public int getId_cursada() {
        return id_cursada;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public int getId_materia() {
        return id_materia;
    }

    public int getNota() {
        return nota;
    }

    public void setId_cursada(int id_cursada) {
        this.id_cursada = id_cursada;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada: " + "id_alumno= " + id_alumno + ", id_materia= " + id_materia + ", nota= " + nota;
    }
    
    
}
