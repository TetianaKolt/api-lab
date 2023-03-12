package models.products;

import java.util.List;
import lombok.Data;

@Data
public class ProductResponseModel {

  public List<Product> products;
  public Integer total;
  public Integer skip;
  public Integer limit;

  @Data
  public static class Product {

    public Integer id;
    public String title;
    public String description;
    public Integer price;
    public Double discountPercentage;
    public Double rating;
    public Integer stock;
    public String brand;
    public String category;
    public String thumbnail;
    public List<String> images;
  }


}
