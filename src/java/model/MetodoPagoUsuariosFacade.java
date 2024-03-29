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
public class MetodoPagoUsuariosFacade extends AbstractFacade<MetodoPagoUsuarios> {

    @PersistenceContext(unitName = "VentaAccionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MetodoPagoUsuariosFacade() {
        super(MetodoPagoUsuarios.class);
    }
    
    public List<MetodoPagoUsuarios> getMetodosPagoByUser(int idusuario){
         String query="SELECT m FROM MetodoPagoUsuarios m WHERE m.metodoPagoUsuariosPK.idUsuario = :idUsuario";
         Query CreateQuery= em.createQuery(query);
         CreateQuery.setParameter("idUsuario", idusuario );
         
        return CreateQuery.getResultList();
    }
    
}
