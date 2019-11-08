/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
}
