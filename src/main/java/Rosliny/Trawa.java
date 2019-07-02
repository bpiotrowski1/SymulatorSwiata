package Rosliny;

import java.awt.Color;

import Swiat.Roslina;
import Swiat.Swiat;

public class Trawa extends Roslina {

    public Trawa(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 0;
        this.inicjatywa = 0;
        this.kolizjaSpec = false;
        this.znak = 't';
        this.color = Color.magenta;
    }

}
