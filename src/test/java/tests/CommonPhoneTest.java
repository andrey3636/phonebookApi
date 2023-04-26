package tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTest extends PhoneApi {
    @Test
    public void createEditDeletePhoneTest() {
        createPhone(201);
        Response response = getAllPhoneByContactId(200);
        int phoneId = response.jsonPath().getInt("[0].id");
        String phone = response.jsonPath().getString("[0].phoneNumber");
        String countryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(phone, randomDataForCreatePhone().getPhoneNumber(), "Created phone is not equals");
        Assert.assertEquals(countryCode, randomDataForCreatePhone().getCountryCode(), "Created countryCode is not equals");

        updatePhone(200, phoneId);
        Response updatedPhone = getAllPhoneByContactId(200);
        String updatedPhoneNumber = updatedPhone.jsonPath().getString("[0].phoneNumber");
        String updatedCountryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(updatedPhoneNumber, randomDataForExistingPhone(phoneId).getPhoneNumber(), "Updated phone is not equals");
        Assert.assertEquals(updatedCountryCode, randomDataForExistingPhone(phoneId).getCountryCode(), "Updated countryCode is not equals");

        deletePhone(200, phoneId);
        Response errorMessage = getCreatedPhoneByPhoneId(500, phoneId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");

    }

}

//        Response expectedResponse = getContactPhone(200, contactId);
//
//        Assert.assertEquals(actualResponse.jsonPath().getString("countryCode"), expectedResponse.jsonPath().getString("countryCode"), "countryCode not equal");
//        Assert.assertEquals(actualResponse.jsonPath().getString("phoneNumber"), expectedResponse.jsonPath().getString("phoneNumber"), "phoneNumber not equal");
//        Assert.assertEquals(actualResponse.jsonPath().getString("contactId"), expectedResponse.jsonPath().getString("contactId"), "Description contact not equal");
//
//        editExistingPhone(200, contactId);
//
//        getContactPhone(200, contactId);
//
//        Response actualEditResponse = getContactPhone(200, contactId);
//
//        Assert.assertEquals(actualEditResponse.jsonPath().getString("countryCode"), randomDataBodyForEditPhone(contactId).getCountryCode(), "First name contact not equal");
//        Assert.assertEquals(actualEditResponse.jsonPath().getString("phoneNumber"), randomDataBodyForEditPhone(contactId).getPhoneNumber(), "Last name contact not equal");
//        Assert.assertEquals(actualEditResponse.jsonPath().getString("contactId"), randomDataBodyForEditPhone(contactId).getContactId(), "Description contact not equal");
//        deleteExistingContact(200, contactId);
//
//        Response actualDeletedResponse = getContactPhone(500, contactId);
//        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");



