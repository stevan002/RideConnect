package com.rideconnect.server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.rideconnect.server.constant.ErrorMessage.NOT_BLANK;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = NOT_BLANK)
    private String email;

    @NotBlank(message = NOT_BLANK)
    private String password;
}
