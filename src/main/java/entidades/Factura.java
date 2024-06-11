package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nroFactura;

    @ManyToOne
    @JoinColumn(name = "empleado_id") // Foreign key in Factura table
    private Empleado emp;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // Foreign key in Factura table
    private Cliente clie;

    private Date fecha;
    private double monto;

    public Factura() {}

    public Factura(Empleado emp, Cliente clie, int nroFactura, Date fecha, double monto) {
        this.emp = emp;
        this.clie = clie;
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.monto = monto;
    }

    // Getters and setters

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public Cliente getClie() {
        return clie;
    }

    public void setClie(Cliente clie) {
        this.clie = clie;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Factura{" + "nroFactura=" + nroFactura + ", emp=" + emp + ", clie=" + clie + ", fecha=" + fecha + ", monto=" + monto + '}';
    }
    
}
