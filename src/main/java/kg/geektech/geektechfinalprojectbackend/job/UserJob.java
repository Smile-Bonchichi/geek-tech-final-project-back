package kg.geektech.geektechfinalprojectbackend.job;

import kg.geektech.geektechfinalprojectbackend.entity.user.User;
import kg.geektech.geektechfinalprojectbackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJob {
    final UserRepository userRepository;

    @Autowired
    public UserJob(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUsersInDataBase() {
        List<User> users = userRepository.findAllByEnabledFalse();

//        users.stream()
//                .filter(x -> x.getCreatedAt().isBefore())
    }
}
