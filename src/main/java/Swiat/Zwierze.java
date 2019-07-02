package Swiat;

import java.util.Random;

public class Zwierze extends Organizm {
    public static final int KIERUNKI = 8;

    public Zwierze(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
    }

    protected void zmianaPolozenia(int kierunek) {
        switch (kierunek) {
            case 0:
                if (this.polozenieX + 1 < swiat.getRozmiarX()) {
                    this.polozenieX++;
                }
                break;
            case 1:
                if (this.polozenieX + 1 < swiat.getRozmiarX() && this.polozenieY + 1 < swiat.getRozmiarY()) {
                    this.polozenieX++;
                    this.polozenieY++;
                }
                break;
            case 2:
                if (this.polozenieY + 1 < swiat.getRozmiarY()) {
                    this.polozenieY++;
                }
                break;
            case 3:
                if (this.polozenieX - 1 >= 0 && this.polozenieY + 1 < swiat.getRozmiarY()) {
                    this.polozenieX--;
                    this.polozenieY++;
                }
                break;
            case 4:
                if (this.polozenieX - 1 >= 0) {
                    this.polozenieX--;
                }
                break;
            case 5:
                if (this.polozenieX - 1 >= 0 && this.polozenieY - 1 >= 0) {
                    this.polozenieX--;
                    this.polozenieY--;
                }
                break;
            case 6:
                if (this.polozenieY - 1 >= 0) {
                    this.polozenieY--;
                }
                break;
            case 7:
                if (this.polozenieX + 1 < swiat.getRozmiarX() && this.polozenieY - 1 >= 0) {
                    this.polozenieX++;
                    this.polozenieY--;
                }
                break;
        }
    }

    @Override
    protected void akcja() {
        Random rand = new Random();
        int kierunek = rand.nextInt(KIERUNKI);

        Organizm przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, kierunek);
        if (przeciwnik != null) {
            if (swiat.konfrontuj(this, przeciwnik) == true) {
                this.zmianaPolozenia(kierunek);
            }
        } else {
            this.zmianaPolozenia(kierunek);
        }
    }

    @Override
    protected boolean kolizja(Organizm przeciwnik) {
        return false;
    }

}
