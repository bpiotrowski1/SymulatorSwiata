package Rosliny;

import java.awt.Color;

import Swiat.Organizm;
import Swiat.Roslina;
import Swiat.Swiat;

public class Wjagody extends Roslina {

    public Wjagody(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 99;
        this.inicjatywa = 0;
        this.kolizjaSpec = true;
        this.znak = 'j';
        this.color = Color.pink;
    }

    @Override
    protected boolean kolizja(Organizm atakujacy) {
        return zjeGinie(atakujacy);
    }

}
