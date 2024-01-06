package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
    public void createUser(int id,String userName,String firstName,String lastName, String phone, String email){
        User payload =new User();

        payload.setId(id);
        payload.setUserName(userName);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setPhone(phone);
        payload.setEmail(email);

       Response rsp= UserEndPoints.createUser(payload);
        Assert.assertEquals(rsp.getStatusCode(),200);
    }



}
