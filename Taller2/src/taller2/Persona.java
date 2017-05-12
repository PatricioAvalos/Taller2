/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller2;

/**
 *
 * @author favya
 */
public class Persona {
    
    private String nombre;
    private int edad;
    private String ocupacion;
    private int cantidadFamilia;
    private String nombreC;
    private String censista;

    public Persona(String nombre, int edad, String nombreC, String ocupacion, int cantidadFamilia, String censista) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.cantidadFamilia = cantidadFamilia;
        this.nombreC = nombreC;
        this.censista = censista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getCantidadFamilia() {
        return cantidadFamilia;
    }

    public void setCantidadFamilia(int cantidadFamilia) {
        this.cantidadFamilia = cantidadFamilia;
    }

    public String getComuna() {
        return nombreC;
    }

    public void setComuna(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getCensista() {
        return censista;
    }

    public void setCensista(String censista) {
        this.censista = censista;
    }
}
