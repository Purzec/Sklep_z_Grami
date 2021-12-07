package pl.patryk.planszowki.sklep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

    private double maxPrice;
    private double minPrice;

    private int minPlayerToPlay;

    private int maxPlayerToPlay;

    private double maxAverageGrade;
    private double minAverageGrade;

    private int minTime;

    private int maxTime;


    private double maxWeight;
    private double minWeight;

}
