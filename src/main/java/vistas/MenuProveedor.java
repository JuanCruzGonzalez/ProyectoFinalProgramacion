/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import modelos.ProveedorModelo;
import static vistas.Menu.sc;

/**
 *
 * @author juanc
 */
public class MenuProveedor {    
    final String SEPARADOR = "---------------------------------------------------------";
    ProveedorModelo proveedorModelo = new ProveedorModelo();
    public void menuProveedor(){
        System.out.println(SEPARADOR);
        System.out.println("Opciones Proveedor");
        System.out.println(SEPARADOR);
        System.out.println("1- Ingresar un proveedor");
        System.out.println("2- Modificar los datos de un proveedor");
        System.out.println("3- Mostrar todos los proveedores");
        System.out.println("4- Eliminar un proveedor");
        System.out.println("5- Volver");
        System.out.println(SEPARADOR);
        System.out.println("Seleccione una opcion");
        int entrada3 = sc.nextInt();
        sc.nextLine();
        switch (entrada3) {
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
