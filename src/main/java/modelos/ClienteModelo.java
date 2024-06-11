/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.PersistenceException;
import persistencia.ClienteJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class ClienteModelo {
    ClienteJpaController clienteJpa = new ClienteJpaController();
    Cliente cliente;
    static Scanner sc = new Scanner(System.in);
    public void crearCliente() {
        System.out.println("Ingrese el nombre");
        String nombreCliente = sc.nextLine();
        System.out.println("Ingrese el apellido");
        String apellidoCliente = sc.nextLine();
        System.out.println("Ingrese el dni");
        int dniCliente = sc.nextInt();
        sc.nextLine(); // Limpiar el búfer de entrada
        System.out.println("Ingrese el cuit");
        int cuitCliente = sc.nextInt();
        sc.nextLine(); // Limpiar el búfer de entrada
        cliente = new Cliente(dniCliente, nombreCliente, apellidoCliente, cuitCliente);
        try{
           clienteJpa.create(cliente);
            System.out.println("Cliente creado existosamente");
        }catch(PersistenceException e){
            System.out.println("Error al crear el cliente");
        }
    }

    public void eliminarCliente() {
        try{
            System.out.println("Ingrese el dni");
            int dniCliente = sc.nextInt();
            clienteJpa.destroy(dniCliente);
            System.out.println("Cliente eliminado existosamente");
        }catch (NonexistentEntityException ex){
            System.out.println("Error al eliminar el cliente");
        }
    }

    public void editarCliente() {
        try {
            System.out.println("Ingrese el id del cliente a modificar");
            int dniClienteEditar = sc.nextInt();
            sc.nextLine();
            System.out.println("¿Que desea modificar?\n1-Nombre\n2-Apellido\n3-Cuit");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre");
                    String nombreClienteNuevo = sc.nextLine();
                    cliente = traerCliente(dniClienteEditar);
                    cliente.setNombre(nombreClienteNuevo);
                    clienteJpa.edit(cliente);
                    break;
                case 2:
                    System.out.println("Ingrese el Apelldo");
                    String apellidoClienteNuevo = sc.nextLine();
                    cliente = traerCliente(dniClienteEditar);
                    cliente.setApellido(apellidoClienteNuevo);
                    clienteJpa.edit(cliente);                            
                    break;
                case 3:
                    System.out.println("Ingrese el Cuit");
                    int cuitClienteNuevo = sc.nextInt();
                    sc.nextLine();
                    cliente = traerCliente(dniClienteEditar);
                    cliente.setCuit(cuitClienteNuevo);
                    clienteJpa.edit(cliente);                           
                    break;
            }
            System.out.println("Cliente editado existosamente");
        } catch (Exception ex) {
            System.out.println("Error al editar el cliente");
        }
    }

    public Cliente traerCliente(int id) {
        try{
            return clienteJpa.findCliente(id);
        }catch(Exception e){
            System.out.println("Error al buscar el cliente");
            return null;
        }
    }
    
    public void mostrarTodosClientes(){
        for(Cliente clienteMostar : traerListaClientes()){
            System.out.println(clienteMostar.toString());
        }
    }
    
    public ArrayList<Cliente> traerListaClientes(){
        List<Cliente> clientes = clienteJpa.findClienteEntities();
        ArrayList<Cliente> arrayClientes = new ArrayList<>(clientes);
        return arrayClientes;
    }
}
