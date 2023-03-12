package models.login;

import lombok.Data;

@Data
public class LoginResponseModel {

  private Integer id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String gender;
  private String image;
  private String token;

}
