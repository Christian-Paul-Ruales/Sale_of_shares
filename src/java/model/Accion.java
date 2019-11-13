/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collection;
import javax.faces.context.FacesContext;
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
import javax.persistence.Query;
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
    
    @Column(name = "cantidad")
    private Integer cantidad;
    
     @Column(name = "valor_real")
    private BigDecimal valorReal;
    
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idEmpresa;
    
    
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

    public BigDecimal getValorReal() {
        return valorReal;
    }

    public void setValorReal(BigDecimal valorReal) {
        this.valorReal = valorReal;
    }
    
    /**                   Genera el valor real, el precio final ----------------*/
        public BigDecimal GenerateRealValue(){
        try {
            System.out.println("-------------------------------------------------------Valor Nominal:"+getValorNominal());
            BigDecimal RealValue=getValorNominal();

            double cast = Double.parseDouble(RealValue.toString()) ;
            double Quintaparte =(Math.random() * cast)/5;
            double Real_final_value = cast + Quintaparte;
            BigDecimal retornar = new BigDecimal(Real_final_value);
           
            System.out.println("------------------------------------------Generate real value descipcion : " +getDescripcion());
          int num =0;
          int cast2 =(int)cast;
          int M =0 ;
            try {
                 String query = "SELECT h FROM HistoricoVentas h WHERE h.idAccion.descripcion=:descripcion";
         //Query creaQuery = encreateQuery(query);
        // List<Object[]> obj = new ArrayList<>();
        // creaQuery.setParameter("descripcion", descripcion);
          //creaQuery.getResultList();
          AccionFacade AF= new AccionFacade();
                num= AF.findMovementActions(getDescripcion()).size();
                System.out.println(" ---------------------------------- num :"+num);
                
                
                if(num>5){
                    M=(int)(cast/2);
                }
                if(num<=5 && num>1){
                    M=0;
                    cast2=cast2/2;

                }
                if(num==1){
                        M=0;
                        cast2=1;
                    }
                if(num==0){
                        M=-10;
                        cast2=0;
                    }

            
            } catch (Exception calculo) {
                            System.err.println("----------------------------------------------------------Error en el calculo:"+calculo.toString());

                
            }
            
              int valorEntero = (int) Math.floor(Math.random()*(( cast2-M+1)+M));
              BigDecimal value_accions = new BigDecimal(valorEntero);
             retornar=retornar.add(value_accions);
             retornar = retornar.setScale(2, RoundingMode.HALF_UP);
             return retornar;
        } catch (Exception e) {
            System.err.println("----------------------------------------------------------Error al generar valor Nominal:"+e.toString());
            return new BigDecimal(0);
        }
        
    
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        
        this.idUsuario = idUsuario;
    }

    public Usuario getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Usuario idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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
