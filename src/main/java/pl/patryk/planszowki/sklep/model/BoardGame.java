package pl.patryk.planszowki.sklep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BOARDGAME")
@Getter
@Setter
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private int minPlayerToPlay;

    private int maxPlayerToPlay;

    private double averageGrade;

    private int minTime;

    private int maxTime;

    private double weight;

    private boolean graZostalaZaatakowana = false;

    @OneToMany(mappedBy = "boardGame")
    private List<Commentary> commentaryList;

    @OneToMany(mappedBy = "boardGame")
    Set<OrderDetails> orderDetails;

}
