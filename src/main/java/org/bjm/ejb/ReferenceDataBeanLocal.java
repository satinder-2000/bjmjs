package org.bjm.ejb;

import jakarta.ejb.Local;
import java.util.List;
import org.bjm.model.State;

/**
 *
 * @author singh
 */
@Local
public interface ReferenceDataBeanLocal {
    
    public List<State> getAllStates();
    
}
