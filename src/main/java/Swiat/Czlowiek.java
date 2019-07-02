package Swiat;

import java.awt.Color;
import java.util.Random;

public class Czlowiek extends Zwierze {

    private boolean niesmiertelnosc;
    private boolean blokada;
    private int czasNiesmiertelnosci;
    private int czasBlokady;
    private static final int GORA = 6;
    private static final int DOL = 2;
    private static final int LEWO = 4;
    private static final int PRAWO = 0;

    public Czlowiek(Swiat swiatRef, int x, int y) {
        super(swiatRef, x, y);
        this.sila = 5;
        this.inicjatywa = 4;
        this.kolizjaSpec = true;
        this.znak = 'C';
        this.wiek = 0;
        this.niesmiertelnosc = false;
        this.blokada = false;
        this.color = Color.BLACK;
    }

    @Override
    protected void setNiesmiertelnosc(boolean niesmiertelnosc) {
        this.niesmiertelnosc = niesmiertelnosc;
    }

    @Override
    protected void setCzasNiesmiertelnosci(int czasNiesmiertelnosci) {
        this.czasNiesmiertelnosci = czasNiesmiertelnosci;
    }

    @Override
    protected int getCzasNiesmiertelnosci() {
        return this.czasNiesmiertelnosci;
    }

    protected boolean getBlokada() {
        return this.blokada;
    }

    protected int getCzasBlokady() {
        return this.czasBlokady;
    }

    @Override
    public boolean getNiesmiertelnosc() {
        return this.niesmiertelnosc;
    }

    protected void setBlokada(boolean blokada) {
        this.blokada = blokada;
    }

    protected void setCzasBlokady(int czas) {
        this.czasBlokady = czas;
    }

    private void moveRight() {
        Organizm przeciwnik = null;
        if (this.polozenieX + 1 < swiat.getRozmiarX()) {
            przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, PRAWO);
            if (przeciwnik != null) {
                if (swiat.konfrontuj(this, przeciwnik) == true) {
                    this.polozenieX++;
                }
            } else {
                this.polozenieX++;
            }
        }
        sprawdzNiesmiertelnosc();
    }

    private void moveLeft() {
        Organizm przeciwnik = null;
        if (this.polozenieX - 1 >= 0) {
            przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, LEWO);
            if (przeciwnik != null) {
                if (swiat.konfrontuj(this, przeciwnik) == true) {
                    this.polozenieX--;
                }
            } else {
                this.polozenieX--;
            }
        }
        sprawdzNiesmiertelnosc();
    }

    private void moveUp() {
        Organizm przeciwnik = null;
        if (this.polozenieY - 1 >= 0) {
            przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, GORA);
            if (przeciwnik != null) {
                if (swiat.konfrontuj(this, przeciwnik) == true) {
                    this.polozenieY--;
                }
            } else {
                this.polozenieY--;
            }
        }
        sprawdzNiesmiertelnosc();
    }

    private void moveDown() {
        Organizm przeciwnik = null;
        if (this.polozenieY + 1 < swiat.getRozmiarY()) {
            przeciwnik = swiat.sprawdzPole(this.polozenieX, this.polozenieY, DOL);
            if (przeciwnik != null) {
                if (swiat.konfrontuj(this, przeciwnik) == true) {
                    this.polozenieY++;
                }
            } else {
                this.polozenieY++;
            }
        }
        sprawdzNiesmiertelnosc();
    }

    protected void sprawdzNiesmiertelnosc() {
        if (niesmiertelnosc == true) {
            if (czasNiesmiertelnosci > 0) {
                czasNiesmiertelnosci--;
            } else {
                this.niesmiertelnosc = false;
            }
        }
        if (blokada == true) {
            if (czasBlokady > 0) {
                czasBlokady--;
            } else {
                this.blokada = false;
            }
        }
    }

    @Override
    public void niesmiertelnosc() {
        if (blokada == false) {
            this.czasNiesmiertelnosci = 6;
            this.niesmiertelnosc = true;
            this.blokada = true;
            this.czasBlokady = 11;
        }
    }

    @Override
    public void akcja() {
        switch (swiat.getCzlowiekRuch()) {
            case 0:
                moveRight();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveUp();
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean kolizja(Organizm atakujacy) {
        if (niesmiertelnosc == true) {
            if (atakujacy.getSila() > sila) {
                Random rand = new Random();
                int kierunek = rand.nextInt(KIERUNKI);
                if (swiat.sprawdzPole(this.polozenieX, this.polozenieY, kierunek) == null) {
                    super.zmianaPolozenia(kierunek);
                }
                return false;
            } else {
                //swiat.ustawKomunikat("1", String.valueOf(znak), String.valueOf(atakujacy.getZnak()));
                //swiat.usunOrganizm(atakujacy);
                return true;
            }
        } else {
            if (atakujacy.getSila() > sila) {
                swiat.ustawKomunikat("1", String.valueOf(atakujacy.getZnak()), String.valueOf(znak));
                swiat.usunOrganizm(this);
                return false;
            } else {
                swiat.ustawKomunikat("1", String.valueOf(znak), String.valueOf(atakujacy.getZnak()));
                swiat.usunOrganizm(atakujacy);
                return true;
            }
        }
    }

}
