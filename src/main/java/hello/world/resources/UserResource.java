package hello.world.resources;

import hello.world.api.User;
import hello.world.api.UserName;
import hello.world.api.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class.getName());

    @POST
    public void createUser(User createUserRequest) {
        LOGGER.info(">>createUser('{}')", createUserRequest);
    }

    /**
     * Retrieves a user for the given name.
     * <p>@param name
     * The name of the user</p>
     */
    @GET
    @Path("{name}")
    public User getUser(@PathParam("name") UserName name) {
        LOGGER.info(">>getUser({})", name);
        return getSampleUser();
    }

    @GET
    public Set<User> getAllUsers() {
        LOGGER.info(">>getAllUsers ");
        return Collections.singleton(getSampleUser());
    }

    /**
     * Temporal: builds a test user in order to return something in the requests
     * FIXME remove once connected to DB
     *
     * @return dummy
     */
    private User getSampleUser() {
        return User.builder()
                .name("Juan Perez")
                .email("jperez@mail.com")
                .created(LocalDateTime.now())
                .type(UserType.ADMINISTRATOR)
                .build();
    }

    @DELETE
    @Path("{name}")
    public void deleteUser(@PathParam("name") UserName name) {
        LOGGER.info(">>deleteUser({})", name);
    }
}
