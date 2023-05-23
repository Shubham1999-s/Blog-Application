package com.Intellijspringproject.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data//It called as pojo(plain old java object) class where only generate encapsulation means getters ad setterrs
public class PostDto {
  private long id;
  @NotEmpty(message = "It is Mandatory")
  @Size(min=2,message="Post title should have atleast two characters")
  private String title;
  @NotEmpty
  @Size(min = 10, message = "Post description should have at least 10 characters")

  private String description;
  @NotEmpty
  @Size(min = 10, message = "Post contents should have at least 10 characters")

  private String content;
}
