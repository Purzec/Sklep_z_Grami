package pl.patryk.planszowki.sklep.model;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@ManyToOne
@JoinColumn(name = "ACCOUNT_ID")
    private Account account;

@ManyToOne
@JoinColumn(name = "BOARDGAME_ID")
private BoardGame boardGame;
    //ilosc
private int quantity;
    //cena
private double price;


}
