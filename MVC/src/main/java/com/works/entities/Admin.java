package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(length = 300)
    @Length(min = 5, max = 300)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(length = 500)
    @NotEmpty
    @NotNull
    private String password;

    private String remember;

    @Max(100)
    @Min(18)
    private Integer age;

}
