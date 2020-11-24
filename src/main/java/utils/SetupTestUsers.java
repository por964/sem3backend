package utils;


import entities.Favourite;
import entities.Role;
import entities.User;
import entities.UserInfo;
import errorhandling.MissingInputException;
import facades.UserFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) throws MissingInputException {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test1");
    User admin = new User("admin", "test1");
    User both = new User("user_admin", "test1");
    User claes = new User("claes", "rufbtr1");
    User kunde = new User("kunde", "test22");
    
    UserFacade FACADE = UserFacade.getUserFacade(emf);


    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Favourite fav = new Favourite("Denmark");
    user.addFavourite(fav);
    UserInfo inf = new UserInfo("claesvonh", "1234");
    inf.setUser(claes);
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    claes.addRole(userRole);
    kunde.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(claes);
    em.persist(kunde);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(inf);
    em.persist(fav);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
    
    //FACADE.addFavourite("Denmark", "user");
  }

}
