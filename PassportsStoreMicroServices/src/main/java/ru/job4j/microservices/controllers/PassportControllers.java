package ru.job4j.microservices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.microservices.domain.Passport;
import ru.job4j.microservices.services.PassportServices;

import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportControllers {
    private final PassportServices passportServices;

    public PassportControllers(PassportServices passportServices) {
        this.passportServices = passportServices;
    }

    @GetMapping
    public List<Passport> findAll() {
        return passportServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@PathVariable long id) {
        var passport = passportServices.findById(id);
        return new ResponseEntity<>(
                passport.orElse(new Passport()),
                passport.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public Passport save(@RequestBody Passport passport) {
        return passportServices.save(passport);
    }

    @PutMapping
    public void update(@RequestBody Passport passport) {
        passportServices.save(passport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        passportServices.delete(id);
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<Passport> findBySerial(@PathVariable int serial) {
        var passport = passportServices.findBySerial(serial);
        return new ResponseEntity<>(
                passport.orElse(new Passport()),
                passport.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findPassportIsUnavaliabe() {
        return passportServices.findPassportIsUnavaliabe();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findPassportIsNeededReplace() {
        return passportServices.findPassportIsNeededReplace();
    }
}
