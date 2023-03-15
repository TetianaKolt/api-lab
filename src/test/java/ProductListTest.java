import io.restassured.RestAssured;
import models.products.ProductModel;
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
    softAssertions.assertThat(products.getProducts())
        .map(ProductModel::getId)
        .as("[id] of the product(s) is null")
        .doesNotContainNull();

    //every product has not null or not empty [title]
    softAssertions.assertThat(products.getProducts())
        .map(ProductModel::getTitle)
        .as("Product(s) have(has) empty [title]")
        .doesNotContainNull()
        .isNotEmpty();

    //every product has not null [price]
    softAssertions.assertThat(products.getProducts())
        .map(ProductModel::getPrice)
        .as("[price] of products(s) is null")
        .doesNotContainNull();

    //every product has not at least one [image]
    softAssertions.assertThat(products.getProducts())
        .map(ProductModel::getImages)
        .as("Product(s) doesn't have [image]")
        .allMatch(pics -> !pics.isEmpty());

    softAssertions.assertAll();
  }

}
