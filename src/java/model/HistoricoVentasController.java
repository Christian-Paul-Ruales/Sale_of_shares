package model;

import model.util.JsfUtil;
import model.util.PaginationHelper;

import java.io.Serializable;
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

@Named("historicoVentasController")
@SessionScoped
public class HistoricoVentasController implements Serializable {

    private HistoricoVentas current;
    private DataModel items = null;
    @EJB
    private model.HistoricoVentasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    List<HistoricoVentas> hv_descipcion;
    public HistoricoVentasController() {
    }

    public HistoricoVentas getSelected() {
        if (current == null) {
            current = new HistoricoVentas();
            selectedItemIndex = -1;
        }
        return current;
    }

    private HistoricoVentasFacade getFacade() {
        return ejbFacade;
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (HistoricoVentas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new HistoricoVentas();
        selectedItemIndex = -1;
        return "/usuario/workspace_user?faces-redirect=true";
    }

    public String create() {
        System.out.println("-------------------------------------------Entrando a create historico ventas ID ACCION: "+current.getIdAccion().getIdAccion());
                System.out.println("-------------------------------------------Entrando a create historico ventas ID USUARIO: "+current.getIdUsuario());

                System.out.println("-------------------------------------------Entrando a create historico ventas FECHA VENTA: "+current.getFechaVenta());
        
                System.out.println("-------------------------------------------Entrando a create historico ventas ESTADO ACTUAL: "+current.getEstadoActual());
                System.out.println("-------------------------------------------Entrando a create historico ventas VALOR VENTA: "+current.getValorVenta());
                System.out.println("-------------------------------------------Entrando a create historico ventas VALOR REAL: "+current.getValorReal());
                System.out.println("-------------------------------------------Entrando a create historico ventas CAMTIDAD: "+current.getCantidad());
                
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistoricoVentasCreated"));
            return prepareCreate();
       
          
    }
    
     public String createsetHistorico(HistoricoVentas hv) {
        System.out.println("-------------------------------------------Entrando a create historico ventas ID ACCION: "+current.getIdAccion().getIdAccion());
                System.out.println("-------------------------------------------Entrando a create historico ventas ID USUARIO: "+current.getIdUsuario().getIdUsuario());

                System.out.println("-------------------------------------------Entrando a create historico ventas FECHA VENTA: "+current.getFechaVenta());
        
                System.out.println("-------------------------------------------Entrando a create historico ventas ESTADO ACTUAL: "+current.getEstadoActual());
                System.out.println("-------------------------------------------Entrando a create historico ventas VALOR VENTA: "+current.getValorVenta());
                System.out.println("-------------------------------------------Entrando a create historico ventas VALOR REAL: "+current.getValorReal());
                System.out.println("-------------------------------------------Entrando a create historico ventas CAMTIDAD: "+current.getCantidad());
                
            getFacade().insertWithQuery(hv);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistoricoVentasCreated"));
            return prepareCreate();
       
          
    }
    
    

    public String prepareEdit() {
        current = (HistoricoVentas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistoricoVentasUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (HistoricoVentas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HistoricoVentasDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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
     public String ShowHistorico() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String parameter = params.get("desc"); 
        this.hv_descipcion= getFacade().findMovementActionsbydescpcion(parameter);
       // this.current = accions.get(0);
       // int number=accions.size();
        //this.Number_Accions=number;
        
        //?faces-redirect=true
       // return "/accion/View?faces-redirect=true";
       
      // return hv;
      return "/accion/View?faces-redirect=true";
        
    }

    public List<HistoricoVentas> getHv_descipcion() {
        return hv_descipcion;
    }

    public void setHv_descipcion(List<HistoricoVentas> hv_descipcion) {
        this.hv_descipcion = hv_descipcion;
    }
     
     
     public List<HistoricoVentas> GetByDescription(String Descripcion) {
         System.out.println("His-----------------------------------------------------Historico Ventas: "+current.getIdAccion().getDescripcion());
        return getFacade().findMovementActionsbydescpcion(current.getIdAccion().getDescripcion());
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public HistoricoVentas getHistoricoVentas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = HistoricoVentas.class)
    public static class HistoricoVentasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HistoricoVentasController controller = (HistoricoVentasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "historicoVentasController");
            return controller.getHistoricoVentas(getKey(value));
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
            if (object instanceof HistoricoVentas) {
                HistoricoVentas o = (HistoricoVentas) object;
                return getStringKey(o.getIdVentas());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + HistoricoVentas.class.getName());
            }
        }

    }

}
