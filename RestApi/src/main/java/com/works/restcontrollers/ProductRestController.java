package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.profile.IProfile;
import com.works.services.DummyService;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService service;
    final DummyService dummyService;
    final IProfile iProfile;

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return service.list();
    }

    @GetMapping("/auth")
    public ResponseEntity auth() {
        return dummyService.auth();
    }

    @GetMapping("/xml")
    public ResponseEntity xml() {
        return service.xml();
    }

    @GetMapping("/profile")
    public Map profile() {
        return iProfile.config();
    }


}
