/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Empleado extends Persona implements Serializable {
    private Date fechaIngreso;
    private int nroLegajo;

    @OneToMany(mappedBy = "emp")
    private List<Factura> facturas;

    public Empleado() {}

    public Empleado(int dni, String nombre, String apellido, long cuit, Date fechaIngreso, int nroLegajo) {
        super(dni, nombre, apellido, cuit);
        this.fechaIngreso = fechaIngreso;
        this.nroLegajo = nroLegajo;
    }

    // Getters and setters

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public String toString() {
        return "Empleado{" + "fechaIngreso=" + fechaIngreso + ", nroLegajo=" + nroLegajo + ", facturas=" + facturas + '}';
    }
    
}
