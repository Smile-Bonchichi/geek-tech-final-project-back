package kg.geektech.dostavkakgbackend.util;

import kg.geektech.dostavkakgbackend.exception.file.FileException;
import kg.geektech.dostavkakgbackend.exception.mail.MailException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class CommonUtil {
    @Value("${custom.cors.domain}")
    String corsDomain;

    public String buildConfirmEmailText(String token) {
        try {
            URL url = this.getClass().getClassLoader().getResource("assets/EmailTemplate.html");

            if (url == null)
                throw new FileException("EmailTemplate.html не найден");

            Document htmlFile = Jsoup.parse(
                    new File(url.getFile()),
                    StandardCharsets.UTF_8.name()
            );

            return htmlFile.toString()
                    .replace("%url", corsDomain)
                    .replace("%token", String.join("/confirm/", corsDomain, token));
        } catch (IOException e) {
            throw new MailException(e.getMessage());
        }
    }
}
