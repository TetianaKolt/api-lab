package endpoints;

import lombok.Data;
import lombok.Getter;

public class Endpoints {

  public static String AUTH_LOGIN = "/auth/login";
  public static String PRODUCT_BY_ID = "/products/{product_id}";
  public static String PRODUCTS_SEARCH = "/products/search";
  public static String PRODUCTS_ADD = "/products/add";
  public static String PRODUCTS = "/products";
  public static String CART_BY_ID = "/carts/{cart_id}";



}
