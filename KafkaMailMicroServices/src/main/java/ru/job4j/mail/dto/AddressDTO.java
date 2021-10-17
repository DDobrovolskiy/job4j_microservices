package ru.job4j.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private String country;
    private String city;
    private Long flatNumber;
}
