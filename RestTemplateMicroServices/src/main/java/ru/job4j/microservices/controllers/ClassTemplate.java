package ru.job4j.microservices.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.microservices.DTO.PassportDTO;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/passports")
public class ClassTemplate {
    private final RestTemplate restTemplate;

    @Value("${api-url}")
    private String url;

    public ClassTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        System.out.println("API-URL: " + url);
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return restTemplate.optionsForAllow(url + "/passports/unavaliabe");
    }

    @GetMapping
    public List findAll() {
        return restTemplate.getForEntity(url + "/passports", List.class).getBody();
    }

    @GetMapping("/{id}")
    public PassportDTO findById(@PathVariable long id) {
        return restTemplate.getForEntity(url + "/passports/" + id, PassportDTO.class).getBody();
    }

    @PostMapping
    public PassportDTO save(@RequestBody PassportDTO passport) {
        return restTemplate.postForEntity(url + "/passports", passport, PassportDTO.class).getBody();
    }

    @PutMapping
    public void update(@RequestBody PassportDTO passport) {
       restTemplate.put(url + "/passports", passport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        restTemplate.delete(url + "/passports/" + id);
    }

    @GetMapping("/serial/{serial}")
    public PassportDTO findBySerial(@PathVariable int serial) {
        return restTemplate.getForEntity(url + "/passports/serial/" + serial, PassportDTO.class).getBody();
    }

    @GetMapping("/unavaliabe")
    public List findPassportIsUnavaliabe() {
        return restTemplate.getForEntity(url + "/passports/unavaliabe", List.class).getBody();
    }

    @GetMapping("/find-replaceable")
    public List findPassportIsNeededReplace() {
        return restTemplate.getForEntity(url + "/passports/find-replaceable", List.class).getBody();
    }

}
