package com.wilfred.security.springsecurity.listeners;

import com.wilfred.security.springsecurity.model.Token;
import com.wilfred.security.springsecurity.model.TokenType;
import com.wilfred.security.springsecurity.model.User;
import com.wilfred.security.springsecurity.repository.TokenRepository;
import com.wilfred.security.springsecurity.service.JWTService;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;


@RequiredArgsConstructor
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    private final JavaMailSender emailSender;

    private final MessageSource messages;

    private final Environment env;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        var token = jwtService.generateToken(user);
        log.info("TOKEN::::::::::::::::::::::::::::: " + token);
        saveToken(user, token);
        constructEmailMessage(event, user, token);
    }

    private void saveToken(User user, String token) {
        var savedToken = Token.builder().user(user).token(token).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(savedToken);
    }

    private void constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/api/v1/registration/confirmation?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", event.getLocale());
        log.info("Message :::::::::::::::::: " + message);

        sendSimpleMessage(recipientAddress, subject, message + " \r\n" + confirmationUrl, env.getProperty("support.email"));

    }

    public void sendSimpleMessage(String to, String subject, String text, String from) {
        try {
            log.info("TO>>>>>>>>>>>>> " + to);
            log.info("SUBJECT>>>>>>>>>>>>> " + subject);
            log.info("TEXT>>>>>>>>>>>>> " + text);
            log.info("FROM>>>>>>>>>>>>> " + from);


            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setSubject(subject);

     /*   // Adding the attachment
        FileSystemResource file = new FileSystemResource(new File(file));
        mimeMessageHelper.addAttachment(file.getFilename(), file);*/

            // Sending the mail
            log.info("About to send email::::::::::::::::");
            emailSender.send(mimeMessage);
            log.info("email sent::::::::::::::::");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
