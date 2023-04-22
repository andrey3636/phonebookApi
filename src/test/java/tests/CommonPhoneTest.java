package tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTest extends PhoneApi {


    @Test
    public void createEditDeleteNewPhone() {

        Response actualResponse = createPhone(201);
        int contactId = actualResponse.jsonPath().getInt("id");

        Response expectedResponse = getContactPhone(200, contactId);

        Assert.assertEquals(actualResponse.jsonPath().getString("countryCode"), expectedResponse.jsonPath().getString("countryCode"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("phoneNumber"), expectedResponse.jsonPath().getString("phoneNumber"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("contactId"), expectedResponse.jsonPath().getString("contactId"), "Description contact not equal");

        editExistingPhone(200, contactId);

        getContactPhone(200, contactId);

        Response actualEditResponse = getContactPhone(200, contactId);

        Assert.assertEquals(actualEditResponse.jsonPath().getString("countryCode"), randomDataBodyForEditPhone(contactId).getCountryCode(), "First name contact not equal");
        Assert.assertEquals(actualEditResponse.jsonPath().getString("phoneNumber"), randomDataBodyForEditPhone(contactId).getPhoneNumber(), "Last name contact not equal");
        Assert.assertEquals(actualEditResponse.jsonPath().getString("contactId"), randomDataBodyForEditPhone(contactId).getContactId(), "Description contact not equal");
        deleteExistingContact(200, contactId);

        Response actualDeletedResponse = getContactPhone(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");


    }

}
