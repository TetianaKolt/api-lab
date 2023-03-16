package framework.models.products;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ProductDeleteResponseModel {

  private Integer id;
  private String title;
  private String description;
  private Integer price;
  private Integer discountPercentage;
  private Double rating;
  private Integer stock;
  private String brand;
  private String category;
  private String thumbnail;
  private List<String> images;
  private Boolean isDeleted;
  private Date deletedOn;



}
