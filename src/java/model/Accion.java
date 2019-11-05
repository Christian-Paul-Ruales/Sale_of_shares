/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RJ
 */
@Entity
@Table(name = "accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a")
    , @NamedQuery(name = "Accion.findByIdAccion", query = "SELECT a FROM Accion a WHERE a.idAccion = :idAccion")
    , @NamedQuery(name = "Accion.findByDescripcion", query = "SELECT a FROM Accion a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Accion.findByEstadoAccion", query = "SELECT a FROM Accion a WHERE a.estadoAccion = :estadoAccion")
    , @NamedQuery(name = "Accion.findByValorPorcentual", query = "SELECT a FROM Accion a WHERE a.valorPorcentual = :valorPorcentual")
    , @NamedQuery(name = "Accion.findByValorNominal", query = "SELECT a FROM Accion a WHERE a.valorNominal = :valorNominal")})
public class Accion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accion")
    private Integer idAccion;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_accion")
    private boolean estadoAccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_porcentual")
    private BigDecimal valorPorcentual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_nominal")
    private BigDecimal valorNominal;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idAccion")
    private Collection<HistoricoVentas> historicoVentasCollection;

    public Accion() {
    }

    public Accion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public Accion(Integer idAccion, boolean estadoAccion, BigDecimal valorPorcentual, BigDecimal valorNominal) {
        this.idAccion = idAccion;
        this.estadoAccion = estadoAccion;
        this.valorPorcentual = valorPorcentual;
        this.valorNominal = valorNominal;
    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstadoAccion() {
        return estadoAccion;
    }

    public void setEstadoAccion(boolean estadoAccion) {
        this.estadoAccion = estadoAccion;
    }

    public BigDecimal getValorPorcentual() {
        return valorPorcentual;
    }

    public void setValorPorcentual(BigDecimal valorPorcentual) {
        this.valorPorcentual = valorPorcentual;
    }

    public BigDecimal getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(BigDecimal valorNominal) {
        this.valorNominal = valorNominal;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<HistoricoVentas> getHistoricoVentasCollection() {
        return historicoVentasCollection;
    }

    public void setHistoricoVentasCollection(Collection<HistoricoVentas> historicoVentasCollection) {
        this.historicoVentasCollection = historicoVentasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccion != null ? idAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accion)) {
            return false;
        }
        Accion other = (Accion) object;
        if ((this.idAccion == null && other.idAccion != null) || (this.idAccion != null && !this.idAccion.equals(other.idAccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Accion[ idAccion=" + idAccion + " ]";
    }
    
}
