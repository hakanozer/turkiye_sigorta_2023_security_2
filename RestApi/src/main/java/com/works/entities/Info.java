package com.works.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Info {

    public Info() { }

    public Info(String url, String name, String roles, String agent, String sessionId, String ipAddress, String now) {
        this.url = url;
        this.name = name;
        this.roles = roles;
        this.agent = agent;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
        this.now = now;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String name;
    private String roles;
    private String agent;
    private String sessionId;
    private String ipAddress;
    private String now;


}
