package pl.patryk.planszowki.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryk.planszowki.sklep.model.Token;

@Repository
public interface TokenRepository extends JpaRepository <Token,Long> {


    Token findByValue(String value);

}
