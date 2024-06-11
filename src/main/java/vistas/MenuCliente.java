/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import modelos.ClienteModelo;
import static vistas.Menu.sc;

/**
 *
 * @author juanc
 */
public class MenuCliente {
    final String SEPARADOR = "---------------------------------------------------------";
    ClienteModelo clienteModelo = new ClienteModelo();
    public void menuCliente(){
        System.out.println(SEPARADOR);
        System.out.println("Opciones cliente");
        System.out.println(SEPARADOR);
        System.out.println("1- Ingresar un cliente");
        System.out.println("2- Modificar los datos de un cliente");
        System.out.println("3- Mostrar todos los clientes");
        System.out.println("4- Asignar Tarjeta de Credito a un Cliente");
        System.out.println("5- Mostrar Tarjetas de Credito de un Cliente");
        System.out.println("6- Eliminar un cliente");
        System.out.println("7- Volver");
        System.out.println(SEPARADOR);
        System.out.println("Seleccione una opcion");
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
                clienteModelo.agregarTarjetaCliente();
                break;
            case 5:
                System.out.println("Tarjetas del Cliente");
                clienteModelo.mostrarTarjetasDeCreditoCliente();
                break;
            case 6:
                clienteModelo.eliminarCliente();
                break;
            case 7:
                break;
            default:
                System.out.println("Ingrese un numero valido");
                System.out.println(SEPARADOR);
                break;
        }
    }
}
