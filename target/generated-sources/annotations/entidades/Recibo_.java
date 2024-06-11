package entidades;

import entidades.Proveedor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-09T19:11:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Recibo.class)
public class Recibo_ extends Comprobante_ {

    public static volatile SingularAttribute<Recibo, Float> total;
    public static volatile SingularAttribute<Recibo, Proveedor> proveedor;
    public static volatile SingularAttribute<Recibo, String> detalle;

}