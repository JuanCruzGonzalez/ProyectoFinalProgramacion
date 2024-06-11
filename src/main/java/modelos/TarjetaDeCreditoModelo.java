/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.TarjetaDeCredito;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modelos.ClienteModelo.sc;
import persistencia.ClienteJpaController;
import persistencia.TarjetaDeCreditoJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class TarjetaDeCreditoModelo {
    TarjetaDeCreditoJpaController tarjetaDeCreditoJpa = new TarjetaDeCreditoJpaController();
    TarjetaDeCredito tarjeta = new TarjetaDeCredito();
    public void crearTarjetaDeCredito() {
        System.out.println("Ingrese el limite");
        double limiteTarjeta = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese la fecha de ingreso de la tarjeta de credito");
        System.out.println("Dia");
        int diaIngreso = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Mes");
        int mesIngreso = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Año");
        int anioIngreso = sc.nextInt();
        sc.nextLine();
        
        tarjeta = new TarjetaDeCredito(new Date(anioIngreso, mesIngreso, diaIngreso), limiteTarjeta);
        
        System.out.println("¿Desea asignar la tarjeta a un cliente?");
        String opcion = sc.toString();
        sc.nextLine();
        try{
            tarjetaDeCreditoJpa.create(tarjeta);
            System.out.println("Tarjeta creada exitosamente");
            if(opcion.contains("S") || opcion.contains("s")){
                System.out.println("Ingrese el id del cliente");
                int idCliente = sc.nextInt();
                sc.nextLine();
                ClienteJpaController clienteJpa = new ClienteJpaController();
                clienteJpa.addTarjetaToCliente(idCliente, tarjeta);
                System.out.println("Tarjeta asignada correctamente");
            }else{
                System.out.println("Elijio no");
            }
        }catch(Exception e){
            System.out.println("Error al crear la tarjeta de credito");
        }
    }

    public void eliminarTarjetaDeCredito() {
        try{
            System.out.println("Ingrese el id de la tarjeta a eliminar");
            int idTarejta = sc.nextInt();
            tarjetaDeCreditoJpa.destroy(idTarejta);
            System.out.println("Tarjeta eliminada existosamente");
        }catch (NonexistentEntityException ex){
            System.out.println("Error al eliminar la tarjeta");            
        }
    }

    public void editarTarjetaDeCredito(TarjetaDeCredito tarjetaDeCredito) {
        try {
            tarjetaDeCreditoJpa.edit(tarjetaDeCredito);
        } catch (Exception ex) {
            Logger.getLogger(TarjetaDeCreditoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TarjetaDeCredito traerTarjetaDeCredito(int id) {
        return tarjetaDeCreditoJpa.findTarjetaDeCredito(id);
    }
    
    public void mostrarTodoasTarjetasDeCredito(){
        for(TarjetaDeCredito tarjetaMostar : traerListaTarjetasDeCredito()){
            System.out.println(tarjetaMostar.toString());
        }
    }
    
    public ArrayList<TarjetaDeCredito> traerListaTarjetasDeCredito(){
        List<TarjetaDeCredito> tarjetasDeCredito = tarjetaDeCreditoJpa.findTarjetaDeCreditoEntities();
        ArrayList<TarjetaDeCredito> arrayTarjetasDeCredito = new ArrayList<TarjetaDeCredito>(tarjetasDeCredito);
        return arrayTarjetasDeCredito;
    }
}
