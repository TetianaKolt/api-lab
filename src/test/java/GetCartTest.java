import static framework.services.ShopServices.getCartById;

import java.util.List;
import java.util.stream.Collectors;
import framework.models.cart.CartModel;
import framework.models.cart.CartModel.ProductInCartModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class GetCartTest extends BaseTest {

  @Test
  public void getCartByIdTest() {
    int cartId = 1;

    final CartModel cartModel = getCartById(cartId);
//        RestAssured
//        .given()
//        .pathParams("cart_id", cartId)
//        .get(CART_BY_ID)
//        .then()
//        .statusCode(200)
//        .extract()
//        .body()
//        .as(CartModel.class);

    SoftAssertions softAssertions = new SoftAssertions();
    //id = 1
    softAssertions.assertThat(cartModel.getId())
        .as("[id] is not as expected " + cartId)
        .isEqualTo(cartId);

    //every product in [products] list has not null id
    List<ProductInCartModel> cartModelsList = cartModel.getProducts();

    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getId)
        .as("Cart contains products with null in [id]")
        .doesNotContainNull();

    //not null title
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getTitle)
        .as("Cart contains products with null in [title]")
        .doesNotContainNull();

    //not null price
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getPrice)
        .as("Cart contains products with null in [price]")
        .doesNotContainNull();

    //not null quantity
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getQuantity)
        .as("Cart contains products with null in [quantity]")
        .doesNotContainNull();

    //not null total
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getTotal)
        .as("Cart contains products with null in [total]")
        .doesNotContainNull();

    //not null discountPercentage
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getDiscountPercentage)
        .as("Cart contains products with null in [discount percentage]")
        .doesNotContainNull();

    //not null discountedPrice
    softAssertions.assertThat(cartModelsList)
        .map(ProductInCartModel::getDiscountedPrice)
        .as("Cart contains products with null in [discounted price]")
        .doesNotContainNull();

    //[total] of every product should calculate as [price] * [quantity] ???????????????????????????
    softAssertions.assertThat(cartModelsList)
        .map(pr -> pr.getPrice() * pr.getQuantity())
        .as("Cart contains wrong calculation in [total]: "
            + "it has to be [price] * [quantity]")
        .isEqualTo((cartModelsList
            .stream()
            .map(ProductInCartModel::getTotal)
            .collect(Collectors.toList())));

    //????????????????????????
    //[discountedPrice] of every product should calculate as [total] * [discountPercentage] - [total] (check rounding)
    softAssertions.assertThat(cartModelsList)
        .map(pr -> (int) Math.round(
            pr.getTotal() - (pr.getTotal() * pr.getDiscountPercentage() / 100)))
        .as("Cart contains wrong calculation in [discountedPrice]: "
            + "it has to be [total] * [discountPercentage] - [total]")
        .isEqualTo((cartModelsList
            .stream()
            .map(ProductInCartModel::getDiscountedPrice)
            .collect(Collectors.toList())));

    //general [total] should be sum of total of every product
    softAssertions.assertThat(cartModel.getTotal())
        .as("Cart contains wrong calculation in general [total]: "
            + "it has to contain the sum of all products [total]")
        .isEqualTo((cartModelsList
            .stream()
            .mapToInt(ProductInCartModel::getTotal).sum()));

    //general [discountedTotal] should be sum of discountedPrice of every product
    softAssertions.assertThat(cartModel.getDiscountedTotal())
        .as("Cart contains wrong calculation in [discounted total]: "
            + "it has to contain the sum of all products [discounted price]")
        .isEqualTo((cartModelsList
            .stream()
            .mapToInt(ProductInCartModel::getDiscountedPrice).sum()));

    //[userId] should be 97
    softAssertions.assertThat(cartModel.getUserId())
        .as("Cart contains wrong [user id]")
        .isEqualTo(97);

    //[totalProducts] should be equals to amount of products from the list
    softAssertions.assertThat(cartModel.getTotalProducts())
        .as("Cart contains wrong calculation in [total products]: "
            + "it has to contain the sum of all products in the list")
        .isEqualTo((cartModelsList.size()));

    //[totalQuantity] should be equals to sum of quantity of every product
    softAssertions.assertThat(cartModel.getTotalQuantity())
        .as("Cart contains wrong calculation in [total quantity]: "
            + "it has to contain the sum of every product quantity")
        .isEqualTo((cartModelsList
            .stream()
            .mapToInt(ProductInCartModel::getQuantity).sum()));

    softAssertions.assertAll();
  }

}
