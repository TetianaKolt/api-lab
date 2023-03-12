package endpoints;

import lombok.Data;
import lombok.Getter;

@Data
public class Endpoints {

  public static String AUTH_LOGIN = "/auth/login";
  public static String PRODUCT_BY_ID = "/products/{product_id}";
  public static String PRODUCTS_SEARCH = "/products/search";

}