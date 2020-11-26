package rest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import dtos.UserDTO;
import dtos.UserInfoDTO;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.GenericExceptionMapper;
import errorhandling.MissingInputException;
import facades.UserFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import static security.LoginEndpoint.USER_FACADE;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;
/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);
    
    @Context
    private UriInfo context;
    @Context
    SecurityContext securityContext;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }
    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {
        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }
    
    @POST
    @Path("/{username}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addUserInfo(@PathParam("username") String user, String userinfo) throws MissingInputException {
        UserInfoDTO p = GSON.fromJson(userinfo, UserInfoDTO.class);
        UserInfoDTO newU = FACADE.addUserInfo(p, user);
        return GSON.toJson(newU);
    }
    
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonString) throws API_Exception, MissingInputException {
        String username;
        String password;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            username = json.get("username").getAsString();
            password = json.get("password").getAsString();
        } catch (Exception e) {
           throw new API_Exception("Malformed JSON Suplied",400,e);
        }

            UserDTO userdto = new UserDTO(username, password);
            FACADE.newUser(userdto);
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("username", username);
            return Response.ok(new Gson().toJson(responseJson)).build();


    }
    
    
}