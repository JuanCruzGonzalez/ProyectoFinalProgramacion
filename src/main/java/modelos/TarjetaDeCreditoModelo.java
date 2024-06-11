/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.TarjetaDeCredito;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.TarjetaDeCreditoJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class TarjetaDeCreditoModelo {
    TarjetaDeCreditoJpaController tarjetaDeCreditoJpa = new TarjetaDeCreditoJpaController();
    
    public void crearProveedor(TarjetaDeCredito tarjetaDeCredito) {
        tarjetaDeCreditoJpa.create(tarjetaDeCredito);
    }

    public void eliminarProveedor(int id) {
        try{
            tarjetaDeCreditoJpa.destroy(id);
        }catch (NonexistentEntityException ex){
            Logger.getLogger(TarjetaDeCreditoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarProveedor(TarjetaDeCredito tarjetaDeCredito) {
        try {
            tarjetaDeCreditoJpa.edit(tarjetaDeCredito);
        } catch (Exception ex) {
            Logger.getLogger(TarjetaDeCreditoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TarjetaDeCredito traerProveedor(int id) {
        return tarjetaDeCreditoJpa.findTarjetaDeCredito(id);
    }
    
    public ArrayList<TarjetaDeCredito> traerListaProveedores(){
        List<TarjetaDeCredito> tarjetasDeCredito = tarjetaDeCreditoJpa.findTarjetaDeCreditoEntities();
        ArrayList<TarjetaDeCredito> arrayTarjetasDeCredito = new ArrayList<TarjetaDeCredito>(tarjetasDeCredito);
        return arrayTarjetasDeCredito;
    }
}
