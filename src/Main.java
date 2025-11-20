import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Map<String, Vare> alleVarer = CsvVareLæser.læsVarer("data/varer.csv", false);
        Map<String, Vare> tilbudsVarer = CsvVareLæser.læsVarer("data/tilbud.csv", true);

        Kasseapparat kasse = new Kasseapparat(alleVarer, tilbudsVarer);
        TextIO io = new TextIO();

        List<Vare> kurv = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean kører = true;

        while (kører) {
            System.out.println("""
                    
                    ======= MENU =======
                    1) Scan vare
                    2) Fjern vare
                    3) Vis bon (uden at betale)
                    4) Betal og print bon
                    0) Afslut
                    """);

            System.out.print("Vælg: ");
            int valg = Integer.parseInt(scanner.nextLine());

            switch (valg) {
                case 1 -> io.scanVare(kurv, alleVarer.values());
                case 2 -> io.fjernVare(kurv);
                case 3 -> kasse.udskrivBon(kurv);
                case 4 -> {
                    double total = kasse.beregnTotal(kurv);
                    if (total <= 0) {
                        System.out.println("Kurven er tom. Der er intet at betale.\n");
                        break;
                    }
                    boolean betalt = io.betal(total);
                    if (betalt) {
                        // betaling godkendt → print bon til kunden
                        kasse.udskrivBon(kurv);
                        kører = false;
                    }
                }
                case 0 -> kører = false;
                default -> System.out.println("Ugyldigt valg.");
            }
        }

        System.out.println("Programmet er afsluttet.");
    }
}
