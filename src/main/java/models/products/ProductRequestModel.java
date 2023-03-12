package models.products;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductRequestModel {

//  public Integer id;
  private String title;
  private Integer price;
  private Integer stock;
  private Integer rating;
  private List<String> images;
  private String thumbnail;
  private String description;
  private String brand;
  private String category;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRequestModel that = (ProductRequestModel) o;
    return Objects.equals(title, that.title) && Objects.equals(price, that.price)
        && Objects.equals(stock, that.stock) && Objects.equals(rating,
        that.rating) && Objects.equals(images, that.images) && Objects.equals(
        thumbnail, that.thumbnail) && Objects.equals(description, that.description)
        && Objects.equals(brand, that.brand) && Objects.equals(category,
        that.category);
  }

}
