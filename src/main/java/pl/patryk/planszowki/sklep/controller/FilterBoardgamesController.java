package pl.patryk.planszowki.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.planszowki.sklep.model.Criteria;
import pl.patryk.planszowki.sklep.service.BoardGameService;

@Controller
public class FilterBoardgamesController {

    @Autowired
    private BoardGameService boardGameService;


}
