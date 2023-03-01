package com.works.services;

import com.works.entities.Product;
import com.works.props.Currency;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        repository.save(product);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", product);
        cacheManager.getCache("product").clear();
        return new ResponseEntity( hm, HttpStatus.OK );
    }

    @Cacheable("product")
    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", repository.findAll());
        return new ResponseEntity( hm, HttpStatus.OK );
    }

    public ResponseEntity xml() {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(stData, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            List<Currency> ls = new ArrayList<>();
            for (Element item : elements) {
                String currencyName = item.getElementsByTag("CurrencyName").text();
                String forexBuying = item.getElementsByTag("ForexBuying").text();
                String forexSelling = item.getElementsByTag("ForexSelling").text();
                Currency currency = new Currency(currencyName, forexBuying, forexSelling);
                ls.add(currency);
            }
            hm.put("result", ls);
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put("status", false);
        }
        return new ResponseEntity( hm, HttpStatus.OK );
    }

}
