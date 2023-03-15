import static endpoints.Endpoints.PRODUCT_BY_ID;

import io.restassured.RestAssured;
import models.products.ProductResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class DeleteProductTest extends BaseTest {

  @Test
  public void deleteProduct() {
    ProductResponseModel productResponseModel = RestAssured
        .given()
        .pathParam("product_id", 33)
        .delete(PRODUCT_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductResponseModel.class);

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(productResponseModel.getIsDeleted())
        .as("Product is not deleted")
        .isTrue();
    softAssertions.assertThat(productResponseModel.getDeletedOn())
        .as("Deletion does not contain date of deleting")
        .isNotNull();
    softAssertions.assertAll();
  }

}
