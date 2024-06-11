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
import entidades.Empleado;
import entidades.Cliente;
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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public FacturaJpaController() {
        emf = Persistence.createEntityManagerFactory("ClientePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado emp = factura.getEmp();
            if (emp != null) {
                emp = em.getReference(emp.getClass(), emp.getDni());
                factura.setEmp(emp);
            }
            Cliente clie = factura.getClie();
            if (clie != null) {
                clie = em.getReference(clie.getClass(), clie.getDni());
                factura.setClie(clie);
            }
            em.persist(factura);
            if (emp != null) {
                emp.getFacturas().add(factura);
                emp = em.merge(emp);
            }
            if (clie != null) {
                clie.getFacturas().add(factura);
                clie = em.merge(clie);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getNroFactura());
            Empleado empOld = persistentFactura.getEmp();
            Empleado empNew = factura.getEmp();
            Cliente clieOld = persistentFactura.getClie();
            Cliente clieNew = factura.getClie();
            if (empNew != null) {
                empNew = em.getReference(empNew.getClass(), empNew.getDni());
                factura.setEmp(empNew);
            }
            if (clieNew != null) {
                clieNew = em.getReference(clieNew.getClass(), clieNew.getDni());
                factura.setClie(clieNew);
            }
            factura = em.merge(factura);
            if (empOld != null && !empOld.equals(empNew)) {
                empOld.getFacturas().remove(factura);
                empOld = em.merge(empOld);
            }
            if (empNew != null && !empNew.equals(empOld)) {
                empNew.getFacturas().add(factura);
                empNew = em.merge(empNew);
            }
            if (clieOld != null && !clieOld.equals(clieNew)) {
                clieOld.getFacturas().remove(factura);
                clieOld = em.merge(clieOld);
            }
            if (clieNew != null && !clieNew.equals(clieOld)) {
                clieNew.getFacturas().add(factura);
                clieNew = em.merge(clieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = factura.getNroFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getNroFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Empleado emp = factura.getEmp();
            if (emp != null) {
                emp.getFacturas().remove(factura);
                emp = em.merge(emp);
            }
            Cliente clie = factura.getClie();
            if (clie != null) {
                clie.getFacturas().remove(factura);
                clie = em.merge(clie);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
