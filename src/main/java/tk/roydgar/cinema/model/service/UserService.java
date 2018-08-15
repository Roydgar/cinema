package tk.roydgar.cinema.model.service;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.cinema.model.entity.User;
import tk.roydgar.cinema.model.entity.temporary.LoginData;
import tk.roydgar.cinema.model.repository.UserRepository;
import tk.roydgar.cinema.util.HashUtil;
import tk.roydgar.cinema.util.SmtpMailSender;
import java.util.Optional;
import static org.springframework.http.HttpStatus.*;
import static tk.roydgar.cinema.util.constants.HeaderMessages.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private Logger logger;
    private SmtpMailSender smtpMailSender;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return  ResponseEntity.ok(user.get());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(CONFLICT).header(HEADER_KEY, USER_EXIST).build();
        }

        user.setPassword(HashUtil.hash(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        User savedUser = userRepository.save(user);

//        smtpMailSender.send(user.getEmail(),
//                "Workshop Master: Email Confirmation"
//                ,   "Please, confirm your email following next link: " +
//                        "https://workshop-master-server.herokuapp.com/user/confirmation/"
//                        + savedUser.getId() + "/" +
//                        savedUser.getName());

        logger.info("register() call; SUCCESS; savedUser = " + savedUser);
        return ResponseEntity.ok(savedUser);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ResponseEntity<?> login(LoginData loginData) {
        String email = loginData.getEmail().toLowerCase();
        String password = loginData.getPassword();

        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            logger.info("login() call; FAILURE ; user with given email doesn't exist; " +
                    "loginData = " + loginData);
            return ResponseEntity.status(UNAUTHORIZED).header(HEADER_KEY, USER_EMAIL_DOESNT_EXIST).build();
        }

        User loggedUser = user.get();

        if (loggedUser.getRole() == User.Role.GUEST) {
            logger.info("login() call; FAILURE ; user didn't confirm his email; " +
                    "loginData = " + loginData +"l; user = " + loggedUser);
            return ResponseEntity.status(UNAUTHORIZED).header(HEADER_KEY, USER_EMAIL_WASNT_CONFIRMED).build();
        }

        if (HashUtil.check(password, loggedUser.getPassword())) {
            logger.info("login() call; SUCCESS ; loginData = " + loginData +"; loggedUser = " + loggedUser);
            return ResponseEntity.ok(loggedUser);
        }

        logger.info("login() call; FAILURE ; password is incorrect;" +
                "loginData = " + loginData);
        return ResponseEntity.status(UNAUTHORIZED).header(HEADER_KEY, USER_WRONG_PASSWORD).build();
    }


    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> confirmEmail(Long userId, String hash) {
        Optional<User> tempUser = userRepository.findById(userId);

        if (!tempUser.isPresent()) {
            logger.info("confirmEmail() call; FAILURE; incorrect user id; userId = "
                    + userId + "; hash = " + hash);
            return ResponseEntity.badRequest().header(HEADER_KEY, ENTITIES_NOT_FOUND).build();
        }

        User user = tempUser.get();

        if (hash.equals(user.getName()) && user.getRole() == User.Role.GUEST) {
            user.setRole(User.Role.USER);
            User savedUser = userRepository.save(user);
            logger.info("confirmEmail() call; SUCCESS; confirmedUser = " + user);
            return ResponseEntity.ok(savedUser);
        }

        logger.info("confirmEmail() call; FAILURE; incorrect hash or already confirmed; userId = "
                + userId + "; hash = " + hash + "; user = " + user);
        return ResponseEntity.badRequest().header(HEADER_KEY, EMAIL_CONFIRMATION_ERROR).build();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateUser(User user, Long userId) {
        if (!userRepository.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(userId);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
