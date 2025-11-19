import java.util.Objects;

public class Vare {

    // Hver vare har et unikt artikelnummer (som stregkoden i virkeligheden)
    private String artikelnummer;
    private String navn;     // Navnet på varen, fx "Banan"
    private double pris;     // Pris pr. stk eller pr. enhed
    private double mængde;   // Hvor meget der er i varen (fx 1 stk, 500 g)
    private String enhed;    // Enheden, fx "stk", "g", "ml"

    private boolean erPåTilbud; // Fortæller om varen kommer fra tilbudslisten

    public Vare(String artikelnummer, String navn, double pris,
                double mængde, String enhed, boolean erPåTilbud) {

        this.artikelnummer = artikelnummer;
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
        this.enhed = enhed;
        this.erPåTilbud = erPåTilbud;
    }

    public String getArtikelnummer() {
        return artikelnummer;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public double getMængde() {
        return mængde;
    }

    public String getEnhed() {
        return enhed;
    }

    public boolean isErPåTilbud() {
        return erPåTilbud;
    }

    // To varer er ens, hvis de har samme artikelnummer
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vare)) return false;
        Vare vare = (Vare) o;
        return Objects.equals(artikelnummer, vare.artikelnummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelnummer);
    }
}
