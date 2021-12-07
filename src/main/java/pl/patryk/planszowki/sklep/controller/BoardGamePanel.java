package pl.patryk.planszowki.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.planszowki.sklep.model.Commentary;
import pl.patryk.planszowki.sklep.model.Criteria;
import pl.patryk.planszowki.sklep.repository.BoardGameRepository;
import pl.patryk.planszowki.sklep.service.BoardGameService;

@Controller
public class BoardGamePanel {

    @Autowired
    private BoardGameService boardGameService;

    @Autowired
    private BoardGameRepository boardGameRepository;

    public BoardGamePanel(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping("/search")
    public String searchPanel(Model model,@ModelAttribute("criteria") Criteria criteria) {
        model.addAttribute("planszowki", boardGameService.getBoardGamesFromDatabase(criteria));
        return "SearchPanel";
    }
    //zrobic mechanizm sortowania
    @PostMapping("/search/filter")
    public String loginAccount(Model model,@ModelAttribute("criteria") Criteria criteria) {
        model.addAttribute("planszowki", boardGameService.getBoardGamesFromDatabase(criteria));
        return "SearchPanel";
    }



    @GetMapping("/boardgame/{id}")
    public String getDetails(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("boardgame",boardGameRepository.findById(id).get());
        model.addAttribute("playerScore" , boardGameService.getScore(id));
        return "BoardGameDetails";
    }

    @PostMapping("/boardgame/{id}/addCom")
    public String addCommentary (@PathVariable("id") Long id,Commentary commentary){
       boardGameService.fillComment(id,commentary);
return "redirect:/boardgame/{id}";
    }


}
