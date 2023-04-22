package tests;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {

    @Test
    public void createEditDeleteNewContact() {

        Response actualResponse = createContact(201);
        int contactId = actualResponse.jsonPath().getInt("id");

        Response expectedResponse = getContact(200, contactId);

        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description contact not equal");

        editExistingContact(200, contactId);

        getContact(200, contactId);

        Response actualEditResponse = getContact(200, contactId);

        Assert.assertEquals(actualEditResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");
        deleteExistingContact(200, contactId);

        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");


    }
}
