package api.endpoints;

import api.payloads.User;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User user){

       Response rsp= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)

                .when()
                    .post(Routes.create_user);
       return rsp;

    }

    public static Response getUserByUserName(String username){

      Response rsp=  given()
              .accept("application/json")
              .pathParam("username",username)
              .when()
                .get(Routes.get_url);
      return rsp;
    }

    public static Response updateUser(String userName,User payload){

        Response rsp=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)

                .when()
                   .put(Routes.update_user);

        return rsp;

    }

    public static Response deleteUser(String userName){

        Response rsp=given()
                .accept("application/json")
                .pathParam("username",userName)

                .when()
                      .delete(Routes.delete_user);
        return rsp;
    }
}
