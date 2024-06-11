package entidades;

import entidades.Factura;
import entidades.TarjetaDeCredito;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-11T19:25:54", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Persona_ {

    public static volatile ListAttribute<Cliente, TarjetaDeCredito> tarjetasDeCredito;
    public static volatile ListAttribute<Cliente, Factura> facturas;

}