package kg.geektech.dostavkakgbackend.job;

import kg.geektech.dostavkakgbackend.entity.user.User;
import kg.geektech.dostavkakgbackend.repository.UserRepository;
import kg.geektech.dostavkakgbackend.util.CommonUtil;
import kg.geektech.dostavkakgbackend.util.MailSenderUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJob {
    final UserRepository userRepository;
    final MailSenderUtil mailSenderUtil;
    final CommonUtil commonUtil;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Almaty")
    public void deleteUsersInDataBase() {
        List<User> users = userRepository.findAllByExpiredConfirmToken();

        if (!users.isEmpty())
            users.forEach(x -> {
                        mailSenderUtil.send(
                                x.getEmail(),
                                "Удаление учетной записи",
                                commonUtil.buildConfirmEmailText("")
                        );

                        userRepository.delete(x);
                    }
            );
    }
}
