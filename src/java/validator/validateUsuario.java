/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author RJ
 */
@FacesValidator("validateUsuario")
public class validateUsuario implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    UIInput textInput = (UIInput) context.getViewRoot().findComponent("clave");

        String pass1=textInput.getValue().toString();
               
        String pass2=value.toString();
        if(pass1.equals(pass2)==false ){
            throw new ValidatorException(new FacesMessage("Las claves no coinciden"));
        }
    
    }
    
}
