/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import modelos.FacturaModelo;
import static vistas.Menu.sc;

/**
 *
 * @author juanc
 */
public class MenuFactura {
    final String SEPARADOR = "---------------------------------------------------------";
    FacturaModelo facturaModelo = new FacturaModelo();
    public void menuFactura(){
        System.out.println(SEPARADOR);
        System.out.println("Opciones Factura");
        System.out.println(SEPARADOR);
        System.out.println("1- Ingresar una factura");
        System.out.println("2- Modificar los datos de una factura");
        System.out.println("3- Mostrar todas las facturas");
        System.out.println("4- Eliminar una factura");
        System.out.println(SEPARADOR);
        System.out.println("Seleccione una opcion");
        int entrada4 = sc.nextInt();
        sc.nextLine();
        switch (entrada4) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Ingrese un numero valido");
                System.out.println(SEPARADOR);
                break;
        }
    }
}
