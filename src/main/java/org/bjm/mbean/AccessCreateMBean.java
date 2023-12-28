package org.bjm.mbean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;
import org.bjm.dto.AccessDto;
import org.bjm.ejb.AccessBeanLocal;
import org.bjm.model.Access;
import org.bjm.util.PasswordUtil;

/**
 *
 * @author singh
 */
@Named(value = "AccessCreateMBean")
@ViewScoped
public class AccessCreateMBean implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(AccessCreateMBean.class.getName());
    private AccessDto accessDto;
    private Access access;
    @Inject
    private AccessBeanLocal accessBeanLocal;
    
    @PostConstruct
    public void init(){
        accessDto=new AccessDto();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String email=request.getParameter("email");
        access = accessBeanLocal.findByEmail(email);
        if (access!=null){
            accessDto.setEmail(access.getEmail());
        }
    }
    
    
    public String createAccess(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        if (accessDto.getPassword().equals(accessDto.getConfirmPassword())){
            facesContext.addMessage("password",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match","Passwords do not match"));
        }else{
            access.setPassword(PasswordUtil.generateSecurePassword(access.getPassword(), access.getEmail()));
            access=accessBeanLocal.updateAccess(access);
                facesContext.addMessage("password",new FacesMessage(FacesMessage.SEVERITY_INFO, "Password created succesfully","Password created succesfully"));
        }
        return null;
    }
    
    
}
