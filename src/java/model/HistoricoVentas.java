/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RJ
 */
@Entity
@Table(name = "historico_ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoVentas.findAll", query = "SELECT h FROM HistoricoVentas h")
    , @NamedQuery(name = "HistoricoVentas.findByIdVentas", query = "SELECT h FROM HistoricoVentas h WHERE h.idVentas = :idVentas")
    , @NamedQuery(name = "HistoricoVentas.findByFechaVenta", query = "SELECT h FROM HistoricoVentas h WHERE h.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "HistoricoVentas.findByEstadoActual", query = "SELECT h FROM HistoricoVentas h WHERE h.estadoActual = :estadoActual")
    , @NamedQuery(name = "HistoricoVentas.findByValorVenta", query = "SELECT h FROM HistoricoVentas h WHERE h.valorVenta = :valorVenta")
    , @NamedQuery(name = "HistoricoVentas.findByValorReal", query = "SELECT h FROM HistoricoVentas h WHERE h.valorReal = :valorReal")})
public class HistoricoVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ventas")
    private Integer idVentas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_actual")
    private int estadoActual;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_venta")
    private BigDecimal valorVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_real")
    private BigDecimal valorReal;
    @JoinColumn(name = "id_accion", referencedColumnName = "id_accion")
    @ManyToOne
    private Accion idAccion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public HistoricoVentas() {
    }

    public HistoricoVentas(Integer idVentas) {
        this.idVentas = idVentas;
    }

    public HistoricoVentas(Integer idVentas, Date fechaVenta, int estadoActual, BigDecimal valorVenta, BigDecimal valorReal) {
        this.idVentas = idVentas;
        this.fechaVenta = fechaVenta;
        this.estadoActual = estadoActual;
        this.valorVenta = valorVenta;
        this.valorReal = valorReal;
    }

    public Integer getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Integer idVentas) {
        this.idVentas = idVentas;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
    }

    public BigDecimal getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(BigDecimal valorVenta) {
        this.valorVenta = valorVenta;
    }

    public BigDecimal getValorReal() {
        return valorReal;
    }

    public void setValorReal(BigDecimal valorReal) {
        this.valorReal = valorReal;
    }

    public Accion getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Accion idAccion) {
        this.idAccion = idAccion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVentas != null ? idVentas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoVentas)) {
            return false;
        }
        HistoricoVentas other = (HistoricoVentas) object;
        if ((this.idVentas == null && other.idVentas != null) || (this.idVentas != null && !this.idVentas.equals(other.idVentas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.HistoricoVentas[ idVentas=" + idVentas + " ]";
    }
    
}
