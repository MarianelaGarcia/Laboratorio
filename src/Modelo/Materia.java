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
public class Materia {

private int id_materia=-1;
private String nombre_materia;
private int año;
private boolean estado;

    public Materia(String nombre_materia, int año, boolean estado) {
        this.nombre_materia = nombre_materia;
        this.año = año;
        this.estado = estado;
    }
    
    public Materia(int id_materia,String nombre_materia, int año, boolean estado){
        this.id_materia = id_materia;
        this.nombre_materia = nombre_materia;
        this.año = año;
        this.estado = estado;
    }
    
    public Materia(){
    }
    
    @Override
    public String toString(){
        return id_materia+" - "+nombre_materia;
    }

    public int getId_materia() {
        return id_materia;
    }

    public String getNombre_Materia() {
        return nombre_materia;
    }
    
    public int getAño() {
        return año;
    }

    public boolean isEstado() {
        return estado;
    }
    
    public void setId_Materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
    
    public void setAño(int año) {
        this.año = año;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}