package api.tests;

import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    public User user;
    public Faker faker;
  //  public UserEndPoints userEndPoints;

    @BeforeClass
    public void setUp(){
        user=new User();
        faker=new Faker();
      // userEndPoints=new UserEndPoints();
        user.setId(faker.idNumber().hashCode());
        user.setUserName(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().phoneNumber());

    }

    @Test
    public void  createUser(){
       Response rsp= UserEndPoints.createUser(user);
        Assert.assertEquals(rsp.getStatusCode(),200);
    }

   // @Test
    public void getUserByName(){
     Response rsp= UserEndPoints.getUserByUserName(this.user.getUserName());
        Assert.assertEquals(rsp.getStatusCode(),200);
       //Assert.assertEquals(rsp.jsonPath().get("firstName"),this.user.getFirstName());
    }

    @Test
    public void updateUserByName(){
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

      Response rsp= UserEndPoints.updateUser(this.user.getUserName(),user);
      Assert.assertEquals(rsp.getStatusCode(),200);
    }

    @Test
    public void deleteuserByName(){

       Response rsp=UserEndPoints.deleteUser(this.user.getUserName());
       Assert.assertEquals(rsp.getStatusCode(),200);
    }
}
