package Zwierzeta;

import java.awt.Color;
import java.util.Random;

import Swiat.Organizm;
import Swiat.Swiat;
import Swiat.Zwierze;

public class Zolw extends Zwierze{

    public Zolw(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 2;
        this.inicjatywa = 1;
        this.kolizjaSpec = true;
        this.znak = 'Z';
        this.color = Color.GREEN;
    }

    @Override
    protected void akcja() {
        Random rand = new Random();
        int los = rand.nextInt(100) + 1, kierunek = rand.nextInt(KIERUNKI);
        if (los <= 75) {
            Organizm przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, kierunek);
            if (przeciwnik != null) {
                if (swiat.konfrontuj(this, przeciwnik) == true) {
                    this.zmianaPolozenia(kierunek);
                }
            } else {
                this.zmianaPolozenia(kierunek);
            }
        }
    }

    @Override
    protected boolean kolizja(Organizm atakujacy) {
        if (atakujacy.getSila() < 5) {
            return true;
        } else {
            return false;
        }
    }
}
