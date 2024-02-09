package com.activeone.posts.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponse {
    private Integer id;
    private String titulo;
    private String contenido;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;
}
