/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Empleado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import static modelos.ClienteModelo.sc;
import persistencia.EmpleadoJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class EmpleadoModelo {
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    Empleado empleado;
    public void crearEmpleado() {
        System.out.println("Ingrese el nombre");
        String nombreEmpleado = sc.nextLine();
        System.out.println("Ingrese el apellido");
        String apellidoEmpleado = sc.nextLine();
        System.out.println("Ingrese el dni");
        int dniEmpleado = sc.nextInt();
        sc.nextLine(); // Limpiar el búfer de entrada
        System.out.println("Ingrese el cuit");
        int cuitEmpleado = sc.nextInt();
        sc.nextLine(); // Limpiar el búfer de entrada
        System.out.println("Ingrese la fecha de ingreso del empleado");
        System.out.println("Dia");
        int diaIngreso = sc.nextInt();
        sc.nextLine();
        System.out.println("Mes");
        int mesIngreso = sc.nextInt();
        sc.nextLine();
        System.out.println("Año");
        int anioIngreso = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el numero de legajo");
        int legajoEmpleado = sc.nextInt();
        sc.nextLine();
        empleado = new Empleado(dniEmpleado, nombreEmpleado, apellidoEmpleado, cuitEmpleado, new Date(anioIngreso, mesIngreso, diaIngreso), legajoEmpleado);
        try{
           empleadoJpa.create(empleado);
            System.out.println("Empleado creado existosamente");
        }catch(PersistenceException e){
            System.out.println("Error al crear el empleado");
        }
    }

    public void eliminarEmpleado() {
        try{
            System.out.println("Ingrese el dni");
            int dniCliente = sc.nextInt();
            empleadoJpa.destroy(dniCliente);
            System.out.println("Empleado eliminado existosamente");
        }catch (NonexistentEntityException ex){
            System.out.println("Error al eliminar el empleado");            
        }
    }

    public void editarEmpleado() {
        try {
            System.out.println("Ingrese el id del empleado a modificar");
            int dniEmpleadoEditar = sc.nextInt();
            sc.nextLine();
            System.out.println("¿Que desea modificar?\n1-Nombre\n2-Apellido\n3-Cuit\n4-Fecha de ingreso\n5-Legajo");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre");
                    String nombreEmpleadoNuevo = sc.nextLine();
                    empleado = traerEmpleado(dniEmpleadoEditar);
                    empleado.setNombre(nombreEmpleadoNuevo);
                    empleadoJpa.edit(empleado);
                    break;
                case 2:
                    System.out.println("Ingrese el Apelldo");
                    String apellidoClienteNuevo = sc.nextLine();
                    empleado = traerEmpleado(dniEmpleadoEditar);
                    empleado.setApellido(apellidoClienteNuevo);
                    empleadoJpa.edit(empleado);                            
                    break;
                case 3:
                    System.out.println("Ingrese el Cuit");
                    int cuitEmpleadoNuevo = sc.nextInt();
                    sc.nextLine();
                    empleado = traerEmpleado(dniEmpleadoEditar);
                    empleado.setCuit(cuitEmpleadoNuevo);
                    empleadoJpa.edit(empleado);                           
                    break;
                case 4:
                    System.out.println("Ingrese la nueva fecha de ingreso");
                    System.out.println("Dia");
                    int diaIngreso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Mes");
                    int mesIngreso = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Año");
                    int anioIngreso = sc.nextInt();
                    sc.nextLine();
                    empleado = traerEmpleado(dniEmpleadoEditar);
                    empleado.setFechaIngreso(new Date(anioIngreso, mesIngreso, diaIngreso));
                    empleadoJpa.edit(empleado);
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo legajo");
                    int legajoEmpleado = sc.nextInt();
                    sc.nextLine();
                    empleado = traerEmpleado(dniEmpleadoEditar);
                    empleado.setNroLegajo(legajoEmpleado);
                    empleadoJpa.edit(empleado);
                    break;
            }
                System.out.println("Empleado editado existosamente");
            } catch (Exception ex) {
                System.out.println("Error al editar el cliente");
            }
    }

    public Empleado traerEmpleado(int id) {
        return empleadoJpa.findEmpleado(id);
    }
    
    public void mostrarTodosEmpleados(){
        for(Empleado empleadoMostar : traerListaEmpleados()){
            System.out.println(empleadoMostar.toString());
        }
    }
    
    public ArrayList<Empleado> traerListaEmpleados(){
        List<Empleado> empleados = empleadoJpa.findEmpleadoEntities();
        ArrayList<Empleado> arrayEmpleados = new ArrayList<>(empleados);
        return arrayEmpleados;
    }
}
