package pl.patryk.planszowki.sklep.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.patryk.planszowki.sklep.model.Account;
import pl.patryk.planszowki.sklep.model.Token;
import pl.patryk.planszowki.sklep.repository.AccountRepository;
import pl.patryk.planszowki.sklep.repository.TokenRepository;
import pl.patryk.planszowki.sklep.service.AccountService;

@Controller
public class MainPage {

    @Autowired
    private AccountService accountService;

    TokenRepository tokenRepository;

    AccountRepository accountRepository;

    public MainPage(TokenRepository tokenRepository, AccountRepository accountRepository) {
        this.tokenRepository = tokenRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/")
    public String home(){
        //TODO ZROBIĆ PANEL GŁÓWNY

        return "MainPage";
    }


    @PostMapping("/register")
    public String register(Account account) {
        accountService.addAccount(account);
        return "MainPage";
    }

    //strona rejestracji
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "Register";
    }


    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value);
        Account appUser = byValue.getAccount();
        appUser.setEnabled(true);
        accountRepository.save(appUser);
        return "MainPage";
    }

}
