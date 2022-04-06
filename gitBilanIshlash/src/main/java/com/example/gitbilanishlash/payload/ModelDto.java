package com.example.gitbilanishlash.payload;

import com.example.gitbilanishlash.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelDto {
    private String number;
    private String floor;
    private Double size;
    private Long hodelId;

}
