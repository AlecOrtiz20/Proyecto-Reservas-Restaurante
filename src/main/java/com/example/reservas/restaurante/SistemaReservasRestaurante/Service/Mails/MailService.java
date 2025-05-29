package com.example.reservas.restaurante.SistemaReservasRestaurante.Service.Mails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendMailClient(String email, String reservationDate, String location, String clientName, String numberOfPeople) throws MessagingException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("reservtionDate", reservationDate);
        context.setVariable("location", location);
        context.setVariable("clientName", clientName);
        context.setVariable("numberOfPeople", numberOfPeople);

        String templateContent = this.templateEngine.process("email-reservation-client", context);

        //Email al cual va dirigida la notificacion
        messageHelper.setTo(email);

        //Asunto del email
        messageHelper.setSubject("Reservacion confirmada");

        messageHelper.setText(templateContent, true);

        this.mailSender.send(mimeMessage);
    }
}
