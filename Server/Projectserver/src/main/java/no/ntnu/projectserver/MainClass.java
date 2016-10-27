
package no.ntnu.projectserver;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * This is a server solution for the Tungrocken news app for Android systems.
 * It is a REST based server implemented with JAX-RS.
 * 
 * @author Team Tungrocken
 */

@Stateless
@Path("app")
@Produces(MediaType.APPLICATION_JSON)

public class MainClass {
    
    @PersistenceContext
    EntityManager em;
    
    @Resource(mappedName="jdbc/project")
    DataSource dataSource;
    
    // Return a list of all users
    @GET
    @Path("users")
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u from User u",User.class).getResultList();
    }
    
    // Search for a user by first or last name
    @GET
    @Path("finduser")
    public List<User> findUser(@QueryParam("name") String userParam) {
        userParam = userParam.toLowerCase();
        return em.createQuery("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(:userName) OR LOWER(u.lastName) LIKE LOWER(:userName)").setParameter("userName", userParam).getResultList();
    }
    
    // Return a user based on ID
    @GET
    @Path("getuser")
    public List<User> getUser(@QueryParam("id") Long userID) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :paramID").setParameter("paramID", userID).getResultList();
    }
}
