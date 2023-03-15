import static endpoints.Endpoints.PRODUCTS_SEARCH;

import io.restassured.RestAssured;
import java.util.Objects;
import models.products.ProductModel;
import models.products.ProductResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

  @Test
  public void searchProductTest() {
    ProductResponseModel products = RestAssured
        .given()
        .queryParam("q", "Samsung")
        .get(PRODUCTS_SEARCH)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductResponseModel.class);

    SoftAssertions softAssertions = new SoftAssertions();
    //product titles are [Samsung Universe 9, Samsung Galaxy Book]
    softAssertions.assertThat(products.getProducts())
        .map(ProductModel::getTitle)
        .as("Product [title]s do(es) not match to expected")
        .contains("Samsung Universe 9", "Samsung Galaxy Book");

    //total 2
    softAssertions.assertThat(products.getProducts().size())
        .as("Product [total] is not as expected")
        .isEqualTo(2);

    softAssertions.assertAll();

  }

}
