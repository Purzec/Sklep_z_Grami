package pl.patryk.planszowki.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.patryk.planszowki.sklep.enums.CommentaryType;
import pl.patryk.planszowki.sklep.model.Account;
import pl.patryk.planszowki.sklep.model.BoardGame;
import pl.patryk.planszowki.sklep.model.Commentary;
import pl.patryk.planszowki.sklep.model.Criteria;
import pl.patryk.planszowki.sklep.repository.BoardGameRepository;
import pl.patryk.planszowki.sklep.repository.CommentaryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardGameService {

    @Autowired
    private BoardGameRepository boardGameRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    /**
     * Filtrowanie gier planszowych
     *
     * @param criteria kryteria filtrowania
     * @return gry planszowe po przefiltrowaniu
     */
    public List<BoardGame> getBoardGamesFromDatabase(Criteria criteria) {
        List<BoardGame> boardGames = boardGameRepository.findAll();
        if (criteria.getMinPlayerToPlay() > 0) {
            boardGames = boardGames.stream()
                    .filter(boardGame -> boardGame.getMinPlayerToPlay() >= criteria.getMinPlayerToPlay())
                    .collect(Collectors.toList());
        }
        if (criteria.getMaxPlayerToPlay() > 0) {
            boardGames = boardGames.stream()
                    .filter(boardGame -> boardGame.getMaxPlayerToPlay() <= criteria.getMaxPlayerToPlay())
                    .collect(Collectors.toList());
        }
        if (criteria.getMinPrice() > 0) {
            boardGames = boardGames.stream()
                    .filter(boardGame -> boardGame.getPrice() >= criteria.getMinPrice())
                    .collect(Collectors.toList());
        }
        if (criteria.getMaxPrice() > 0) {
            boardGames = boardGames.stream()
                    .filter(boardGame -> boardGame.getPrice() <= criteria.getMaxPrice())
                    .collect(Collectors.toList());
        }
        if (criteria.getMinAverageGrade() > 0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getAverageGrade() >= criteria.getMinAverageGrade())
                    .collect(Collectors.toList());
        }
        if (criteria.getMaxAverageGrade() > 0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getAverageGrade() <= criteria.getMaxAverageGrade())
                    .collect(Collectors.toList());
        }
        if (criteria.getMinTime() > 0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getMinTime() >= criteria.getMinTime())
                    .collect(Collectors.toList());
        }
        if (criteria.getMaxTime() > 0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getMaxTime() <= criteria.getMaxTime()).collect(Collectors.toList());
        }
        if (criteria.getMinWeight() > 0.0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getWeight() >= criteria.getMinWeight())
                    .collect(Collectors.toList());
        }
        if (criteria.getMaxWeight() > 0.0) {
            boardGames = boardGames.stream().filter(boardGame -> boardGame.getWeight() <= criteria.getMaxWeight())
                    .collect(Collectors.toList());
        }
        return boardGames;
    }

    public BoardGame getBoardGame(Long id) {
        Optional<BoardGame> optionalBoardGame = boardGameRepository.findById(id);
        if (optionalBoardGame.isPresent()) {
            BoardGame boardGame = optionalBoardGame.get();
            return boardGame;
        }
        throw new IllegalArgumentException("Nieprawidłowa gra planszowa");
    }

    public double getScore(Long id) {
        BoardGame boardGame = boardGameRepository.findById(id).get();
        if (boardGame.isGraZostalaZaatakowana())
        {
            List<Commentary> commentaries = boardGame.getCommentaryList();
            commentaries = commentaries.stream().filter(commentary -> !commentary.getCommentCreator().equals("Anonimowy")).collect(Collectors.toList());
            int i = 0;
            double averscore = 0;
            for (Commentary commentary : commentaries) {
                i++;
                if (commentary.getScore() != 0) {
                    averscore = commentary.score + averscore;
                }
            }
            averscore = averscore / i;
            return averscore;
        }else
        {
            int i = 0;
            double averscore = 0;
            for (Commentary commentary : boardGame.getCommentaryList()) {
                i++;
                if (commentary.getScore() != 0) {
                    averscore = commentary.score + averscore;
                }
            }
            averscore = averscore / i;
            return averscore;
        }
    }

    public void fillComment(Long id, Commentary commentary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser")) {
            commentary.setCommentCreator("Anonimowy");
            commentary.setCommentaryType(CommentaryType.KOMENTARZ_ANONIMOWY);
            commentary.setId(null);
            commentary.setSentDate(new Date());
            commentary.setBoardGame(boardGameRepository.getById(id));
            commentaryRepository.save(commentary);
        } else {
            Account account = (Account) auth.getPrincipal();
            commentary.setCommentCreator(account.getUsername());
            commentary.setCommentaryType(CommentaryType.KOMENTARZ_ZALOGOWANEGO_UZYTKOWNIKA);
            commentary.setId(null);
            commentary.setSentDate(new Date());
            commentary.setBoardGame(boardGameRepository.getById(id));
        }
    }
}
