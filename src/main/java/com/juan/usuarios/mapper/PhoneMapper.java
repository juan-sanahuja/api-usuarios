package com.juan.usuarios.mapper;

import com.juan.usuarios.model.Phone;
import com.juan.usuarios.dto.PhoneDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhoneMapper {

    public static PhoneDTO toDTO(Phone phone){
        return PhoneDTO
                .builder()
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .number(phone.getNumber())
                .build();
    }

    public static Phone toEntity(PhoneDTO phone){
        return Phone
                .builder()
                .phone_id(UUID.randomUUID().toString())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .number(phone.getNumber())
                .build();
    }
}
