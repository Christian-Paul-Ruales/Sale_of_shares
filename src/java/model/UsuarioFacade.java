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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "VentaAccionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    //@Override
    /////////////////////////////////////////////////////Creo el login
    public Usuario Login(Usuario us){
        Usuario user = null;
        String query;
        try {
            query = "SELECT u FROM Usuario u WHERE u.correo = :correo and u.clave = :clave";
            Query createQuery = em.createQuery(query);
            createQuery.setParameter("correo", us.getCorreo());
            createQuery.setParameter("clave", us.getClave());
            
            List<Usuario> listUser=createQuery.getResultList();
            if(!listUser.isEmpty()){
                user = listUser.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }
    
}
