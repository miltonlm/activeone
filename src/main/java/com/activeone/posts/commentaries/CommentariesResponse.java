package com.activeone.posts.commentaries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentariesResponse {
    private Integer id;
    private Integer postsId;
    private String contenido;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;
}
