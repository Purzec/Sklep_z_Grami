package pl.patryk.planszowki.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryk.planszowki.sklep.model.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame,Long> {



}
