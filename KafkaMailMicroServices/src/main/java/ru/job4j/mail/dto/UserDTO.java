package ru.job4j.mail.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long age;
    private String name;
    private AddressDTO address;
}
