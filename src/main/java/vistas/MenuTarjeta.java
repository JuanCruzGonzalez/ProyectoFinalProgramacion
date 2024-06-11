/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import entidades.TarjetaDeCredito;
import modelos.TarjetaDeCreditoModelo;
import static vistas.Menu.sc;

/**
 *
 * @author juanc
 */
public class MenuTarjeta {
    final String SEPARADOR = "---------------------------------------------------------";
    TarjetaDeCreditoModelo tarjetaModelo = new TarjetaDeCreditoModelo();
    
    public void menuTarjeta(){
        System.out.println(SEPARADOR);
        System.out.println("Opciones Tarjeta de Credito");
        System.out.println(SEPARADOR);
        System.out.println("1- Ingresar una tarjeta de credito");
        System.out.println("2- Modificar los datos de una tarjeta de credito");
        System.out.println("3- Mostrar todas las tarjetas de credito");
        System.out.println("4- Eliminar una tarjeta de credito");
        System.out.println("5- Volver");
        System.out.println(SEPARADOR);
        System.out.println("Seleccione una opcion");
        int entrada5 = sc.nextInt();
        sc.nextLine();
        switch (entrada5) {
            case 1:
                tarjetaModelo.crearTarjetaDeCredito();
                break;
            case 2:
                tarjetaModelo.mostrarTodoasTarjetasDeCredito();
                System.out.println("Ingrese el id");
                int idTareja = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingrese el nuevo limite");
                double nuevoLimite = sc.nextDouble();
                sc.nextLine();
                TarjetaDeCredito tarjeta = tarjetaModelo.traerTarjetaDeCredito(idTareja);
                tarjeta.setLimite(nuevoLimite);
                tarjetaModelo.editarTarjetaDeCredito(tarjeta);
                break;
            case 3:
                tarjetaModelo.mostrarTodoasTarjetasDeCredito();
                break;
            case 4:
                tarjetaModelo.eliminarTarjetaDeCredito();
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
