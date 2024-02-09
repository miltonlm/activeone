package com.activeone.posts.commentaries;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CommentariesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer postsId;
    private String contenido;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;
}
