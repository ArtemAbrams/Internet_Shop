package com.example.first.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequest {

    private String email;

    private String name;

    private String surname;

    private String password;



}
