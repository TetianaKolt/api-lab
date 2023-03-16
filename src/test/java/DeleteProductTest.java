import static framework.endpoints.Endpoints.PRODUCT_BY_ID;
import static framework.services.ShopServices.deleteProduct;

import io.restassured.RestAssured;
import framework.models.products.ProductDeleteResponseModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class DeleteProductTest extends BaseTest {

  @Test
  public void deleteProductTest() {
    ProductDeleteResponseModel productResponseModel =
         deleteProduct(33);

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
