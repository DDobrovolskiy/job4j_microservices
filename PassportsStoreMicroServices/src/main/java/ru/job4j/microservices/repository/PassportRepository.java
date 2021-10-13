package ru.job4j.microservices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.microservices.domain.Passport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    List<Passport> findAll();

    Optional<Passport> findBySerial(int serial);

    List<Passport> findByDateBefore(Date date);

    List<Passport> findByDateBetween(Date dateBegin, Date dateEnd);
}
