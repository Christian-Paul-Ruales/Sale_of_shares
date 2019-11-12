/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RJ
 */
@Stateless
public class AccionFacade extends AbstractFacade<Accion> {

    @PersistenceContext(unitName = "VentaAccionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccionFacade() {
        super(Accion.class);
    }
    public List<Accion> findAccionsbyIdUser(Usuario us,int[] range) {
        String query = "SELECT a FROM Accion a WHERE a.idUsuario = :idusuario and a.estadoAccion = :estadoAccion";
        Query createQuery = em.createQuery(query);
        createQuery.setParameter("idusuario", us);
            createQuery.setParameter("estadoAccion", true);
            createQuery.getResultList();
        createQuery.setMaxResults(range[1] - range[0] + 1);
        createQuery.setFirstResult(range[0]);
        return createQuery.getResultList();
    }
    
     public List<Accion> findGeneralAccions() {
         
        
        String query = "SELECT a FROM Accion a WHERE a.estadoAccion = :estadoAccion";
        Query createQuery = em.createQuery(query);
        createQuery.setParameter("estadoAccion", true);
          
        return createQuery.getResultList();
    }
     
        public List<Accion> findAccionsByDescripcion(int id){
         String query = "SELECT a FROM Accion a WHERE a.estadoAccion = :estadoAccion AND a.idAccion = :id";
         Query creaQuery = em.createQuery(query);
        // List<Object[]> obj = new ArrayList<>();
         creaQuery.setParameter("estadoAccion", true);
         creaQuery.setParameter("id", id);
         
         return creaQuery.getResultList();
     }
        
        public List<Accion> findByDescripcion(String descripcion){
            descripcion = descripcion.toLowerCase();
         String query = "SELECT a FROM Accion a WHERE a.estadoAccion = :estadoAccion AND LOWER(a.descripcion) LIKE :descripcion";
         Query creaQuery = em.createQuery(query);
        // List<Object[]> obj = new ArrayList<>();
         creaQuery.setParameter("estadoAccion", true);
         creaQuery.setParameter("descripcion", "%"+descripcion+"%");
         
         return creaQuery.getResultList();
     }
    
}
