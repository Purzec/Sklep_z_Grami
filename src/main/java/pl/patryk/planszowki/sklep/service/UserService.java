package pl.patryk.planszowki.sklep.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patryk.planszowki.sklep.repository.AccountRepository;


@Service
public class UserService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
