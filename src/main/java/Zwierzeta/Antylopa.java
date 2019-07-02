package Zwierzeta;

import java.awt.Color;

import Swiat.Swiat;
import Swiat.Zwierze;

public class Antylopa extends Zwierze{
    public Antylopa(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.kolizjaSpec = true;
        this.znak = 'A';
        this.color = Color.BLUE;
    }
}
