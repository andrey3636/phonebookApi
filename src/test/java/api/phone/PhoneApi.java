package api.phone;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;

    PhoneDto dto;

    Faker faker = new Faker();

    public PhoneDto randomDataBodyForCreatePhone() {
        dto = new PhoneDto();
        dto.setCountryCode("+93");
        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        dto.setContactId(4799);
        return dto;
    }

    public PhoneDto randomDataBodyForEditPhone(Integer contactId) {
        dto = new PhoneDto();
        dto.setId(contactId);
        dto.setCountryCode("+49");
        dto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        dto.setContactId(4799);
        return dto;
    }

    public Response createPhone(Integer code) {
        String endpoint = "/api/phone";

        response = postRequest(endpoint, code, randomDataBodyForCreatePhone());
        return response;

    }

    public void editExistingPhone(Integer code, Integer contactId) {
        String endpoint = "/api/phone";
        putRequest(endpoint, code, randomDataBodyForEditPhone(contactId));
    }

    public void deleteExistingContact(int code, Integer contactId) {
        String endpoint = "/api/phone/{id}";
        deleteRequestWithParam(endpoint, code, contactId);
    }

    public Response getContactPhone(Integer code, int contactId) {
        String endpoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endpoint, code, contactId);
        return response;

    }
}
