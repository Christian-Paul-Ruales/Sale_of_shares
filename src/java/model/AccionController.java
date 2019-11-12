package model;

import model.util.JsfUtil;
import model.util.PaginationHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("accionController")
@SessionScoped
public class AccionController implements Serializable {
    
    private Accion current;
    private DataModel items = null;
    private String Search;
    /**Datos para mostrar la venta de Acciones*/
    private List<Accion> Elements= new ArrayList<Accion>();
    @EJB
    private model.AccionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Integer Number_Accions;

    /**forSearch*/
    
    
    public String getSearch() {
        return Search;
    }

    public void setSearch(String Search) {
        this.Search = Search;
    }
    
    public String SearchAccions(){
      this.Elements=  getFacade().findByDescripcion(Search);
        for (Accion item : Elements) {
                   System.out.println("+-----------------------------------------+++++++++++++++++++++++-Entro a SearchAccions:"+item.getDescripcion());
 
        }
       System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SI ENTRO A SEARCH ACCIONS" +getSearch());

      return "/accion/List?faces-redirect=true";
    }

    public List<Accion> getElements() {
        System.out.println("              ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++         --------------------------------"+getSearch());
        return Elements;
    }

    public void setElements(List<Accion> Elements) {
        this.Elements = Elements;
    }
    
    
    public AccionController() {
    }

    public Accion getSelected() {
        if (current == null) {
            current = new Accion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AccionFacade getFacade() {
        return ejbFacade;
    }

    public Integer getNumber_Accions() {
        return Number_Accions;
    }

    public void setNumber_Accions(Integer Number_Accions) {
        this.Number_Accions = Number_Accions;
    }

    

    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public PaginationHelper getPaginationbyId() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    FacesContext context= FacesContext.getCurrentInstance();
                
                    Usuario us=(Usuario)context.getExternalContext().getSessionMap().get("usuario");
                    return new ListDataModel(getFacade().findAccionsbyIdUser(us,new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Accion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/accion/View?faces-redirect=true";
    }

    
    /**----------------------------------------------------------------------------Mostrar las acciones */
    public String ShowAccionsByDescription() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int parameter = Integer.parseInt(params.get("id")); 
        List<Accion> accions= getFacade().findAccionsByDescripcion(parameter);
        this.current = accions.get(0);
        int number=accions.size();
        this.Number_Accions=number;
        
        //?faces-redirect=true
        return "/accion/View?faces-redirect=true";
        
    }
    
    public String prepareBuy(){
         
        return "";
    }
    
    public String Buy(){
        BigDecimal valor_real = GenerateRealValue();

        update();
        UsuarioController us = new UsuarioController();
        Usuario accionist = us.LoggedUser();
        current.setIdUsuario(accionist);
        current.setCantidad(Number_Accions);
        
        /**Guardo la venta*/
        HistoricoVentasController hvc= new HistoricoVentasController();
        
        hvc.getSelected().setIdAccion(current);
        
        hvc.getSelected().setIdUsuario(accionist);
        Date objDate = new Date();
        hvc.getSelected().setFechaVenta(objDate);
        hvc.getSelected().setEstadoActual(1);
        
        
        hvc.getSelected().setValorReal(valor_real);
        System.out.println("-------------------------------------------------Buy accion cotroller Valor real: "+valor_real);
        BigDecimal cant = new BigDecimal(current.getCantidad());
        BigDecimal bd = valor_real.multiply(cant);
         System.out.println("-------------------------------------------------Buy accion cotroller Valor Venta: "+bd);

        hvc.getSelected().setValorVenta(bd);
                 System.out.println("-------------------------------------------------Buy accion cotroller Cantidad "+current.getCantidad());

        hvc.getSelected().setCantidad(current.getCantidad());
       
        hvc.create();
        
        
        create();
        
        
       
        
        return "/usuario/workspace_user";
    }
    
    public String prepareCreate() {
        current = new Accion();
        selectedItemIndex = -1;
        return "/accion/Create?faces-redirect=true";
    }

    public String create() {
        
        try {
            try {
                FacesContext context= FacesContext.getCurrentInstance();
                
                 Usuario us=(Usuario)context.getExternalContext().getSessionMap().get("usuario");
                 current.setIdUsuario(us);
                 current.setEstadoAccion(true);
                 current.setIdEmpresa(us);
            } catch (Exception e) {
            }
            
            
               getFacade().create(current);
                JsfUtil.addSuccessMessage("Accion Creada");
              
            
           return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Accion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/accion/Edit?faces-redirect=true";
    }

    /**Genero un precio Final*/
    public BigDecimal GenerateRealValue(){
        try {
            System.out.println("-------------------------------------------------------Valor Nominal:"+current.getValorNominal());
        BigDecimal RealValue=current.getValorNominal();
        
        double cast = Double.parseDouble(RealValue.toString()) ;
        double Quintaparte =Math.random() * cast;
        double Real_final_value = cast + Quintaparte;
         BigDecimal retornar = new BigDecimal(Real_final_value);
        
         return retornar;
        } catch (Exception e) {
            System.err.println("----------------------------------------------------------Error al generar valor Nominal:"+e.getMessage());
            return new BigDecimal(0);
        }
        
    
    }
    public String update() {
        try {
            
            current.setCantidad(current.getCantidad()-Number_Accions);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AccionUpdated"));
            return "/accion/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Accion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/usuario/workspace_user?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/accion/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/accion/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Accion Eliminada");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,"Error ha pcurrido al eliminar la accion");
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
     public DataModel getItemsById() {
        if (items == null) {
            items = getPaginationbyId().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    
    /**------------------------------------------------------------- Lista de Acciones---------------*/
    /**Lista de acciones para el index*/
    public DataModel getAccionsForIndex(){
        ListDataModel dm= new ListDataModel(getFacade().findGeneralAccions());
        this.items = dm;
        
        for (Object items : dm) {
           
            System.out.println("---------------------------------------------"
                + "model.AccionController.getAccionsForIndex()"+items);
        }
       return dm;
    }
    
     public List<Accion> getAccionsinList(){
         this.Elements= getFacade().findGeneralAccions();
        
       return this.Elements;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Accion getAccion(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Accion.class)
    public static class AccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccionController controller = (AccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accionController");
            return controller.getAccion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Accion) {
                Accion o = (Accion) object;
                return getStringKey(o.getIdAccion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Accion.class.getName());
            }
        }

    }

}
