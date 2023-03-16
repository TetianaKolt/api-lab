import static framework.endpoints.Endpoints.PRODUCTS;
import static framework.services.ShopServices.getProducts;

import framework.models.products.ProductResponseModel;
import io.restassured.RestAssured;
import framework.models.products.ProductModel;
import framework.models.products.ProductDeleteResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class ProductListTest extends BaseTest {

  @Test
  public void checkProductList() {
    ProductResponseModel products = getProducts();

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
