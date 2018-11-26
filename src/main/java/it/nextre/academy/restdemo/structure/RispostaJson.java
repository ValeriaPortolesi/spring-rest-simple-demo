package it.nextre.academy.restdemo.structure;


import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString

public class RispostaJson {

    private int stato;
    private String messaggio;
    private Object response;
    private LocalDateTime generate = LocalDateTime.now();

}
