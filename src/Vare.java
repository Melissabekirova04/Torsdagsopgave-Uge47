import java.util.Objects;

public class Vare {

    private String artikelnummer;
    private String navn;
    private double pris;
    private double mængde;
    private String enhed;
    private boolean erPåTilbud;

    public Vare(String artikelnummer, String navn, double pris,
                double mængde, String enhed, boolean erPåTilbud) {
        this.artikelnummer = artikelnummer;
        this.navn = navn;
        this.pris = pris;
        this.mængde = mængde;
        this.enhed = enhed;
        this.erPåTilbud = erPåTilbud;
    }

    public String getArtikelnummer() { return artikelnummer; }
    public String getNavn() { return navn; }
    public double getPris() { return pris; }
    public double getMængde() { return mængde; }
    public String getEnhed() { return enhed; }
    public boolean isErPåTilbud() { return erPåTilbud; }

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
