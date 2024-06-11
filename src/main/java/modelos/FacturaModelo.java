/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Factura;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.FacturaJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class FacturaModelo {
    FacturaJpaController facturaJpa = new FacturaJpaController();
    
    public void crearFactura(Factura factura) {
        facturaJpa.create(factura);
    }

    public void eliminarFactura(int id) {
        try{
            facturaJpa.destroy(id);
        }catch (NonexistentEntityException ex){
            Logger.getLogger(FacturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarFactura(Factura factura) {
        try {
            facturaJpa.edit(factura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Factura traerFactura(int id) {
        return facturaJpa.findFactura(id);
    }
    
    public ArrayList<Factura> traerListaFacturas(){
        List<Factura> facturas = facturaJpa.findFacturaEntities();
        ArrayList<Factura> arrayFacturas = new ArrayList<Factura>(facturas);
        return arrayFacturas;
    }
}
