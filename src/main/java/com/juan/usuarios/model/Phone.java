package com.juan.usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phone")
public class Phone {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private String phone_id;
    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

   // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  //  @JoinColumn(name = "user_id")
   // private User user;
}
