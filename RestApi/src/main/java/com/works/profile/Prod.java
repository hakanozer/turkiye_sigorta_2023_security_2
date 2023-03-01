package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("prod")
@Component
public class Prod implements IProfile {

    @Override
    public Map config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("rowCount", 20);
        hm.put("apiKey", "prod_21j34h12jk312k21j3");
        hm.put("baseUrl", "prod.turkiye.gov.tr");
        return hm;
    }

}
