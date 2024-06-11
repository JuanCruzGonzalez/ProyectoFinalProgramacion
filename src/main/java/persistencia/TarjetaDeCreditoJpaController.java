/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cliente;
import entidades.TarjetaDeCredito;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author juanc
 */
public class TarjetaDeCreditoJpaController implements Serializable {

    public TarjetaDeCreditoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TarjetaDeCreditoJpaController() {
        emf = Persistence.createEntityManagerFactory("ClientePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TarjetaDeCredito tarjetaDeCredito) {
        if (tarjetaDeCredito.getClientes() == null) {
            tarjetaDeCredito.setClientes(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Cliente> attachedClientes = new ArrayList<Cliente>();
            for (Cliente clientesClienteToAttach : tarjetaDeCredito.getClientes()) {
                clientesClienteToAttach = em.getReference(clientesClienteToAttach.getClass(), clientesClienteToAttach.getDni());
                attachedClientes.add(clientesClienteToAttach);
            }
            tarjetaDeCredito.setClientes(attachedClientes);
            em.persist(tarjetaDeCredito);
            for (Cliente clientesCliente : tarjetaDeCredito.getClientes()) {
                clientesCliente.getTarjetasDeCredito().add(tarjetaDeCredito);
                clientesCliente = em.merge(clientesCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TarjetaDeCredito tarjetaDeCredito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TarjetaDeCredito persistentTarjetaDeCredito = em.find(TarjetaDeCredito.class, tarjetaDeCredito.getId());
            ArrayList<Cliente> clientesOld = persistentTarjetaDeCredito.getClientes();
            ArrayList<Cliente> clientesNew = tarjetaDeCredito.getClientes();
            ArrayList<Cliente> attachedClientesNew = new ArrayList<Cliente>();
            for (Cliente clientesNewClienteToAttach : clientesNew) {
                clientesNewClienteToAttach = em.getReference(clientesNewClienteToAttach.getClass(), clientesNewClienteToAttach.getDni());
                attachedClientesNew.add(clientesNewClienteToAttach);
            }
            clientesNew = attachedClientesNew;
            tarjetaDeCredito.setClientes(clientesNew);
            tarjetaDeCredito = em.merge(tarjetaDeCredito);
            for (Cliente clientesOldCliente : clientesOld) {
                if (!clientesNew.contains(clientesOldCliente)) {
                    clientesOldCliente.getTarjetasDeCredito().remove(tarjetaDeCredito);
                    clientesOldCliente = em.merge(clientesOldCliente);
                }
            }
            for (Cliente clientesNewCliente : clientesNew) {
                if (!clientesOld.contains(clientesNewCliente)) {
                    clientesNewCliente.getTarjetasDeCredito().add(tarjetaDeCredito);
                    clientesNewCliente = em.merge(clientesNewCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tarjetaDeCredito.getId();
                if (findTarjetaDeCredito(id) == null) {
                    throw new NonexistentEntityException("The tarjetaDeCredito with id " + id + " no longer exists.");
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
            TarjetaDeCredito tarjetaDeCredito;
            try {
                tarjetaDeCredito = em.getReference(TarjetaDeCredito.class, id);
                tarjetaDeCredito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarjetaDeCredito with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Cliente> clientes = tarjetaDeCredito.getClientes();
            for (Cliente clientesCliente : clientes) {
                clientesCliente.getTarjetasDeCredito().remove(tarjetaDeCredito);
                clientesCliente = em.merge(clientesCliente);
            }
            em.remove(tarjetaDeCredito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TarjetaDeCredito> findTarjetaDeCreditoEntities() {
        return findTarjetaDeCreditoEntities(true, -1, -1);
    }

    public List<TarjetaDeCredito> findTarjetaDeCreditoEntities(int maxResults, int firstResult) {
        return findTarjetaDeCreditoEntities(false, maxResults, firstResult);
    }

    private List<TarjetaDeCredito> findTarjetaDeCreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TarjetaDeCredito.class));
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

    public TarjetaDeCredito findTarjetaDeCredito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TarjetaDeCredito.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarjetaDeCreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TarjetaDeCredito> rt = cq.from(TarjetaDeCredito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
