package Zwierzeta;

import java.awt.Color;

import Swiat.Swiat;
import Swiat.Zwierze;

public class Owca extends Zwierze {

    public Owca(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 4;
        this.inicjatywa = 4;
        kolizjaSpec = false;
        this.znak = 'O';
        this.color = Color.WHITE;
    }

}
