package ru.job4j.microservices.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassportDTO {
    private long id;
    private int serial;
    private String nameOwner;
    private Date date;
}
