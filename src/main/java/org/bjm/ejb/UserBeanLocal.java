package org.bjm.ejb;

import jakarta.ejb.Local;
import org.bjm.dto.UserDto;
import org.bjm.model.User;

/**
 *
 * @author singh
 */
@Local
public interface UserBeanLocal {
    
    public User createUser(UserDto userDto);
    public User updateUser(User user);
}
