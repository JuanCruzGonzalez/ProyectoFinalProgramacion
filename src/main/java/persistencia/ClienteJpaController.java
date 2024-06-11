/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.TarjetaDeCredito;
import java.util.ArrayList;
import entidades.Factura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("ClientePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getFacturas() == null) {
            cliente.setFacturas(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            ArrayList<Factura> attachedFacturas = new ArrayList<Factura>();
            for (Factura facturasFacturaToAttach : cliente.getFacturas()) {
                facturasFacturaToAttach = em.getReference(facturasFacturaToAttach.getClass(), facturasFacturaToAttach.getNroFactura());
                attachedFacturas.add(facturasFacturaToAttach);
            }
            cliente.setFacturas(attachedFacturas);

            em.persist(cliente);

            for (Factura facturasFactura : cliente.getFacturas()) {
                Cliente oldClieOfFacturasFactura = facturasFactura.getClie();
                facturasFactura.setClie(cliente);
                facturasFactura = em.merge(facturasFactura);
                if (oldClieOfFacturasFactura != null) {
                    oldClieOfFacturasFactura.getFacturas().remove(facturasFactura);
                    oldClieOfFacturasFactura = em.merge(oldClieOfFacturasFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void addTarjetaToCliente(int clienteId, TarjetaDeCredito tarjeta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, clienteId);
            if (cliente == null) {
                throw new IllegalArgumentException("Cliente no encontrado");
            }

            if (cliente.getTarjetasDeCredito() == null) {
                cliente.setTarjetasDeCredito(new ArrayList<TarjetaDeCredito>());
            }

            TarjetaDeCredito tarjetaAttached = em.getReference(TarjetaDeCredito.class, tarjeta.getId());
            cliente.getTarjetasDeCredito().add(tarjetaAttached);
            tarjetaAttached.getClientes().add(cliente);

            em.merge(cliente);
            em.merge(tarjetaAttached);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getDni());
            ArrayList<TarjetaDeCredito> tarjetasDeCreditoOld = persistentCliente.getTarjetasDeCredito();
            ArrayList<TarjetaDeCredito> tarjetasDeCreditoNew = cliente.getTarjetasDeCredito();
            ArrayList<Factura> facturasOld = persistentCliente.getFacturas();
            ArrayList<Factura> facturasNew = cliente.getFacturas();
            ArrayList<TarjetaDeCredito> attachedTarjetasDeCreditoNew = new ArrayList<TarjetaDeCredito>();
            for (TarjetaDeCredito tarjetasDeCreditoNewTarjetaDeCreditoToAttach : tarjetasDeCreditoNew) {
                tarjetasDeCreditoNewTarjetaDeCreditoToAttach = em.getReference(tarjetasDeCreditoNewTarjetaDeCreditoToAttach.getClass(), tarjetasDeCreditoNewTarjetaDeCreditoToAttach.getId());
                attachedTarjetasDeCreditoNew.add(tarjetasDeCreditoNewTarjetaDeCreditoToAttach);
            }
            tarjetasDeCreditoNew = attachedTarjetasDeCreditoNew;
            cliente.setTarjetasDeCredito(tarjetasDeCreditoNew);
            ArrayList<Factura> attachedFacturasNew = new ArrayList<Factura>();
            for (Factura facturasNewFacturaToAttach : facturasNew) {
                facturasNewFacturaToAttach = em.getReference(facturasNewFacturaToAttach.getClass(), facturasNewFacturaToAttach.getNroFactura());
                attachedFacturasNew.add(facturasNewFacturaToAttach);
            }
            facturasNew = attachedFacturasNew;
            cliente.setFacturas(facturasNew);
            cliente = em.merge(cliente);
            for (TarjetaDeCredito tarjetasDeCreditoOldTarjetaDeCredito : tarjetasDeCreditoOld) {
                if (!tarjetasDeCreditoNew.contains(tarjetasDeCreditoOldTarjetaDeCredito)) {
                    tarjetasDeCreditoOldTarjetaDeCredito.getClientes().remove(cliente);
                    tarjetasDeCreditoOldTarjetaDeCredito = em.merge(tarjetasDeCreditoOldTarjetaDeCredito);
                }
            }
            for (TarjetaDeCredito tarjetasDeCreditoNewTarjetaDeCredito : tarjetasDeCreditoNew) {
                if (!tarjetasDeCreditoOld.contains(tarjetasDeCreditoNewTarjetaDeCredito)) {
                    tarjetasDeCreditoNewTarjetaDeCredito.getClientes().add(cliente);
                    tarjetasDeCreditoNewTarjetaDeCredito = em.merge(tarjetasDeCreditoNewTarjetaDeCredito);
                }
            }
            for (Factura facturasOldFactura : facturasOld) {
                if (!facturasNew.contains(facturasOldFactura)) {
                    facturasOldFactura.setClie(null);
                    facturasOldFactura = em.merge(facturasOldFactura);
                }
            }
            for (Factura facturasNewFactura : facturasNew) {
                if (!facturasOld.contains(facturasNewFactura)) {
                    Cliente oldClieOfFacturasNewFactura = facturasNewFactura.getClie();
                    facturasNewFactura.setClie(cliente);
                    facturasNewFactura = em.merge(facturasNewFactura);
                    if (oldClieOfFacturasNewFactura != null && !oldClieOfFacturasNewFactura.equals(cliente)) {
                        oldClieOfFacturasNewFactura.getFacturas().remove(facturasNewFactura);
                        oldClieOfFacturasNewFactura = em.merge(oldClieOfFacturasNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getDni();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            ArrayList<TarjetaDeCredito> tarjetasDeCredito = cliente.getTarjetasDeCredito();
            for (TarjetaDeCredito tarjetasDeCreditoTarjetaDeCredito : tarjetasDeCredito) {
                tarjetasDeCreditoTarjetaDeCredito.getClientes().remove(cliente);
                tarjetasDeCreditoTarjetaDeCredito = em.merge(tarjetasDeCreditoTarjetaDeCredito);
            }
            ArrayList<Factura> facturas = cliente.getFacturas();
            for (Factura facturasFactura : facturas) {
                facturasFactura.setClie(null);
                facturasFactura = em.merge(facturasFactura);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
