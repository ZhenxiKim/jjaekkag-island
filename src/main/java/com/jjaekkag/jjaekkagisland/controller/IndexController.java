package com.jjaekkag.jjaekkagisland.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@RestController
public class IndexController {
    public static final String INDEX_URI = "/index";
    @GetMapping(INDEX_URI)
    public ResponseEntity<Void> index() {
        return ResponseEntity.ok().build();
    }
}
