/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import entidades.Cliente;
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
        ClienteModelo clienteModelo = new ClienteModelo();
        EmpleadoModelo empleadoModelo = new EmpleadoModelo();
        ProveedorModelo proveedorModelo = new ProveedorModelo();
        FacturaModelo facturaModelo = new FacturaModelo();
        TarjetaDeCreditoModelo tarjetaModelo = new TarjetaDeCreditoModelo();
        boolean salida = false;
        while (!salida) {
            System.out.println(SEPARADOR);
            System.out.println("Banco");
            System.out.println(SEPARADOR);
            System.out.println("Opciones cliente");
            System.out.println("1- Ingresar un cliente");
            System.out.println("2- Modificar los datos de un cliente");
            System.out.println("3- Mostrar todos los clientes");
            System.out.println("4- Eliminar un cliente");
            System.out.println(SEPARADOR);            
            System.out.println("Opciones Empleado");
            System.out.println("5- Ingresar un empleado");
            System.out.println("6- Modificar los datos de un empleado");
            System.out.println("7- Mostrar todos los empleados");
            System.out.println("8- Eliminar un empleado");
            System.out.println(SEPARADOR);
            System.out.println("Opciones Proveedor");
            System.out.println("9- Ingresar un proveedor");
            System.out.println("10- Modificar los datos de un proveedor");
            System.out.println("11- Mostrar todos los proveedores");
            System.out.println("12- Eliminar un proveedor");
            System.out.println(SEPARADOR);
            System.out.println("Opciones Factura");
            System.out.println("13- Ingresar una factura");
            System.out.println("14- Modificar los datos de una factura");
            System.out.println("15- Mostrar todas las facturas");
            System.out.println("16- Eliminar una factura");
            System.out.println(SEPARADOR);
            System.out.println("Opciones Tarjeta de Credito");
            System.out.println("17- Ingresar una tarjeta de credito");
            System.out.println("18- Modificar los datos de una tarjeta de credito");
            System.out.println("19- Mostrar todas las tarjetas de credito");
            System.out.println("20- Eliminar una tarjeta de credito");
            System.out.println("21- Salida.");
            System.out.println(SEPARADOR);
            int entrada = sc.nextInt();
            sc.nextLine();
            switch (entrada) {
                case 1:
                    clienteModelo.crearCliente();
                    break;
                case 2:
                    clienteModelo.mostrarTodosClientes();
                    clienteModelo.editarCliente();
                    break;
                case 3:
                    clienteModelo.mostrarTodosClientes();
                    break;
                case 4:
                    clienteModelo.eliminarCliente();
                    break;
                case 5:
                    empleadoModelo.crearEmpleado();
                    break;
                case 6:
                    empleadoModelo.mostrarTodosEmpleados();
                    empleadoModelo.editarEmpleado();
                    break;
                case 7:
                    empleadoModelo.mostrarTodosEmpleados();
                    break;
                case 8:
                    empleadoModelo.eliminarEmpleado();
                    break;
                case 9:
                    salida = true;
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    salida = true;
                    break;
                default:
                    System.out.println("Ingrese un número válido.");
                    break;
            }
        }
    }
}
