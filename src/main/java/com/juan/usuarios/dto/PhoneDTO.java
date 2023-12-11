package com.juan.usuarios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    @NotNull(message = "el campo number es obligatorio")
    private Integer number;
    @NotNull(message = "el campo citycode es obligatorio")
    @JsonProperty("citycode")
    private Integer cityCode;
    @NotNull(message = "el campo countrycode es obligatorio")
    @JsonProperty("countrycode")
    private Integer countryCode;
}
