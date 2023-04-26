package tests;

import api.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonEmailTest extends EmailApi {
    @Test
    public void add() {

        addEmail(201);
        Response response = getAllEmailsBycontactId(200); // здесь я достаю весь список имейлов по contactid/ а так как это List то обращенее идет к первому [0] - му элементу List(как массив)

        // todo -------------------Здесь я достаю данные из Response----------------------------------------------------------------------------------
        int emailId = response.jsonPath().getInt("[0].id"); // здесь я достаю Id имейла
        String email = response.jsonPath().getString("[0].email"); // здесь я достаю как написан сам имейл
        int contactId = response.jsonPath().getInt("[0].contactId"); // здесь я достаю contact id который прописываю в самом запросе

        //todo -------------------Проверка имейла что приходит в ответе (email) и того что я отправляю в request(randomDataForEmail() - это метод в EmailApi)
        //Assert.assertEquals(emailId, randomDataForEmail().getId(), "Edited emails is not equals"); //не проходит , логично, так как у имейла который я отправляю еще нету ID
        Assert.assertEquals(email, randomDataForEmail().getEmail(), "Edited emails is not equals");
        Assert.assertEquals(contactId, randomDataForEmail().getContactId(), "Edited emails is not equals");
    }

    @Test
    public void update() {

        Response response = getAllEmailsBycontactId(200);
        int emailId = response.jsonPath().getInt("[0].id");
        updateEmail(200, emailId);
        Response addEmailResponse = getAllEmailsBycontactId(200);

        String email = addEmailResponse.jsonPath().getString("[0].email"); // здесь я достаю как написан сам имейл
        int contactId = addEmailResponse.jsonPath().getInt("[0].contactId"); // здесь я достаю contact id который прописываю в самом запросе

        Assert.assertEquals(email, randomDataForAddEmail(emailId).getEmail(), "Edited emails is not equals");
    }

    @Test
    public void delete_Email() {
        Response response = getAllEmailsBycontactId(200);
        int emailId = response.jsonPath().getInt("[0].id");
        deleteEmail(200, emailId);
        String expectedResponse = "Error! This email doesn't exist in our DB";
        Response actualResponse = getEmailByEmailId(500, emailId);
        Assert.assertEquals(actualResponse.jsonPath().getString("message"), expectedResponse, "Not equals");
    }


}

