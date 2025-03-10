package com.owasp.owaspbe.cryptographicfailures.controller;

import com.owasp.owaspbe.cryptographicfailures.model.CFUser;
import com.owasp.owaspbe.cryptographicfailures.service.CFUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") // ❌ Oricine poate accesa API-ul din orice origine
@RequestMapping("/api/cf")
public class CFUserController {
    private final CFUserService userService;

    public CFUserController(CFUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<CFUser> registerUser(@RequestBody CFUser user) {
        CFUser newUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok(newUser);
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<CFUser>> getUsers() {
//        return ResponseEntity.ok(userService.getAllUsers()); // ❌ Returnează parolele hashuite cu MD5 (vulnerabil)
//    }
}
