package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.service.CircularServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "circular")
public class CircularController {

    @Autowired
    private CircularServices circularServices;

    @GetMapping("/all")
    public List<Circular> getAll() {
        return circularServices.getAll();
    }

    @GetMapping("/{id}")
    public Circular get(@PathVariable Long id) {
        return circularServices.get(id);
    }

    @PostMapping("/save")
    public Circular saveOrUpdate(@Valid @RequestBody Circular circular) {
        return  circularServices.saveOrUpdate(circular);
    }
}