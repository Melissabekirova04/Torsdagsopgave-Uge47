import java.util.*;

public class Kasseapparat {

    private Map<String, Vare> normalVarer;
    private Map<String, Vare> tilbudsVarer;
    private static final double MOMS = 0.25;

    public Kasseapparat(Map<String, Vare> normalVarer, Map<String, Vare> tilbudsVarer) {
        this.normalVarer = normalVarer;
        this.tilbudsVarer = tilbudsVarer;
    }

    // Bruges til betaling: regner kun total, printer ikke noget
    public double beregnTotal(List<Vare> kurv) {
        Map<String, Integer> antal = new HashMap<>();
        for (Vare v : kurv) {
            antal.put(v.getArtikelnummer(), antal.getOrDefault(v.getArtikelnummer(), 0) + 1);
        }

        double total = 0;

        for (String id : antal.keySet()) {
            Vare v = normalVarer.get(id);
            if (v == null) continue;

            int stk = antal.get(id);
            double normalPris = v.getPris();
            Vare tilbud = tilbudsVarer.get(id);
            double brugtPris = (tilbud == null) ? normalPris : tilbud.getPris();

            total += brugtPris * stk;
        }

        return total;
    }

    // Printer kvittering og returnerer total-beløbet (som før)
    public double udskrivBon(List<Vare> kurv) {

        Map<String, Integer> antal = new HashMap<>();
        for (Vare v : kurv) {
            antal.put(v.getArtikelnummer(), antal.getOrDefault(v.getArtikelnummer(), 0) + 1);
        }

        if (kurv.isEmpty()) {
            System.out.println("\nKurven er tom.\n");
            return 0.0;
        }

        System.out.println("\n=============== BON ===============\n");

        double total = 0;
        double rabatTotal = 0;

        for (String id : antal.keySet()) {

            Vare v = normalVarer.get(id);
            if (v == null) continue;

            int stk = antal.get(id);

            double normalPris = v.getPris();
            Vare tilbud = tilbudsVarer.get(id);
            double brugtPris = tilbud == null ? normalPris : tilbud.getPris();

            double linjePris = brugtPris * stk;
            double rabat = (normalPris * stk) - linjePris;

            total += linjePris;
            rabatTotal += Math.max(rabat, 0);

            System.out.println(v.getNavn());
            System.out.println("  " + stk + " x " + brugtPris + " kr = " + linjePris + " kr");

            if (rabat > 0) {
                System.out.println("  * RABAT: -" + rabat + " kr");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------");
        System.out.println("Rabat: -" + rabatTotal + " kr");
        System.out.println("TOTAL: " + total + " kr");
        System.out.println("====================================\n");

        return total;
    }
}
