/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import entidades.Cliente;
import entidades.TarjetaDeCredito;
import java.sql.SQLException;
import java.util.Scanner;
import modelos.ClienteModelo;
import modelos.EmpleadoModelo;
import modelos.FacturaModelo;
import modelos.ProveedorModelo;
import modelos.TarjetaDeCreditoModelo;

/**
 *
 * @author juanc
 */
public class Menu {
    final String SEPARADOR = "---------------------------------------------------------";
    static Scanner sc = new Scanner(System.in);
    public void menu(){
        boolean salida = false;
        while (!salida) {
            System.out.println(SEPARADOR);
            System.out.println("Seleccione una opcion");
            System.out.println("1- Cliente");
            System.out.println("2- Empleado");
            System.out.println("3- Proveedor");
            System.out.println("4- Factura");
            System.out.println("5- Tarjeta de Credito");
            System.out.println("6- Salida.");
            System.out.println(SEPARADOR);
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    MenuCliente menuCliente = new MenuCliente();
                    menuCliente.menuCliente();
                    break;
                case 2:
                    MenuEmpleado menuEmpleado = new MenuEmpleado();
                    menuEmpleado.menuEmpleado();
                    break;
                case 3:
                    MenuProveedor menuProveedor = new MenuProveedor();
                    menuProveedor.menuProveedor();
                    break;
                case 4:
                    MenuFactura menuFactura = new MenuFactura();
                    menuFactura.menuFactura();
                    break;
                case 5:
                    MenuTarjeta menuTarjeta = new MenuTarjeta();
                    menuTarjeta.menuTarjeta();
                    break;
                case 6:
                    salida = true;
                    break;
                default:
                    System.out.println("Ingrese un número válido.");
                    System.out.println(SEPARADOR);            
                    break;
            }
        }
    }
}
