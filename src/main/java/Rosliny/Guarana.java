package Rosliny;

import java.awt.Color;

import Swiat.Organizm;
import Swiat.Roslina;
import Swiat.Swiat;

public class Guarana extends Roslina {

    public Guarana(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 0;
        this.inicjatywa = 0;
        this.kolizjaSpec = true;
        this.znak = 'g';
        this.color = Color.RED;
    }

    @Override
    protected boolean kolizja(Organizm atakujacy) {
        //zwieksza sile o 3 ktore je zje
        atakujacy.setSila(atakujacy.getSila() + 3);
        swiat.ustawKomunikat("1", String.valueOf(atakujacy.getZnak()), String.valueOf(znak));
        swiat.usunOrganizm(this);
        return false;
    }

}
