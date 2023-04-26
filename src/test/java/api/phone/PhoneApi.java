package api.phone;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;
    PhoneDto dto;

    public PhoneDto randomDataForCreatePhone() {
        dto = new PhoneDto();
        dto.setCountryCode("+49");
        dto.setPhoneNumber("15487789652");
        dto.setContactId(4804);
        return dto;
    }

    int contactId;

    public PhoneDto randomDataForExistingPhone(Integer phoneId) {
        dto = new PhoneDto();
        dto.setCountryCode("+49");
        dto.setPhoneNumber("15487789652");
        dto.setContactId(4804);
        dto.setId(phoneId);
        return dto;
    }

    public void createPhone(Integer code) {
        String endPoint = "/api/phone";
        postRequest(endPoint, code, randomDataForCreatePhone());

    }

    public void updatePhone(Integer code, Integer phoneId) {
        String endPoint = "/api/phone";
        putRequest(endPoint, code, randomDataForExistingPhone(phoneId));
    }

    public void deletePhone(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        deleteRequest(endpoint, code, phoneId);
    }

    public Response getCreatedPhoneByPhoneId(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        response = getRequestWithParam(endpoint, code, "id", phoneId);
        return response;
    }

    public Response getAllPhoneByContactId(Integer code) {
        String endPoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", 4804);
        return response;
    }
}
