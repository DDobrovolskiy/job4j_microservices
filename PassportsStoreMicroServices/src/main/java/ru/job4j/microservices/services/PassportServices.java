package ru.job4j.microservices.services;

import org.springframework.stereotype.Service;
import ru.job4j.microservices.domain.Passport;
import ru.job4j.microservices.repository.PassportRepository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PassportServices {
    private final PassportRepository passportRepository;

    public PassportServices(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @PostConstruct
    public void init() {
        List<Passport> passports = List.of(
                new Passport(0, 1234, "TestOwner_Old", Date.from(Instant.now().minus(10, ChronoUnit.DAYS))),
                new Passport(0, 2345, "TestOwner_Now", Date.from(Instant.now().plus(10, ChronoUnit.DAYS))),
                new Passport(0, 3456, "TestOwner_Young", Date.from(Instant.now().plus(360, ChronoUnit.DAYS)))
        );
        passports.forEach(this::save);
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public Optional<Passport> findById(long id) {
        return passportRepository.findById(id);
    }

    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    public void delete(long id) {
        passportRepository.deleteById(id);
    }

    public Optional<Passport> findBySerial(int serial) {
        return passportRepository.findBySerial(serial);
    }

    public List<Passport> findPassportIsUnavaliabe() {
        return passportRepository.findByDateBefore(new Date());
    }

    public List<Passport> findPassportIsNeededReplace() {
        return passportRepository.findByDateBetween(
                new Date(),
                Date.from(Instant.now().plus(90, ChronoUnit.DAYS)));
    }
}
