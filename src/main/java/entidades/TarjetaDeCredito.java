package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;

@Entity
public class TarjetaDeCredito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date fechaIngreso;
    private double limite;
    
    @ManyToMany(mappedBy = "tarjetasDeCredito")
    private ArrayList<Cliente> clientes;

    public TarjetaDeCredito() {}

    public TarjetaDeCredito(Date fechaIngreso, double limite) {
        this.fechaIngreso = fechaIngreso;
        this.limite = limite;
    }
    
    // Getters and setters

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {    
        this.clientes = clientes;
    }
    
    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "Tarjeta      "+ "ID= " + id + " \nFecha de Ingreso = " + fechaIngreso + ", Limite = " + limite;
    }
    
}
