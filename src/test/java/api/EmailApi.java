package api;

import Email.EmailDto;
import io.restassured.response.Response;

public class EmailApi extends ApiBase {
    Response response;
    EmailDto dto;

    public EmailDto randomDataForEmail() {
        dto = new EmailDto();
        dto.setEmail("sty@gmail.com");
        dto.setContactId(4804);
        return dto;
    }

    public EmailDto randomDataForAddEmail(Integer emailId) {
        dto = new EmailDto();
        dto.setEmail("st@gmail.com");
        dto.setContactId(4804);
        dto.setId(emailId);
        return dto;
    }

    //создаю метод для создания имейла
    public void addEmail(Integer code) {
        String endPoint = "/api/email";
        postRequest(endPoint, code, randomDataForEmail());

        //    у меня есть  метод пост, в него надо передать данные, описаные в randomDataForEmail
    }

    // метод для обновления имейла
    public void updateEmail(Integer code, Integer emailId) {
        String endPoint = "/api/email";
        putRequest(endPoint, code, randomDataForAddEmail(emailId));
    }

    // удаляю имейл по его ID
    public void deleteEmail(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        deleteRequest(endpoint, code, emailId);
    }

    public Response getEmailByEmailId(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint, code, "id", emailId); //у имейла в боди приходит tmail id как "id"
        return response;
    }

    public Response getAllEmailsBycontactId(Integer code) {
        String endPoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endPoint, code, "contactId", 4804);
        return response;
    }

}