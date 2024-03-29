package model;

import model.util.JsfUtil;
import model.util.PaginationHelper;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.servlet.ServletContext;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private DataModel items = null;
    @EJB
    private model.UsuarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String pass2;
    public UsuarioController() {
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    
    private UsuarioFacade getFacade() {
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
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Usuario();
        selectedItemIndex = -1;
        return "Create";
    }

    /** --------------------------------------------------------------------------------------------- Crear la sesion ---------------------- */
    
    public String createSession(){
        Usuario us;
       
        try {
            us= getFacade().Login(current);
            if(us!=null){
                                
                JsfUtil.addSuccessMessage(String.format(" Usuario %s conectado", current.getCorreo()));
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  ++++ Almacenar Sesion
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",us);
                
                return "workspace_user?faces-redirect=true";//redireccion
            }else{
                JsfUtil.addErrorMessage("No se ha encontrado el usuario, asegurese de que este registrado");

                return null;
            }
            
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error EXEPCION create Session");
            return null;
        }
        
    }
    
   /** -------------------------------------------------------------------------Verificar Sesiones ------------------------------------------------------*/
    /**Evita el Acceso no autorizado de usuarios que no estan registrados*/
    public void VerifySession(){
        try {
            FacesContext context= FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
               // String root = pageContext.getRequest().getServletContext().getContextPath();
             String path =servletContext.getContextPath();
             System.out.println(" --------------------------------------------- PATH SESSION: "+path);
           Usuario us=(Usuario)context.getExternalContext().getSessionMap().get("usuario");
           if(us == null){
               JsfUtil.addErrorMessage("Debes Iniciar Sesion Primero");
               
               context.getExternalContext().redirect("/VentaAcciones/faces/usuario/login.xhtml");
           }
        } catch (Exception e) {
        }
        
    }
    
    /**--------------------------------------------------------------------------Cerrar Sesion --------------------------------------------------------------*/
    /**Salir de la sesion, deslogearse*/
    public void CloseSession(){
        System.out.println("   ----------------------------------Entro al metodo Close Session");

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {

            System.out.println("   ----------------------------------Error Cerrar sesion:"+e.getMessage());
        }
        
        
    }
    public Usuario LoggedUser(){
        Usuario us = new Usuario();
        try {
            FacesContext context = FacesContext.getCurrentInstance();
             us= (Usuario)context.getExternalContext().getSessionMap().get("usuario");
        } catch (Exception e) {
            
        }
        return us;
    }
    public String create() {
        try {
            
            if(getSelected().getClave().equals(getPass2())){
             getFacade().create(current);
                if(current.getIdTipousuario().getIdTipousuario()==1){
                      int valorEntero = (int) Math.floor(Math.random()*(20000000-10000+1)+10000);
                      BigDecimal valor_emp= new BigDecimal(valorEntero);
                      current.setValorEmpresa(valor_emp);
                }
                JsfUtil.addSuccessMessage("Usuario "+current.getNombre()+"Creado con exito");
                return prepareCreate();
            }else{
                JsfUtil.addErrorMessage("Las claves son diferentes");

                return null;
            }
           
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            
            
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuario) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
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

    public Usuario getUsuario(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }

    }

}
