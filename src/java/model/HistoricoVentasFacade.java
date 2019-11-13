/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
