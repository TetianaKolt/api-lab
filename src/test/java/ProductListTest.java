import io.restassured.RestAssured;
import models.products.ProductResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductListTest extends BaseTest {

  @Test
  public void checkProductList() {
    ProductResponseModel products = RestAssured.given()
        .get("/products")
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductResponseModel.class);

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(products.getTotal())
        .as("[total] is not as expected")
        .isEqualTo(100);

    softAssertions.assertThat(products.getSkip())
        .as("[skip] is not as expected")
        .isEqualTo(0);

    softAssertions.assertThat(products.getLimit())
        .as("[limit] is not as expected")
        .isEqualTo(30);

    //every product has not null [id]
    softAssertions.assertThat(
            products.getProducts().stream().allMatch(product -> product.getId() != null))
        .as("[id] of the product(s) is null")
        .isTrue();

    //every product has not null or not empty [title]
    softAssertions.assertThat(products.getProducts().stream()
            .allMatch((product -> product.getTitle() != null || !product.getTitle().isEmpty())))
        .as("Product(s) have(has) empty [title]")
        .isTrue();

    //every product has not null [price]
    softAssertions.assertThat(products.getProducts().stream()
            .allMatch((product -> product.getPrice() != null)))
        .as("[price] of products(s) is null")
        .isTrue();

    //every product has not at least one [image]
    softAssertions.assertThat(products.getProducts().stream()
            .allMatch(product -> product.getImages().size() > 0))
        .as("Product(s) doesn't have [image]");

    softAssertions.assertAll();
  }

}
