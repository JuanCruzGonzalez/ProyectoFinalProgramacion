/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Cliente extends Persona implements EstadoCuenta, Serializable {
    @ManyToMany
    @JoinTable(
        name = "cliente_tarjeta", 
        joinColumns = @JoinColumn(name = "cliente_id"), 
        inverseJoinColumns = @JoinColumn(name = "tarjeta_id")
    )
    private ArrayList<TarjetaDeCredito> tarjetasDeCredito = new ArrayList<TarjetaDeCredito>();

    @OneToMany(mappedBy = "clie")
    private ArrayList<Factura> facturas = new ArrayList<Factura>();

    public Cliente() {}

    public Cliente(int dni, String nombre, String apellido, long cuit) {
        super(dni, nombre, apellido, cuit);
    }

    @Override
    public boolean problemaVerazCodeme() {
        // Implementación del método
        return false;
    }

    // Getters and setters

    public ArrayList<TarjetaDeCredito> getTarjetasDeCredito() {
        return tarjetasDeCredito;
    }

    public void setTarjetasDeCredito(ArrayList<TarjetaDeCredito> tarjetasDeCredito) {
        this.tarjetasDeCredito = tarjetasDeCredito;
    }
    
    public void addTarjetaDeCredito(TarjetaDeCredito tarjetaDeCredito) {
        this.tarjetasDeCredito.add(tarjetaDeCredito);
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
}

