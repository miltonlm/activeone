package com.activeone.posts.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponse {
    private Integer id;
    private String nombre;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;
}
