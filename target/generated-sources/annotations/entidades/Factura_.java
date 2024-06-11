package entidades;

import entidades.Cliente;
import entidades.Empleado;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-10T20:57:11", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Integer> nroFactura;
    public static volatile SingularAttribute<Factura, Double> monto;
    public static volatile SingularAttribute<Factura, Empleado> emp;
    public static volatile SingularAttribute<Factura, Cliente> clie;

}