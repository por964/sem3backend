package facades;

import dtos.UserInfoDTO;
import entities.User;
import entities.UserInfo;
import errorhandling.MissingInputException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
    
    public UserInfoDTO addUserInfo(UserInfoDTO userinfodto, String userName) throws MissingInputException  {
        
        if (userinfodto.getMail().length() == 0 || (userinfodto.getTelno().length() == 0)) {
            throw new MissingInputException("One or both values are missing");
        }   
        
        EntityManager em = emf.createEntityManager();
        
        try {
        
        User user = em.find(User.class, userName);
        
        UserInfo info = new UserInfo(userinfodto.getMail(), userinfodto.getTelno());
        
        info.setUser(user);
        
        em.getTransaction().begin();
            em.persist(info);
            em.getTransaction().commit();
        
         return new UserInfoDTO(info.getEmail(),info.getPhone());
        } finally {
            em.close();
        }
    }

}
