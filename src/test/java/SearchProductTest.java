import static framework.services.ShopServices.searchProductsByQueryParam;

import framework.models.products.ProductModel;
import framework.models.products.ProductResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

  @Test
  public void searchProductTest() {
    ProductResponseModel products = searchProductsByQueryParam("q",
        "Samsung");

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
