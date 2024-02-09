package com.activeone.posts.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsRequest {
    @NotNull
    @NotBlank
    private String titulo;
    @NotNull
    @NotBlank
    private String contenido;
}
