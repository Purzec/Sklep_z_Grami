package pl.patryk.planszowki.sklep.model;

import lombok.Getter;
import lombok.Setter;
import pl.patryk.planszowki.sklep.enums.CommentaryType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTARY")
@Getter
@Setter
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String content;

    public String commentCreator;

    @Enumerated(EnumType.STRING)
    public CommentaryType commentaryType;

    public Date sentDate;

    public double score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARDGAME_ID")
    public BoardGame boardGame;
}
