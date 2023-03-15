import static endpoints.Endpoints.PRODUCTS_ADD;

import io.restassured.RestAssured;
import java.util.Arrays;
import models.products.ProductModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class AddNewProductTest extends BaseTest {
  @Test
  public void addNewProduct() {
    final ProductModel productModel = ProductModel.builder()
        .title("MacBook Air Pro")
        .price(1500)
        .stock(150)
        .rating(5)
        .images(Arrays.asList("https://i.dummyjson.com/data/products/10/1.jpg",
            "https://i.dummyjson.com/data/products/10/2.jpg"))
        .thumbnail("https://i.dummyjson.com/data/products/10/thumbnail.jpeg")
        .description("MacBook Air Pro 13")
        .brand("Mac")
        .category("laptops")
        .build();

    ProductModel myProduct = RestAssured
        .given()
        .body(productModel)
        .post(PRODUCTS_ADD)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductModel.class);

    SoftAssertions softAssertions = new SoftAssertions();

//    id = 101
    softAssertions.assertThat(myProduct.getId())
        .as("New product [id] is not as expected")
        .isEqualTo(101);
//    all fields from request exist in response with same values
    softAssertions.assertThat(myProduct)
        /// compare fields of two different types
        .usingRecursiveComparison()
//        .ignoringFields("id")
        .ignoringFields("id")
        .as("New product does not have values from requested product")
        .isEqualTo(productModel);
    softAssertions.assertAll();
  }

}
