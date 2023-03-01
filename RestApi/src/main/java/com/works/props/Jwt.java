package com.works.props;

import lombok.Data;

@Data
public class Jwt {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
    private String token;
}
