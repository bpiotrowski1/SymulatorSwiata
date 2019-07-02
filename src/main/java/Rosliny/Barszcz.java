package Rosliny;

import java.awt.Color;

import Swiat.Organizm;
import Swiat.Roslina;
import Swiat.Swiat;
import Swiat.Zwierze;

import static Swiat.Zwierze.KIERUNKI;

public class Barszcz extends Roslina {

    public Barszcz(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 10;
        this.inicjatywa = 0;
        this.kolizjaSpec = true;
        this.znak = 'b';
        this.color = Color.CYAN;
    }

    @Override
    protected void akcja() {
        Organizm organizm;
        for (int i = 0; i < KIERUNKI; i++) {
            organizm = swiat.sprawdzPole(this.polozenieX, this.polozenieY, i);
            if (organizm != null) {
                if (organizm.getZnak() == 'C') {
                    if (organizm.getNiesmiertelnosc() == false) {
                        swiat.ustawKomunikat("1", String.valueOf(znak), String.valueOf(organizm.getZnak()));
                        swiat.usunOrganizm(organizm);
                    }
                } else {
                    if (organizm instanceof Zwierze) {
                        swiat.ustawKomunikat("1", String.valueOf(znak), String.valueOf(organizm.getZnak()));
                        swiat.usunOrganizm(organizm);
                    }
                }
            }
        }
        super.akcja();
    }

    @Override
    protected boolean kolizja(Organizm atakujacy) {
        return zjeGinie(atakujacy);
    }

}
