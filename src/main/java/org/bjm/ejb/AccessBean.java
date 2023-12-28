package org.bjm.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.bjm.model.Access;

/**
 *
 * @author singh
 */
@Stateless
public class AccessBean implements AccessBeanLocal {
    
    private static final Logger LOGGER=Logger.getLogger(AccessBean.class.getName());
    
    @PersistenceContext(name = "bjmPU")
    private EntityManager em;

    @Override
    public Access createAccess(Access access) {
        access.setCreatedOn(LocalDateTime.now());
        access.setUpdatedOn(LocalDateTime.now());
        em.persist(access);
        em.flush();
        LOGGER.info(String.format("Access created with ID: %d", access.getId()));
        return access;
    }

    @Override
    public Access findByEmail(String email) {
        Access toReturn=null;
        TypedQuery<Access> tQ=em.createQuery("select a from Access a where a.email =?1", Access.class);
        try{
            toReturn = tQ.getSingleResult();
        }catch(NoResultException ex){
            //Good for us
        }
        return toReturn;
    }

    @Override
    public Access updateAccess(Access access) {
        access.setUpdatedOn(LocalDateTime.now());
        access = em.merge(access);
        em.flush();
        LOGGER.info(String.format("Access with ID %d updated", access.getId()));
        return access;
        
    }

}
