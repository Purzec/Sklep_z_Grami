package pl.patryk.planszowki.sklep.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.patryk.planszowki.sklep.model.Account;
import pl.patryk.planszowki.sklep.model.Token;
import pl.patryk.planszowki.sklep.repository.AccountRepository;
import pl.patryk.planszowki.sklep.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class AccountService {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;
    private MailService mailService;
    private TokenRepository tokenRepository;

    public AccountService(PasswordEncoder passwordEncoder, AccountRepository accountRepository, MailService mailService, TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.mailService = mailService;
        this.tokenRepository = tokenRepository;
    }

    public void addAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole("ROLE_USER");
        accountRepository.save(account);
        sendToken(account);

    }


    public void sendToken(Account account) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAccount(account);
        tokenRepository.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(account.getEmail(), "Potwierdz założenie konta w naszym serwisie", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }



}
