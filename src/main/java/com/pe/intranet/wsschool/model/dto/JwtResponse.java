package com.pe.intranet.wsschool.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//PASO 9 -JWT
public record JwtResponse(
                @JsonProperty(value = "access_token") String accessToken,
                @JsonProperty(value = "AuthType") String AuthType) {

}
