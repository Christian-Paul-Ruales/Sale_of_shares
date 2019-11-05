/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RJ
 */
@Entity
@Table(name = "metodo_pago_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetodoPagoUsuarios.findAll", query = "SELECT m FROM MetodoPagoUsuarios m")
    , @NamedQuery(name = "MetodoPagoUsuarios.findByIdMetodoPago", query = "SELECT m FROM MetodoPagoUsuarios m WHERE m.metodoPagoUsuariosPK.idMetodoPago = :idMetodoPago")
    , @NamedQuery(name = "MetodoPagoUsuarios.findByIdUsuario", query = "SELECT m FROM MetodoPagoUsuarios m WHERE m.metodoPagoUsuariosPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "MetodoPagoUsuarios.findByIdentificador", query = "SELECT m FROM MetodoPagoUsuarios m WHERE m.identificador = :identificador")
    , @NamedQuery(name = "MetodoPagoUsuarios.findByClave", query = "SELECT m FROM MetodoPagoUsuarios m WHERE m.clave = :clave")})
public class MetodoPagoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetodoPagoUsuariosPK metodoPagoUsuariosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "identificador")
    private String identificador;
    @Size(max = 12)
    @Column(name = "clave")
    private String clave;
    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "id_metodo_pago", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MetodoPago metodoPago;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public MetodoPagoUsuarios() {
    }

    public MetodoPagoUsuarios(MetodoPagoUsuariosPK metodoPagoUsuariosPK) {
        this.metodoPagoUsuariosPK = metodoPagoUsuariosPK;
    }

    public MetodoPagoUsuarios(MetodoPagoUsuariosPK metodoPagoUsuariosPK, String identificador) {
        this.metodoPagoUsuariosPK = metodoPagoUsuariosPK;
        this.identificador = identificador;
    }

    public MetodoPagoUsuarios(int idMetodoPago, int idUsuario) {
        this.metodoPagoUsuariosPK = new MetodoPagoUsuariosPK(idMetodoPago, idUsuario);
    }

    public MetodoPagoUsuariosPK getMetodoPagoUsuariosPK() {
        return metodoPagoUsuariosPK;
    }

    public void setMetodoPagoUsuariosPK(MetodoPagoUsuariosPK metodoPagoUsuariosPK) {
        this.metodoPagoUsuariosPK = metodoPagoUsuariosPK;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metodoPagoUsuariosPK != null ? metodoPagoUsuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoPagoUsuarios)) {
            return false;
        }
        MetodoPagoUsuarios other = (MetodoPagoUsuarios) object;
        if ((this.metodoPagoUsuariosPK == null && other.metodoPagoUsuariosPK != null) || (this.metodoPagoUsuariosPK != null && !this.metodoPagoUsuariosPK.equals(other.metodoPagoUsuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MetodoPagoUsuarios[ metodoPagoUsuariosPK=" + metodoPagoUsuariosPK + " ]";
    }
    
}
