package ru.job4j.mail.contollers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.mail.dto.UserDTO;

@EnableKafka
@RestController
@RequestMapping("/console")
public class ConsoleController {

    @KafkaListener(topics="msg")
    public void msgListener(ConsumerRecord<Integer, UserDTO> message){
        System.out.println("Message: topic = " + message.topic());
        System.out.println("Message: key = " + message.key());
        System.out.println("Message: value = " + message.value());
    }

}
