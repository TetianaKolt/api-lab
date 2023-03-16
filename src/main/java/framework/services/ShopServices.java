package framework.services;

import static framework.endpoints.Endpoints.AUTH_LOGIN;
import static framework.endpoints.Endpoints.CART_BY_ID;
import static framework.endpoints.Endpoints.PRODUCTS;
import static framework.endpoints.Endpoints.PRODUCTS_ADD;
import static framework.endpoints.Endpoints.PRODUCTS_SEARCH;
import static framework.endpoints.Endpoints.PRODUCT_BY_ID;
import static io.restassured.RestAssured.given;

import framework.models.cart.CartModel;
import framework.models.login.LoginRequestModel;
import framework.models.login.LoginResponseModel;
import framework.models.products.ProductDeleteResponseModel;
import framework.models.products.ProductModel;
import framework.models.products.ProductResponseModel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class ShopServices {

  private static ValidatableResponse getProductByIdResponse(int id) {
    return given()
        .pathParams("product_id", id)
        .get(PRODUCT_BY_ID)
        .then();
  }
  public static LoginResponseModel logIn(LoginRequestModel requestModel) {
    return given()
            .body(requestModel)
            .post(AUTH_LOGIN)
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(LoginResponseModel.class);
  }

  public static ProductModel updateProductById(int id, ProductModel productBodyToUpdate) {
    return given()
            .pathParams("product_id", id)
            .body(productBodyToUpdate)
            .put(PRODUCT_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(ProductModel.class);
  }


  public static ProductResponseModel getProducts() {
    return given()
        .get(PRODUCTS)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductResponseModel.class);
  }

  public static ProductResponseModel searchProductsByQueryParam(String queryName,
      String searchName) {
    return given()
        .queryParam(queryName, searchName)
        .get(PRODUCTS_SEARCH)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductResponseModel.class);
  }

  public static ProductModel getProductById(int id) {
    return getProductByIdResponse(id)
        .statusCode(200)
        .extract()
        .body()
        .as(ProductModel.class);
  }

  public static ProductModel addNewProduct(ProductModel productModel) {
    return RestAssured.given()
        .body(productModel)
        .post(PRODUCTS_ADD)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductModel.class);
  }

  public static ProductDeleteResponseModel deleteProduct(int id) {
    return given()
        .pathParam("product_id", id)
        .delete(PRODUCT_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(ProductDeleteResponseModel.class);
  }

  public static CartModel getCartById(int id) {
    return given()
        .pathParams("cart_id", id)
        .get(CART_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(CartModel.class);
  }

}
