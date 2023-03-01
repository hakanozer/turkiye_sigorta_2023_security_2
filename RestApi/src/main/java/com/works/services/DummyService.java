package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.Jwt;
import com.works.props.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DummyService {

    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public ResponseEntity auth() {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
        String url = "https://dummyjson.com/auth/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername("kminchelle");
        jwtUser.setPassword("0lelplR");
        String stObj = objectMapper.writeValueAsString(jwtUser);

        HttpEntity httpEntity = new HttpEntity(stObj, headers);
        ResponseEntity<Jwt> responseEntity = restTemplate.postForEntity(url, httpEntity, Jwt.class );
        Jwt jwt = responseEntity.getBody();
        System.out.println( jwt.getToken() );
        System.out.println( jwt.getEmail() );
        return responseEntity;
        }catch (Exception ex) {}
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
