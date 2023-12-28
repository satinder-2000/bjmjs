package org.bjm.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import org.bjm.dto.UserDto;
import org.bjm.model.Access;
import org.bjm.model.User;

/**
 *
 * @author singh
 */
@Stateless
public class UserBean implements UserBeanLocal {
    
    private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext(name = "bjmPU")
    private EntityManager em;
    
    @Inject
    private AccessBeanLocal accessBeanLocal;
    
    @Override
    public User createUser(UserDto userDto) {
        User user= new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setDob(LocalDate.parse(userDto.getDob(), DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        user.setGender(userDto.getGender());
        user.setPhone(userDto.getPhone());
        user.setMobile(userDto.getMobile());
        user.setStateCode(userDto.getStateCode());
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        Access access= new Access();
        access.setEmail(user.getEmail());
        access.setProfileFile(userDto.getProfileImage());
        access.setImage(userDto.getImage());
        accessBeanLocal.createAccess(access);
        em.persist(user);
        em.flush();
        LOGGER.info(String.format("User created with ID %d", user.getId()));
        return user;
    }

    @Override
    public User updateUser(User user) {
        user = em.merge(user);
        em.flush();
        LOGGER.info(String.format("User with ID %d updated", user.getId()));
        return user;
    }

}
