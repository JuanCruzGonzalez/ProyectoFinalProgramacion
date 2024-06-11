/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ProveedorJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class ProveedorModelo {
    ProveedorJpaController proveedorJpa = new ProveedorJpaController();
    
    public void crearProveedor(Proveedor proveedor) {
        proveedorJpa.create(proveedor);
    }

    public void eliminarProveedor(int id) {
        try{
            proveedorJpa.destroy(id);
        }catch (NonexistentEntityException ex){
            Logger.getLogger(ProveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarProveedor(Proveedor proveedor) {
        try {
            proveedorJpa.edit(proveedor);
        } catch (Exception ex) {
            Logger.getLogger(ProveedorModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Proveedor traerProveedor(int id) {
        return proveedorJpa.findProveedor(id);
    }
    
    public ArrayList<Proveedor> traerListaProveedores(){
        List<Proveedor> proveedores = proveedorJpa.findProveedorEntities();
        ArrayList<Proveedor> arrayProveedores = new ArrayList<Proveedor>(proveedores);
        return arrayProveedores;
    }
}
