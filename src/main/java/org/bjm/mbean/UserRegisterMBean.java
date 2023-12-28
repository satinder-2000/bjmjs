package org.bjm.mbean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;
import org.bjm.dto.UserDto;
import org.bjm.ejb.ReferenceDataBeanLocal;

/**
 *
 * @author singh
 */
@Named("userRegisterMBean")
@FlowScoped(value = "UserRegister")
public class UserRegisterMBean implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(UserRegisterMBean.class.getName());
    private UserDto userDto;
    
    @Inject
    private ReferenceDataBeanLocal referenceDataBeanLocal;
    
    @PostConstruct
    public void init(){
        userDto=new UserDto();
        userDto.setAllStates(referenceDataBeanLocal.getAllStates());
        
    }
    
    
}
