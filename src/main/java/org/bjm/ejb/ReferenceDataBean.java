package org.bjm.ejb;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import org.bjm.model.State;

/**
 *
 * @author singh
 */
@Singleton
@Startup
public class ReferenceDataBean implements ReferenceDataBeanLocal {
    
    private static final Logger LOGGER = Logger.getLogger(ReferenceDataBean.class.getName());
    
    @PersistenceContext(name = "bjmPU")
    private EntityManager em;

    @Override
    public List<State> getAllStates() {
        TypedQuery<State> tQ = em.createQuery("select s from State s", State.class);
        return tQ.getResultList();
    }

    
}
