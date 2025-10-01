package com.pe.intranet.wsschool.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 8 -JWT
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequest {

    private String username;

    private String password;

}
