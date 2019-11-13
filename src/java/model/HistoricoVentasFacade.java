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
public class HistoricoVentasFacade extends AbstractFacade<HistoricoVentas> {

    @PersistenceContext(unitName = "VentaAccionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoVentasFacade() {
        super(HistoricoVentas.class);
    }
    public void insertWithQuery(HistoricoVentas hv) {
        System.out.println("--------------------------------------------------Entrando a historico facade"+hv.getIdAccion().getIdAccion()+ " - " +hv.getIdUsuario().getIdUsuario() + " " +hv.getFechaVenta()+ " - "+hv.getEstadoActual() + " - "+hv.getValorVenta()+ " - " +hv.getValorReal()+ " - "+ hv.getCantidad());
    em.createNativeQuery("INSERT INTO HistoricoVentas(idAccion, idUsuario, fechaVenta, estadoActual, valorVenta, valorReal, cantidad) VALUES (?,?,?,?,?,?,?)")
      .setParameter(1, hv.getIdAccion())
      .setParameter(2, hv.getIdUsuario())
      .setParameter(3, hv.getFechaVenta())
      .setParameter(4, hv.getEstadoActual())
      .setParameter(5, hv.getValorVenta())
      .setParameter(6, hv.getValorReal())
            .setParameter(7, hv.getCantidad())
      .executeUpdate();
}
    
    public List<HistoricoVentas> findMovementActions(int descripcion){
            //descripcion = descripcion.toLowerCase();
         String query = "SELECT h FROM HistoricoVentas h WHERE h.idAccion.idAccion=:descripcion";
         Query creaQuery = em.createQuery(query);
        // List<Object[]> obj = new ArrayList<>();
         creaQuery.setParameter("descripcion", descripcion);
         return creaQuery.getResultList();
     }
    
}
