package co.edu.uniquindio.ingesis.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestLogin {
    private String email;
    private String password;
}
