package kg.geektech.geektechfinalprojectbackend.util;

import kg.geektech.geektechfinalprojectbackend.exception.mail.MailException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CommonUtil {
    @Value("${custom.cors.domain}")
    String corsDomain;

    public String buildConfirmEmailText(String token) {
        try {
            Document htmlFile = Jsoup.parse(
                    new File(this.getClass().getClassLoader().getResource("assets/EmailTemplate.html").getFile()),
                    StandardCharsets.UTF_8.name()
            );

            return htmlFile.toString()
                    .replace("%url", corsDomain)
                    .replace("%token", String.join("/", corsDomain, token));
        } catch (IOException e) {
            throw new MailException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
