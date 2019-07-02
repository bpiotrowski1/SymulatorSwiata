package Swiat;

import Rosliny.*;
import Zwierzeta.*;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Swiat {

    private int tura;
    private int klawisz;
    @Getter private int rozmiarX;
    @Getter private int rozmiarY;
    private int iloscOrganizmowPocz;
    @Getter private int iloscOrganizmow;
    private int maxOrganizmow;
    private boolean czyCzlowiekZyje;
    private boolean wczytuje;
    private String[][] komunikaty;
    private Organizm[] organizmy;
    @Getter private Organizm wskCzlowiek;
    @Getter @Setter private int czlowiekRuch;
    private final static int RODZAJOW_ORGANIZMOW = 10;
    private final static int PROCENT_ORGANIZMOW = 20;
    private final static int CZLOWIEK_X = 0;
    private final static int CZLOWIEK_Y = 0;
    private final static int PRAWO = 0;
    private final static int DOL = 2;
    private final static int LEWO = 4;
    private final static int GORA = 6;
    private final static int KOMUNIKATY_X = 5;
    private final static int KOMUNIKATY_Y = 3;

    public String getKomunikat(int x, int y) {
        return komunikaty[x][y];
    }

    public void setKomunikat(String komunikat, int x, int y) {
        komunikaty[x][y] = komunikat;
    }

    public Organizm getOrganizm(int i) {
        return organizmy[i];
    }

    public Swiat(int x, int y) {
        this.rozmiarX = x;
        this.rozmiarY = y;
        czlowiekRuch = -1;
        tura = 0;
        iloscOrganizmow = 0;
        iloscOrganizmowPocz = (rozmiarX * rozmiarY * PROCENT_ORGANIZMOW) / 100;
        maxOrganizmow = x * y;
        wczytuje = false;
        organizmy = new Organizm[rozmiarX * rozmiarX];
        komunikaty = new String[KOMUNIKATY_Y][KOMUNIKATY_X];
        for (int i = 0; i < KOMUNIKATY_Y; i++) {
            for (int j = 0; j < KOMUNIKATY_X; j++) {
                komunikaty[i][j] = "0";
            }
        }
    }

    public void rozpocznij() {
        wskCzlowiek = new Czlowiek(this, CZLOWIEK_X, CZLOWIEK_Y);
        organizmy[iloscOrganizmow++] = wskCzlowiek;
        czyCzlowiekZyje = true;

        for (int i = 3; i < iloscOrganizmowPocz; i++) {
            Random rand = new Random();
            int x = rand.nextInt(this.rozmiarX), y = rand.nextInt(this.rozmiarY), r = rand.nextInt(RODZAJOW_ORGANIZMOW);
            while (znajdzPozycje(x, y) != -1) {
                x = rand.nextInt(this.rozmiarX);
                y = rand.nextInt(this.rozmiarY);
            }
            this.dodajOrganizm(x, y, r);
        }
        this.wykonajTure();
    }

    public void odNowa(int x, int y) {
        rozmiarX = x;
        rozmiarY = y;
        tura = 0;
        iloscOrganizmow = 0;
        iloscOrganizmowPocz = (rozmiarX * rozmiarY * PROCENT_ORGANIZMOW) / 100;
        maxOrganizmow = x * y;
        wczytuje = false;
        for (int i = 0; i < rozmiarX * rozmiarX; i++) {
            organizmy[i] = null;
        }
        wskCzlowiek = null;
    }

    private int organizmNaLiczbe(char znak) {
        switch (znak) {
            case 'A':
                return 0;
            case 'L':
                return 1;
            case 'O':
                return 2;
            case 'W':
                return 3;
            case 'Z':
                return 4;
            case 'b':
                return 5;
            case 'g':
                return 6;
            case 'm':
                return 7;
            case 't':
                return 8;
            case 'j':
                return 9;
            default:
                return -1;
        }
    }

    private void dodajOrganizm(int x, int y, int r) {
        if (iloscOrganizmow < maxOrganizmow) {
            if (x >= 0 && x < rozmiarX && y >= 0 && y < rozmiarY) {
                switch (r) {
                    case 0:
                        organizmy[this.iloscOrganizmow++] = new Antylopa(this, x, y);
                        break;
                    case 1:
                        organizmy[this.iloscOrganizmow++] = new Lis(this, x, y);
                        break;
                    case 2:
                        organizmy[this.iloscOrganizmow++] = new Owca(this, x, y);
                        break;
                    case 3:
                        organizmy[this.iloscOrganizmow++] = new Wilk(this, x, y);
                        break;
                    case 4:
                        organizmy[this.iloscOrganizmow++] = new Zolw(this, x, y);
                        break;
                    case 5:
                        organizmy[this.iloscOrganizmow++] = new Barszcz(this, x, y);
                        break;
                    case 6:
                        organizmy[this.iloscOrganizmow++] = new Guarana(this, x, y);
                        break;
                    case 7:
                        organizmy[this.iloscOrganizmow++] = new Mlecz(this, x, y);
                        break;
                    case 8:
                        organizmy[this.iloscOrganizmow++] = new Trawa(this, x, y);
                        break;
                    case 9:
                        organizmy[this.iloscOrganizmow++] = new Wjagody(this, x, y);
                        break;
                }
            }
        }
    }

    public void dodajOrganizm(int x, int y, char r) {
        if (iloscOrganizmow < maxOrganizmow) {
            if (x >= 0 && x < rozmiarX && y >= 0 && y < rozmiarY) {
                switch (r) {
                    case 'A':
                        organizmy[this.iloscOrganizmow++] = new Antylopa(this, x, y);
                        break;
                    case 'L':
                        organizmy[this.iloscOrganizmow++] = new Lis(this, x, y);
                        break;
                    case 'O':
                        organizmy[this.iloscOrganizmow++] = new Owca(this, x, y);
                        break;
                    case 'W':
                        organizmy[this.iloscOrganizmow++] = new Wilk(this, x, y);
                        break;
                    case 'Z':
                        organizmy[this.iloscOrganizmow++] = new Zolw(this, x, y);
                        break;
                    case 'b':
                        organizmy[this.iloscOrganizmow++] = new Barszcz(this, x, y);
                        break;
                    case 'g':
                        organizmy[this.iloscOrganizmow++] = new Guarana(this, x, y);
                        break;
                    case 'm':
                        organizmy[this.iloscOrganizmow++] = new Mlecz(this, x, y);
                        break;
                    case 't':
                        organizmy[this.iloscOrganizmow++] = new Trawa(this, x, y);
                        break;
                    case 'j':
                        organizmy[this.iloscOrganizmow++] = new Wjagody(this, x, y);
                        break;
                }
                if (!wczytuje) {
                    ustawKomunikat("2", String.valueOf(r), "0");
                }
            }
        }
    }

    private Organizm znajdzOrganizm(int x, int y) {
        for (int i = 0; i < this.iloscOrganizmow; i++) {
            if (this.organizmy[i].getPolozenieX() == x && this.organizmy[i].getPolozenieY() == y) {
                return organizmy[i];
            }
        }
        return null;
    }

    public int znajdzPozycje(int x, int y) {
        for (int i = 0; i < this.iloscOrganizmow; i++) {
            if (this.organizmy[i].getPolozenieX() == x && this.organizmy[i].getPolozenieY() == y) {
                return i;
            }
        }
        return -1;
    }

    public Organizm sprawdzPole(int x, int y, int kierunek) {
        Organizm organizm = null;
        switch (kierunek) {
            case 0:
                if (x + 1 < rozmiarX) {
                    organizm = this.znajdzOrganizm(x + 1, y);
                }
                break;
            case 1:
                if (y + 1 < rozmiarY && x + 1 < rozmiarX) {
                    organizm = this.znajdzOrganizm(x + 1, y + 1);
                }
                break;
            case 2:
                if (y + 1 < rozmiarY) {
                    organizm = this.znajdzOrganizm(x, y + 1);
                }
                break;
            case 3:
                if (y + 1 < rozmiarY && x - 1 >= 0) {
                    organizm = this.znajdzOrganizm(x - 1, y + 1);
                }
                break;
            case 4:
                if (x - 1 >= 0) {
                    organizm = this.znajdzOrganizm(x - 1, y);
                }
                break;
            case 5:
                if (y - 1 >= 0 && x - 1 >= 0) {
                    organizm = this.znajdzOrganizm(x - 1, y - 1);
                }
                break;
            case 6:
                if (y - 1 >= 0) {
                    organizm = this.znajdzOrganizm(x, y - 1);
                }
                break;
            case 7:
                if (y - 1 >= 0 && x + 1 < rozmiarX) {
                    organizm = this.znajdzOrganizm(x + 1, y - 1);
                }
                break;
        }
        return organizm;
    }

    public void ustawKomunikat(String opcja, String atakujacy, String przeciwnik) {
        for (int i = 0; i < KOMUNIKATY_X; i++) {
            if ("0".equals(komunikaty[0][i])) {
                komunikaty[0][i] = opcja;
                komunikaty[1][i] = atakujacy;
                komunikaty[2][i] = przeciwnik;
                break;
            }
        }
    }

    public boolean konfrontuj(Organizm atakujacy, Organizm przeciwnik) {
        if (atakujacy.getZnak() == przeciwnik.getZnak()) {
            //ROZMNARZANIE
            if (atakujacy.getPolozenieX() == przeciwnik.getPolozenieX()) {
                //lewo prawo od obu
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), LEWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() - 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), PRAWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() + 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY(), LEWO) == null) {
                    dodajOrganizm(przeciwnik.getPolozenieX() - 1, przeciwnik.getPolozenieY(), przeciwnik.getZnak());
                } else if (sprawdzPole(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY(), PRAWO) == null) {
                    dodajOrganizm(przeciwnik.getPolozenieX() + 1, przeciwnik.getPolozenieY(), przeciwnik.getZnak());
                }
            } else if (atakujacy.getPolozenieY() == przeciwnik.getPolozenieY()) {
                //gora dol od obu
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), GORA) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() - 1, atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), DOL) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() + 1, atakujacy.getZnak());
                } else if (sprawdzPole(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY(), GORA) == null) {
                    dodajOrganizm(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY() - 1, przeciwnik.getZnak());
                } else if (sprawdzPole(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY(), DOL) == null) {
                    dodajOrganizm(przeciwnik.getPolozenieX(), przeciwnik.getPolozenieY() + 1, przeciwnik.getZnak());
                }
            } else if (atakujacy.getPolozenieX() < przeciwnik.getPolozenieX() && atakujacy.getPolozenieY() < przeciwnik.getPolozenieY()) {
                //od A prawo dol
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), PRAWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() + 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), DOL) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() + 1, atakujacy.getZnak());
                }
            } else if (atakujacy.getPolozenieX() > przeciwnik.getPolozenieX() && atakujacy.getPolozenieY() < przeciwnik.getPolozenieY()) {
                //od A lewo dol
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), LEWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() - 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), DOL) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() + 1, atakujacy.getZnak());
                }
            } else if (atakujacy.getPolozenieX() > przeciwnik.getPolozenieX() && atakujacy.getPolozenieY() > przeciwnik.getPolozenieY()) {
                //od A lewo gora
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), LEWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() - 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), GORA) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() - 1, atakujacy.getZnak());
                }
            } else if (atakujacy.getPolozenieX() < przeciwnik.getPolozenieX() && atakujacy.getPolozenieY() > przeciwnik.getPolozenieY()) {
                //od A prawo gora
                if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), PRAWO) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX() + 1, atakujacy.getPolozenieY(), atakujacy.getZnak());
                } else if (sprawdzPole(atakujacy.getPolozenieX(), atakujacy.getPolozenieY(), GORA) == null) {
                    dodajOrganizm(atakujacy.getPolozenieX(), atakujacy.getPolozenieY() - 1, atakujacy.getZnak());
                }
            }
            return false;
        } else {
            if (przeciwnik.getKolizjaSpec()) {
                return !przeciwnik.kolizja(atakujacy);
            } else {
                if (atakujacy.getSila() > przeciwnik.getSila()) {
                    ustawKomunikat("1", String.valueOf(atakujacy.getZnak()), String.valueOf(przeciwnik.getZnak()));
                    usunOrganizm(przeciwnik);
                    return true;
                } else if (przeciwnik.getSila() > atakujacy.getSila()) {
                    ustawKomunikat("1", String.valueOf(przeciwnik.getZnak()), String.valueOf(atakujacy.getZnak()));
                    usunOrganizm(atakujacy);
                    return false;
                } else {
                    return false;
                }
            }
        }
    }

    public void zapisz() throws FileNotFoundException {
        try (PrintWriter zapis = new PrintWriter("save.txt")) {
            zapis.println(rozmiarX + " " + rozmiarY + " " + tura + " " + iloscOrganizmow);
            if (czyCzlowiekZyje) {
                zapis.println(wskCzlowiek.getPolozenieX() + " " + wskCzlowiek.getPolozenieY() + " " + wskCzlowiek.getWiek() + " " + wskCzlowiek.getNiesmiertelnosc() + " " + wskCzlowiek.getCzasNiesmiertelnosci());
            } else {
                zapis.println(-1);
            }
            for (int i = 0; i < iloscOrganizmow; i++) {
                if (organizmy[i].getZnak() != 'C') {
                    zapis.println(organizmNaLiczbe(organizmy[i].getZnak()) + " " + organizmy[i].getPolozenieX() + " " + organizmy[i].getPolozenieY() + " " + organizmy[i].getWiek());
                }
            }
        }
    }

    public void odczytaj() throws FileNotFoundException, IOException {
        BufferedReader inFile = new BufferedReader(new FileReader("save.txt"));
        Scanner wejscie = new Scanner(inFile);
        rozmiarX = wejscie.nextInt();
        rozmiarY = wejscie.nextInt();
        tura = wejscie.nextInt();
        iloscOrganizmow = 0;
        int iloscOrganizmowTmp = wejscie.nextInt();
        maxOrganizmow = rozmiarX * rozmiarY;
        wczytuje = true;
        for (int i = 0; i < rozmiarX * rozmiarX; i++) {
            organizmy[i] = null;
        }
        int x, y, wiek, org;
        wskCzlowiek = null;
        x = wejscie.nextInt();
        if (x != -1) {
            wskCzlowiek = new Czlowiek(this, x, wejscie.nextInt());
            wskCzlowiek.setWiek(wejscie.nextInt());
            wskCzlowiek.setNiesmiertelnosc(wejscie.nextBoolean());
            wskCzlowiek.setCzasNiesmiertelnosci(wejscie.nextInt());
            organizmy[iloscOrganizmow++] = wskCzlowiek;
        }
        for (int i = 0; i < iloscOrganizmowTmp - 1; i++) {
            org = wejscie.nextInt();
            x = wejscie.nextInt();
            y = wejscie.nextInt();
            wiek = wejscie.nextInt();
            dodajOrganizm(x, y, org);
            organizmy[iloscOrganizmow - 1].setWiek(wiek);
        }
    }

    public void usunOrganizm(Organizm org) {
        int pozycja = znajdzPozycje(org.getPolozenieX(), org.getPolozenieY());
        if (organizmy[pozycja].getZnak() == 'C') {
            czyCzlowiekZyje = false;
        }
        organizmy[pozycja] = null;
        //delete organizmy[pozycja];

        for (int i = pozycja; i < iloscOrganizmow - 1; i++) {
            organizmy[i] = organizmy[i + 1];
        }
        organizmy[--iloscOrganizmow] = null;
    }

    private void swap(int j) {
        Organizm tmp = organizmy[j];
        organizmy[j] = organizmy[j - 1];
        organizmy[j - 1] = tmp;
    }

    private void sortuj() {
        for (int i = 0; i < iloscOrganizmow; i++) {
            for (int j = 1; j < iloscOrganizmow - i; j++) {
                if (organizmy[j].getInic() > organizmy[j - 1].getInic()) {
                    swap(j);
                } else if (organizmy[j].getInic() == organizmy[j - 1].getInic()) {
                    if (organizmy[j].getWiek() > organizmy[j - 1].getWiek()) {
                        swap(j);
                    }
                }
            }
        }
    }

    public void wykonajTure() {
        sortuj();
        for (int i = 0; i < this.iloscOrganizmow; i++) {
            this.organizmy[i].setRuch(false);
        }
        for (int i = 0; i < this.iloscOrganizmow; i++) {
            if (organizmy[i] != null) {
                if (!organizmy[i].getRuch()) {
                    this.organizmy[i].setWiek(this.organizmy[i].getWiek() + 1);
                    this.organizmy[i].setRuch(true);
                    this.organizmy[i].akcja();
                }
            }
        }
        this.tura++;
    }

}
