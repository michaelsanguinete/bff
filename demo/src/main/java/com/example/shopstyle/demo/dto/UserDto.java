package com.example.shopstyle.demo.dto;

import java.time.LocalDate;

import com.example.shopstyle.demo.entity.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;

    private String firstName;

    private String lastName;

    private Sex sex;

    private String cpf;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private String email;

    private Boolean active;

}
