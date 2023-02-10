package kg.geektech.dostavkakgbackend.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kg.geektech.dostavkakgbackend.exception.mail.MailException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailSenderUtil {
    @Value("${spring.mail.username}")
    String senderEmail;
    @Value("${custom.project.name}")
    String projectName;
    final JavaMailSender javaMailSender;

    public void send(String email, String title, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject(
                    String.join(" - ", projectName, title),
                    "UTF-8"
            );

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    message,
                    true,
                    StandardCharsets.UTF_8.name()
            );

            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailException(e.getMessage());
        }
    }
}
