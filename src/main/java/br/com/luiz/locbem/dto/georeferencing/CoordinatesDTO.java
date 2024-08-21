package br.com.luiz.locbem.dto.georeferencing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesDTO {
    private double latitude;
    private double longitude;
}
