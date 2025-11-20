import java.io.*;
import java.util.*;

public class CsvVareLæser {

    public static Map<String, Vare> læsVarer(String fil, boolean erTilbud) throws IOException {
        Map<String, Vare> varer = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fil))) {

            reader.readLine(); // spring header over
            String linje;

            while ((linje = reader.readLine()) != null) {
                if (linje.isBlank()) continue;

                String[] dele = linje.split(";");

                String artikelnummer = dele[0].trim();
                String navn = dele[1].trim();
                double pris = parseDouble(dele[3]);
                double mængde = parseDouble(dele[5]);
                String enhed = dele[6].trim();

                Vare vare = new Vare(artikelnummer, navn, pris, mængde, enhed, erTilbud);
                varer.put(artikelnummer, vare);
            }
        }

        return varer;
    }

    private static double parseDouble(String tekst) {
        if (tekst == null) return 0.0;
        try {
            return Double.parseDouble(tekst.replace(",", ".").trim());
        } catch (Exception e) {
            System.out.println("Kunne ikke lave til tal: '" + tekst + "'");
            return 0.0;
        }
    }
}

