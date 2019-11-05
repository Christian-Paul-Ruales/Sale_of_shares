/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author RJ
 */
@Embeddable
public class MetodoPagoUsuariosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_metodo_pago")
    private int idMetodoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;

    public MetodoPagoUsuariosPK() {
    }

    public MetodoPagoUsuariosPK(int idMetodoPago, int idUsuario) {
        this.idMetodoPago = idMetodoPago;
        this.idUsuario = idUsuario;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMetodoPago;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoPagoUsuariosPK)) {
            return false;
        }
        MetodoPagoUsuariosPK other = (MetodoPagoUsuariosPK) object;
        if (this.idMetodoPago != other.idMetodoPago) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MetodoPagoUsuariosPK[ idMetodoPago=" + idMetodoPago + ", idUsuario=" + idUsuario + " ]";
    }
    
}
