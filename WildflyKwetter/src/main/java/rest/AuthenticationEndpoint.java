package rest;

import auth.requests.LoginRequest;
import auth.TokenHelper;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/authentication")
public class AuthenticationEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private TokenHelper tokenHelper;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginRequest loginRequest) {

        try {
            authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            String token = issueToken(loginRequest.getUsername());

            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        userService.logIn(username, password);
    }

    private String issueToken(String username) {
        return tokenHelper.createJWT(UUID.randomUUID().toString(), "kwetter", String.valueOf(userService.getUserByUsername(username).getId()), 1000000);
    }
}