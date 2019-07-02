package Swiat;

import java.util.Random;

import static Swiat.Zwierze.KIERUNKI;

public class Roslina extends Organizm {
    private static final int PROCENT_ROZSIANIA = 5;

    public Roslina(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
    }

    @Override
    protected void akcja() {
        Random rand = new Random();
        int los = rand.nextInt(100) + 1;
        if (los <= PROCENT_ROZSIANIA) {
            int kierunek = rand.nextInt(KIERUNKI);
            Organizm przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, kierunek);
            if (przeciwnik == null) {
                zasiej(this.polozenieX, this.polozenieY, kierunek);
            }
        }
    }

    @Override
    protected boolean kolizja(Organizm org) {
        return false;
    }

    protected boolean zjeGinie(Organizm atakujacy) {
        if (atakujacy.getZnak() == 'C') {
            if (atakujacy.getNiesmiertelnosc() == true) {
                if (atakujacy.kolizja(this) == true) {
                    if (atakujacy.getSila() > sila) {
                        swiat.ustawKomunikat("1", "C", String.valueOf(znak));
                        swiat.usunOrganizm(this);
                        return false;
                    } else {
                        swiat.ustawKomunikat("1", String.valueOf(znak), "C");
                        swiat.usunOrganizm(atakujacy);
                    }
                }
            } else {
                swiat.ustawKomunikat("1", String.valueOf(znak), "C");
                swiat.usunOrganizm(atakujacy);
                swiat.usunOrganizm(this);
            }
        } else {
            swiat.ustawKomunikat("1", String.valueOf(znak), String.valueOf(atakujacy.getZnak()));
            swiat.usunOrganizm(atakujacy);
            swiat.usunOrganizm(this);
        }
        return true;
    }

    protected void zasiej(int x, int y, int kierunek) {
        int newX = 0, newY = 0;
        boolean zasiej = false;
        switch (kierunek) {
            case 0:
                if (x + 1 < swiat.getRozmiarX()) {
                    newX = x + 1;
                    newY = y;
                    zasiej = true;
                }
                break;
            case 1:
                if (x + 1 < swiat.getRozmiarX() && y + 1 < swiat.getRozmiarY()) {
                    newX = x + 1;
                    newY = y + 1;
                    zasiej = true;
                }
                break;
            case 2:
                if (y + 1 < swiat.getRozmiarY()) {
                    newX = x;
                    newY = y + 1;
                    zasiej = true;
                }
                break;
            case 3:
                if (x - 1 >= 0 && y + 1 < swiat.getRozmiarY()) {
                    newX = x - 1;
                    newY = y + 1;
                    zasiej = true;
                }
                break;
            case 4:
                if (x - 1 >= 0) {
                    newX = x - 1;
                    newY = y;
                    zasiej = true;
                }
                break;
            case 5:
                if (x - 1 >= 0 && y - 1 >= 0) {
                    newX = x - 1;
                    newY = y - 1;
                    zasiej = true;
                }
                break;
            case 6:
                if (y - 1 >= 0) {
                    newX = x;
                    newY = y - 1;
                    zasiej = true;
                }
                break;
            case 7:
                if (x + 1 < swiat.getRozmiarX() && y - 1 >= 0) {
                    newX = x + 1;
                    newY = y - 1;
                    zasiej = true;
                }
                break;
        }
        if (zasiej == true) {
            swiat.dodajOrganizm(newX, newY, znak);
            swiat.ustawKomunikat("2", String.valueOf(znak), "0");
        }
    }

}
