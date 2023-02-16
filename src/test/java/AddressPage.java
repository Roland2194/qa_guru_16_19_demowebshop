import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddressPage {
    String creds = "Email=veppacrinesse-7149@yopmail.com&Password=_0Bavaria&RememberMe=false";
    String address = "Address.Id=0&Address.FirstName=Twen&Address.LastName=Mark&Address.Email=veppacrinesse-7149@yopmail.com&" +
            "Address.Company=&Address.CountryId=66&Address.StateProvinceId=0&Address.City=Moscow&Address." +
            "Address1=Twerskaya%2C+16&Address.Address2=&Address.ZipPostalCode=447725&Address." +
            "PhoneNumber=991895267&Address.FaxNumber=";

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";

    }

    @Test
    void addAddress() {
        String cookie = given()
                .contentType("application/x-www-form-urlencoded")
                .body(creds)
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .extract().cookie("NOPCOMMERCE.AUTH");

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body(address)
                .when()
                .post("/customer/addressadd")
                .then()
                .statusCode(302);
    }

}
