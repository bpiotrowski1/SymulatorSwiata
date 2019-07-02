package Rosliny;

import java.awt.Color;
import java.util.Random;

import Swiat.Roslina;
import Swiat.Swiat;

public class Mlecz extends Roslina {

    public Mlecz(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 0;
        this.inicjatywa = 0;
        this.kolizjaSpec = false;
        this.znak = 'm';
        this.color = Color.yellow;
    }

    @Override
    protected void akcja() {
        //3 proby rozpszestrzeniania sie
        Random rand = new Random();
        int proby = 0, los;
        while (proby != 3) {
            los = rand.nextInt(100) + 1;
            if (los <= 3) {
                super.akcja();
            }
            proby++;
        }
    }

}
