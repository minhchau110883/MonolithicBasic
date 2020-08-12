package com.chaung.web.rest;

import com.chaung.domain.User;
import com.chaung.repository.UserRepository;
import com.chaung.security.SecurityUtils;
import com.chaung.service.MailService;
import com.chaung.service.UserService;
import com.chaung.service.dto.PasswordChangeDTO;
import com.chaung.service.dto.UserDTO;
import com.chaung.web.rest.errors.EmailAlreadyUsedException;
import com.chaung.web.rest.errors.InvalidPasswordException;
import com.chaung.web.rest.errors.LoginAlreadyUsedException;
import com.chaung.web.rest.vm.KeyAndPasswordVM;
import com.chaung.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class TimeoutResource {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(TimeoutResource.class);

    public TimeoutResource() {
    }

    @GetMapping("/check-timeout")
    public ResponseEntity<Void> activateAccount(@RequestParam(value = "timeoutIn") Integer timeoutIn,
                                                @RequestParam(value = "timeout") Boolean timeout) throws Exception {
        log.info("START API will timeout in {} seconds", timeoutIn);
//        if (timeout != null && timeout) {
//            log.info("WAITING API will timeout in {} seconds", timeoutIn);
//            Thread.sleep(timeoutIn * 1000L);
//        }
        long start = System.currentTimeMillis();
        while (true) {
            long running = System.currentTimeMillis();
            log.info("running {}, run {}", running, (running-start)/1000);
            Thread.sleep( 1000L);
            if ((running-start)/1000/60 > timeoutIn) {
                break;
            }

        }
        log.info("END API will timeout in {} seconds", timeoutIn);
        return ResponseEntity.noContent().build();
    }

}
