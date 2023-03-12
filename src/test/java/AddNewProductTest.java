import static endpoints.Endpoints.PRODUCTS_ADD;

import io.restassured.RestAssured;
import java.util.Arrays;
import models.products.ProductRequestModel;
import models.products.ProductResponseModel.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class AddNewProductTest extends BaseTest {

  ProductRequestModel productRequestModel = new ProductRequestModel("MacBook Air Pro",
      1500, 150, 5,
      Arrays.asList("https://i.dummyjson.com/data/products/10/1.jpg",
          "https://i.dummyjson.com/data/products/10/2.jpg"),
      "https://i.dummyjson.com/data/products/10/thumbnail.jpeg",
      "MacBook Air Pro 13", "Mac",
      "laptops");

  @Test
  public void addNewProduct() {
    Product myProduct = RestAssured
        .given()
        .body(productRequestModel)
        .post(PRODUCTS_ADD)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(Product.class);

    SoftAssertions softAssertions = new SoftAssertions();

//    id = 101
    softAssertions.assertThat(myProduct.getId())
        .as("New product [id] is not as expected")
        .isEqualTo(101);
//    all fields from request exist in response with same values
    softAssertions.assertThat(myProduct.equals(productRequestModel))
        .as("New product does not have values from requested product")
        .isTrue();


  }

}
