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
import javax.persistence.CascadeType;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByCiRuc", query = "SELECT u FROM Usuario u WHERE u.ciRuc = :ciRuc")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByCapital", query = "SELECT u FROM Usuario u WHERE u.capital = :capital")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuario.findByValorEmpresa", query = "SELECT u FROM Usuario u WHERE u.valorEmpresa = :valorEmpresa")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "ci_ruc")
    private String ciRuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "capital")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "clave")
    private String clave;
    @Column(name = "valor_empresa")
    private BigDecimal valorEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Accion> accionCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<HistoricoVentas> historicoVentasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<MetodoPagoUsuarios> metodoPagoUsuariosCollection;
    @JoinColumn(name = "id_tipousuario", referencedColumnName = "id_tipousuario")
    @ManyToOne
    private TipoUsuario idTipousuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String ciRuc, String nombre, String correo, String clave) {
        this.idUsuario = idUsuario;
        this.ciRuc = ciRuc;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCiRuc() {
        return ciRuc;
    }

    public void setCiRuc(String ciRuc) {
        this.ciRuc = ciRuc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public BigDecimal getValorEmpresa() {
        return valorEmpresa;
    }

    public void setValorEmpresa(BigDecimal valorEmpresa) {
        this.valorEmpresa = valorEmpresa;
    }

    @XmlTransient
    public Collection<Accion> getAccionCollection() {
        return accionCollection;
    }

    public void setAccionCollection(Collection<Accion> accionCollection) {
        this.accionCollection = accionCollection;
    }

    @XmlTransient
    public Collection<HistoricoVentas> getHistoricoVentasCollection() {
        return historicoVentasCollection;
    }

    public void setHistoricoVentasCollection(Collection<HistoricoVentas> historicoVentasCollection) {
        this.historicoVentasCollection = historicoVentasCollection;
    }

    @XmlTransient
    public Collection<MetodoPagoUsuarios> getMetodoPagoUsuariosCollection() {
        return metodoPagoUsuariosCollection;
    }

    public void setMetodoPagoUsuariosCollection(Collection<MetodoPagoUsuarios> metodoPagoUsuariosCollection) {
        this.metodoPagoUsuariosCollection = metodoPagoUsuariosCollection;
    }

    public TipoUsuario getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(TipoUsuario idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   nombre;
    }
    
}
