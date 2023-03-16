package framework.models.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel {

  public Integer id;
  private String title;
  private Integer price;
  private Integer stock;
  private Integer rating;
  private List<String> images;
  private String thumbnail;
  private String description;
  private String brand;
  private String category;
  private Double discountPercentage;


}
