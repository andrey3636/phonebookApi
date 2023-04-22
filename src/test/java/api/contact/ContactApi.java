package api.contact;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.contact.ContactDto;

public class ContactApi extends ApiBase {

    Response response;

    Faker faker = new Faker();

    public ContactDto randomDataBodyForContact() {
        ContactDto dto = new ContactDto();
        dto.setFirstName(faker.internet().uuid());
        dto.setLastName(faker.internet().uuid());
        dto.setDescription(faker.internet().uuid());
        return dto;
    }

    public void createContact(Integer code) {
        String url = "/api/contact";

        response = postRequest(url, code, randomDataBodyForContact());

    }
}
