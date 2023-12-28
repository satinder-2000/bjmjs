package org.bjm.ejb;

import jakarta.ejb.Local;
import org.bjm.model.Access;

/**
 *
 * @author singh
 */
@Local
public interface AccessBeanLocal {
    
    public Access createAccess(Access access);
    public Access updateAccess(Access access);
    public Access findByEmail(String email);
    
}
