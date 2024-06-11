/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.EmpleadoJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class EmpleadoModelo {
    EmpleadoJpaController clienteJpa = new EmpleadoJpaController();
    
    public void crearEmpleado(Empleado empleado) {
        clienteJpa.create(empleado);
    }

    public void eliminarEmpleado(int id) {
        try{
            clienteJpa.destroy(id);
        }catch (NonexistentEntityException ex){
            Logger.getLogger(EmpleadoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEmpleado(Empleado empleado) {
        try {
            clienteJpa.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado traerEmpleados(int id) {
        return clienteJpa.findEmpleado(id);
    }
    
    public ArrayList<Empleado> traerListaAlumnos(){
        List<Empleado> empleados = clienteJpa.findEmpleadoEntities();
        ArrayList<Empleado> arrayEmpleados = new ArrayList<Empleado>(empleados);
        return arrayEmpleados;
    }
}
