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
    
     public List<Object[]> findGeneralAccions() {
         
        
        String query = "SELECT  a.descripcion, count(a.descripcion) AS c FROM Accion a WHERE a.estadoAccion = :estadoAccion GROUP BY a.descripcion";
        Query createQuery = em.createQuery(query);
        List<Object[]> obj=new ArrayList<>();
        createQuery.setParameter("estadoAccion", true);
           for (Object object : createQuery.getResultList()) {
               Object [] oj=(Object [])object;
               obj.add(oj);
               System.out.println("   -+-+-+----+-++-+-+-+-+-+--++-+++++nueva corrida---+-+-++++++++-++++++++:"+oj[0] + " - "+oj[1]);
         } 
        
        return obj;
    }
    
}
