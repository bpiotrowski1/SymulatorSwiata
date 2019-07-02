package Swiat;

import java.awt.Color;

public abstract class Organizm {
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected char znak;
    protected int polozenieX;
    protected int polozenieY;
    protected boolean kolizjaSpec;
    protected boolean ruch;
    protected Swiat swiat;
    protected Color color;

    abstract protected void akcja();

    abstract boolean kolizja(Organizm atakujacy);

    public Organizm(Swiat swiatRef, int x, int y) {
        this.swiat = swiatRef;
        this.polozenieX = x;
        this.polozenieY = y;
        this.wiek = 0;
        this.ruch = false;
    }

    protected int getCzasNiesmiertelnosci() {
        return -1;
    }

    protected void setNiesmiertelnosc(boolean niesmiertelnosc) {
        niesmiertelnosc = false;
    }

    protected void setCzasNiesmiertelnosci(int czasNiesmiertelnosci) {
        czasNiesmiertelnosci = 0;
    }

    public int getPolozenieX() {
        return polozenieX;
    }

    public int getPolozenieY() {
        return polozenieY;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getInic() {
        return this.inicjatywa;
    }

    public int getWiek() {
        return this.wiek;
    }

    public char getZnak() {
        return this.znak;
    }

    public boolean getKolizjaSpec() {
        return this.kolizjaSpec;
    }

    public boolean getNiesmiertelnosc() {
        return false;
    }

    public boolean getRuch() {
        return this.ruch;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setPolozenieX(int x) {
        this.polozenieX = x;
    }

    public void setPolozenieY(int y) {
        this.polozenieY = y;
    }

    public void setRuch(boolean ruch) {
        this.ruch = ruch;
    }

    public Color getColor() {
        return color;
    }

    public void niesmiertelnosc() {

    }
}
