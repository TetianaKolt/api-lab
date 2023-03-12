import static endpoints.Endpoints.PRODUCT_BY_ID;

import io.restassured.RestAssured;
import models.products.ProductResponseModel.Product;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class GetProductByIDTest extends BaseTest {

  @Test
  public void getSingleProductById() {
    Product product = RestAssured
        .given()
        .pathParams("product_id", 10)
        .get(PRODUCT_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(Product.class);

    SoftAssertions softAssertions = new SoftAssertions();
//    id = 10
    softAssertions.assertThat(product.getId())
        .as("Product [id] is not as expected")
        .isEqualTo(10);

//    title = HP Pavilion 15-DK1056WM
    softAssertions.assertThat(product.getTitle())
        .as("Product [title] is not as expected")
        .isEqualTo("HP Pavilion 15-DK1056WM");

//    description = HP Pavilion 15-DK1056WM Gaming Laptop 10th Gen Core i5,
//    8GB, 256GB SSD, GTX 1650 4GB, Windows 10
    String expectedDescription = "HP Pavilion 15-DK1056WM Gaming Laptop 10th Gen Core i5, "
        + "8GB, 256GB SSD, GTX 1650 4GB, Windows 10";
    softAssertions.assertThat(product.getDescription())
        .as("Product [description] is not as expected")
        .isEqualTo(expectedDescription);
//    price = 1099
    softAssertions.assertThat(product.getPrice())
        .as("Product [price] differs from expected")
        .isEqualTo(1099);

//    discountPercentage = 6.17
    softAssertions.assertThat(product.getDiscountPercentage())
        .as("Product [discountPercentage] is not as expected")
        .isEqualTo(6.17);

//    rating = 4.43
    softAssertions.assertThat(product.getRating())
        .as("Product [rating] is not as expected")
        .isEqualTo(4.43);

//    stock = 89
    softAssertions.assertThat(product.getStock())
        .as("[stock] is not as expected")
        .isEqualTo(89);

//    brand = HP Pavilion
    softAssertions.assertThat(product.getBrand())
        .as("Product [brand] is not as expected")
        .isEqualTo("HP Pavilion");

//        category = laptops
    softAssertions.assertThat(product.getCategory())
        .as("Product [category] is not as expected")
        .isEqualTo("laptops");
//    images size is 4
    softAssertions.assertThat(product.getImages().size())
        .as("Quantity of [images] is not as expected")
        .isEqualTo(4);

    softAssertions.assertAll();
  }


}
