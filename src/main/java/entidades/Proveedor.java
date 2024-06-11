/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Proveedor extends Persona implements EstadoCuenta, Serializable {
    private String nombreComercial;

    public Proveedor() {}

    public Proveedor(String nombreComercial, int dni, String nombre, String apellido, long cuit) {
        super(dni, nombre, apellido, cuit);
        this.nombreComercial = nombreComercial;
    }

    @Override
    public boolean problemaVerazCodeme() {
        // Implementación del método
        return false;
    }

    // Getters and setters

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "nombreComercial=" + nombreComercial + '}';
    }
    
}
