package com.springboot.videoclub.videoclubapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentItem {
    private String movieId;
    private int days;

}
