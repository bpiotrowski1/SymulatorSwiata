package Zwierzeta;

import java.awt.Color;
import java.util.Random;

import Swiat.Organizm;
import Swiat.Swiat;
import Swiat.Zwierze;

public class Lis extends Zwierze {

    public Lis(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 3;
        this.inicjatywa = 7;
        this.kolizjaSpec = false;
        this.znak = 'L';
        this.color = Color.ORANGE;
    }

    @Override
    protected void akcja() {
        //dobry wech, nie ruszy na pole - silniejszy organizm
        Random rand = new Random();
        int i = 0, kierunek = rand.nextInt(KIERUNKI);
        Organizm org = null;

        while (i < KIERUNKI) {
            org = swiat.sprawdzPole(this.polozenieX, this.polozenieY, kierunek);
            if (org != null) {
                if (this.sila > org.getSila()) {
                    if (swiat.konfrontuj(this, org) == true) {
                        zmianaPolozenia(kierunek);
                    }
                    break;
                } else {
                    kierunek = rand.nextInt(KIERUNKI);
                }
            } else {
                zmianaPolozenia(kierunek);
                break;
            }
            i++;
        }
    }
}
