import static endpoints.Endpoints.PRODUCT_BY_ID;

import io.restassured.RestAssured;
import models.products.ProductModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class UpdateProductTest extends BaseTest{

  @Test
  public void updateProduct() {
//    ProductModel oldProduct = RestAssured
//        .given()
//        .pathParams("product_id", 10)
//        .get(PRODUCT_BY_ID)
//        .then()
//        .statusCode(200)
//        .extract()
//        .body()
//        .as(ProductModel.class);


   ProductModel productModelToUpdate
       = ProductModel
       .builder()
       .title("Google Pixel 6 Pro")
       .price(1200)
       .build();

      ProductModel updatedProduct = RestAssured
        .given()
        .pathParams("product_id", 10)
        .body(productModelToUpdate)
        .put(PRODUCT_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductModel.class);

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(updatedProduct.getTitle())
            .as("The [title] wasn't updated after request for new one")
                .isEqualTo("Google Pixel 6 Pro");
    softAssertions.assertThat(updatedProduct.getPrice())
            .as("The [price] wasn't updated after request for new one")
                .isEqualTo(1200);

    softAssertions.assertAll();

  }

}
