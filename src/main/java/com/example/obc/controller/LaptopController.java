package com.example.obc.controller;

import com.example.obc.entiti.Laptop;
import com.example.obc.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping(value="/laptop")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping(value="/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if(laptop.isPresent())
            return ResponseEntity.ok(laptop.get());
        else
            return ResponseEntity.notFound().build();
    }
    @PostMapping(value="/laptop")
    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            log.warn("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }
    @PutMapping(value = "/laptop")
    public Laptop update(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @DeleteMapping(value = "/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/laptop")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
