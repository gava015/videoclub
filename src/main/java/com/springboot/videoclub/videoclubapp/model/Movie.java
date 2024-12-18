package com.springboot.videoclub.videoclubapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private String id;
    private String title;
    private String type;

}
