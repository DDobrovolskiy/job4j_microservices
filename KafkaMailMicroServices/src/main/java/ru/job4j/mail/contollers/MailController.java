package ru.job4j.mail.contollers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.mail.dto.AddressDTO;
import ru.job4j.mail.dto.UserDTO;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/msg")
public class MailController {
    private final KafkaTemplate<Integer, UserDTO> kafkaTemplate;
    private final List<String> randomValues;

    public MailController(KafkaTemplate<Integer, UserDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        randomValues = List.of("Hello", "End", "Test");
    }

    @PostMapping
    public void send(Integer key, UserDTO value) {
        System.out.println("key: " + key);
        System.out.println("value: " + value);
        ListenableFuture<SendResult<Integer, UserDTO>> future = kafkaTemplate.send("msg", key, value);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    @Scheduled(fixedRate = 3000)
    public void rateSend() {
        int rnd = new Random().nextInt(3);
        var user = new UserDTO();
        user.setName("Dima");
        user.setAge(31L);
        user.setAddress(new AddressDTO("Russia", "Ekaterinburg", 308L));
        send(rnd, user);
    }
}
