package com.blogapplication.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer postID;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto categoryDto;

    private UserDto userDto;

    private List<CommentDto> comments=new ArrayList<>();
}
