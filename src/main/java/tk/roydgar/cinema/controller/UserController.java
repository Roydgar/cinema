package tk.roydgar.cinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.cinema.model.entity.User;
import tk.roydgar.cinema.model.entity.temporary.LoginData;
import tk.roydgar.cinema.model.service.UserService;
import tk.roydgar.cinema.util.constants.Mappings;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Mappings.USER)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    UserService userService;

    @PostMapping(Mappings.USER_REGISTER)
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping(Mappings.USER_LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginData loginData) {
        return userService.login(loginData);
    }

    @PutMapping(Mappings.USER_ID)
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long userId) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping(Mappings.USER_ID)
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }

    @GetMapping("confirmation/{userId}/{hash}")
    public ResponseEntity<?> confirmEmail(@PathVariable Long userId, @PathVariable String hash) {
        return userService.confirmEmail(userId, hash);
    }

    @GetMapping(Mappings.USER_ID)
    public ResponseEntity<?> findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }


}
