package com.usuarios.api.schedule;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.usuarios.api.model.Cliente;
import com.usuarios.api.repository.ClienteRepository;

@Component
@EnableScheduling
public class AniversarioScheduler {

    @Scheduled(cron = CRON_EXPRESSION, zone = TIME_ZONE)
    public void enviarNotificacao(){

        clienteRepository.findByDataNascimento(LocalDate.now()).forEach(cliente -> enviarEmail(cliente));
        
    }

    public void enviarEmail(Cliente cliente){
        SimpleMailMessage email = new SimpleMailMessage();

        email.setSubject("Uma mensagem para voce");
        email.setFrom(EMAIL_SENDER);
        email.setTo(cliente.getUsuario().getEmail());
        email.setText("Feliz aniversario");

        javaMailSender.send(email);
    }

    private final String CRON_EXPRESSION = "0 50 16 * * *";

    private final String TIME_ZONE = "America/Sao_Paulo";

    @Value("${spring.mail.sender}")
    private String EMAIL_SENDER;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JavaMailSender javaMailSender;
}
