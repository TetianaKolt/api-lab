package framework.models.products;

import java.util.List;
import lombok.Data;

@Data
public class ProductResponseModel {

  private List<ProductModel> products;
  private Integer total;
  private Integer skip;
  private Integer limit;
  private Boolean isDeleted;
  private String deletedOn;

}
