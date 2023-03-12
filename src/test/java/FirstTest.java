import static endpoints.Endpoints.AUTH_LOGIN;

import io.restassured.RestAssured;
import models.login.LoginRequestModel;
import models.login.LoginResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {


  @Test
  public void loginTest() {

    LoginRequestModel requestModel = new LoginRequestModel("hbingley1", "CQutx25i8r");

    LoginResponseModel loginResponseModel = RestAssured
        .given()
        .body(requestModel)
        .post(AUTH_LOGIN)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(LoginResponseModel.class);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(loginResponseModel.getId())
        .as("[id] not as expected")
        .isEqualTo(2);
    softAssertions.assertThat(loginResponseModel.getUsername())
        .as("[user] not as expected")
        .isEqualTo("hbingley1");
    softAssertions.assertThat(loginResponseModel.getImage())
        .as("[image] not as expected")
        .startsWith("https://")
        .endsWith(".png");
    softAssertions.assertThat(loginResponseModel.getToken())
        .as("[token] not as expected")
        .isEqualTo(loginResponseModel.getToken());

    softAssertions.assertAll();

  }

}
