package entidades;

import entidades.Cliente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-10T20:57:11", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TarjetaDeCredito.class)
public class TarjetaDeCredito_ { 

    public static volatile SingularAttribute<TarjetaDeCredito, Date> fechaIngreso;
    public static volatile SingularAttribute<TarjetaDeCredito, Integer> id;
    public static volatile SingularAttribute<TarjetaDeCredito, Double> limite;
    public static volatile ListAttribute<TarjetaDeCredito, Cliente> clientes;

}