package pl.patryk.planszowki.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.patryk.planszowki.sklep.model.BoardGame;
import pl.patryk.planszowki.sklep.repository.BoardGameRepository;

@Controller
public class AdminPanel {

    @Autowired
    private BoardGameRepository boardGameRepository;

    @GetMapping("/AdminPanel")
    public String showAllOption(Model model) {
        model.addAttribute("planszowki", boardGameRepository.findAll());
        return "AdminPanel";//
    }

    @GetMapping("/boardGameEdit/{id}")
    public String boardGamesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("planszowka", boardGameRepository.findById(id).get());
        return "EditBoardGame";
    }


    @GetMapping("/delete/{id}")
    public String deleteBoardGames(@PathVariable Long id) {
        boardGameRepository.deleteById(id);
        return "redirect:/AdminPanel";
    }

    @PostMapping
    public String commentaryEdit() {
        return "AdminPanel";
    }

    @PostMapping("/boardgameEdit/{id}/change/")
    public String saveEdit(@PathVariable Long id, @ModelAttribute("planszowka") BoardGame boardGame) {
        BoardGame gameToUpdate = boardGameRepository.getById(id);
        gameToUpdate.setPrice(boardGame.getPrice());
        gameToUpdate.setGraZostalaZaatakowana(boardGame.isGraZostalaZaatakowana());
        boardGameRepository.save(gameToUpdate);
        return "redirect:/AdminPanel";
    }

    @GetMapping("/addBoardGame")
    private String addBoardGamesPanel(Model model) {
        model.addAttribute("planszowka", new BoardGame());
        return "AddBoardgames";
    }

    @PostMapping("/addBoardGames")
    private String saveNewBoardgames(@ModelAttribute("planszowka") BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return "redirect:/AdminPanel";
    }

}
