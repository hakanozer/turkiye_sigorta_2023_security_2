package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("dev")
@Component
public class Dev implements IProfile {

    @Override
    public Map config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("rowCount", 50);
        hm.put("apiKey", "dev_21j34h12jk312k21j3");
        hm.put("baseUrl", "dev.turkiye.gov.tr");
        return hm;
    }

}
