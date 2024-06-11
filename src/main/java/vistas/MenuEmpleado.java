/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import modelos.EmpleadoModelo;
import static vistas.Menu.sc;

/**
 *
 * @author juanc
 */
public class MenuEmpleado {
    final String SEPARADOR = "---------------------------------------------------------";
    EmpleadoModelo empleadoModelo = new EmpleadoModelo();    
    public void menuEmpleado(){
        System.out.println(SEPARADOR);
        System.out.println("Opciones Empleado");
        System.out.println(SEPARADOR);
        System.out.println("1- Ingresar un empleado");
        System.out.println("2- Modificar los datos de un empleado");
        System.out.println("3- Mostrar todos los empleados");
        System.out.println("4- Eliminar un empleado");
        System.out.println("5- Volver");
        System.out.println(SEPARADOR);
        System.out.println("Seleccione una opcion");
        int entrada2 = sc.nextInt();
        sc.nextLine();
        switch (entrada2) {
            case 1:
                empleadoModelo.crearEmpleado();
                break;
            case 2:
                empleadoModelo.mostrarTodosEmpleados();
                empleadoModelo.editarEmpleado();
                break;
            case 3:
                empleadoModelo.mostrarTodosEmpleados();
                break;
            case 4:
                empleadoModelo.eliminarEmpleado();
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
