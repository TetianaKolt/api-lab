package models.cart;

import java.util.List;
import lombok.Data;

@Data
public class CartModel {
  private Integer id;
  public List<ProductInCartModel> products;
  private Integer  total;
  private Integer  discountedTotal;
  private Integer  userId;
  private Integer  totalProducts;
  private Integer  totalQuantity;

  @Data
  public static class ProductInCartModel{
    private Integer  id;
    public String title;
    private Integer  price;
    private Integer  quantity;
    private Integer  total;
    private Double  discountPercentage;
    private Integer  discountedPrice;
  }


}


