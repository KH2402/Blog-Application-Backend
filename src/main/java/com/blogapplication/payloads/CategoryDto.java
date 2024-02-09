package com.blogapplication.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min = 4, message = "Minimum size of title is 4!!")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Minimum size of description is 10!!")
    private String categoryDescription;

}
