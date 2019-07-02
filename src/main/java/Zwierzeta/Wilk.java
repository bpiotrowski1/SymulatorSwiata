package Zwierzeta;

import java.awt.Color;

import Swiat.Swiat;
import Swiat.Zwierze;

public class Wilk extends Zwierze {

    public Wilk(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 9;
        this.inicjatywa = 5;
        this.kolizjaSpec = false;
        this.znak = 'W';
        this.color = Color.GRAY;
    }

}
