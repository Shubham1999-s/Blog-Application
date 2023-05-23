package com.Intellijspringproject.payload;

import com.Intellijspringproject.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id;
    @NotEmpty(message = "It is Mandatory")
    @Size(min=3,message="Comment name should contain atleast 3 characters")
    private String name;
    private String email;
    @NotEmpty
    @Size(min=10,message="Comment body should contain atleast 10 characters")
    private String body;
    //private Post post;


}
