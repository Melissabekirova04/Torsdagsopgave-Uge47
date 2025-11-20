import java.util.*;

public class TextIO {

    private Scanner scanner = new Scanner(System.in);

    // Scan vare ved del af navn
    public void scanVare(List<Vare> kurv, Collection<Vare> alleVarer) {

        System.out.print("Skriv varenavn (eller del af navnet): ");
        String søg = scanner.nextLine().toLowerCase();

        List<Vare> fundne = new ArrayList<>();

        for (Vare v : alleVarer) {
            if (v.getNavn().toLowerCase().contains(søg)) {
                fundne.add(v);
            }
        }

        if (fundne.isEmpty()) {
            System.out.println("Ingen varer fundet.\n");
            return;
        }

        Vare valgt;

        if (fundne.size() == 1) {
            valgt = fundne.get(0);
        } else {
            System.out.println("Vælg vare:");
            for (int i = 0; i < fundne.size(); i++) {
                System.out.println((i + 1) + ") " + fundne.get(i).getNavn());
            }
            System.out.print("Nummer: ");
            int nr = Integer.parseInt(scanner.nextLine());
            valgt = fundne.get(nr - 1);
        }

        System.out.print("Hvor mange? ");
        int antal = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < antal; i++) {
            kurv.add(valgt);
        }

        System.out.println("Tilføjede " + antal + " stk. af: " + valgt.getNavn() + "\n");
    }

    public void fjernVare(List<Vare> kurv) {

        if (kurv.isEmpty()) {
            System.out.println("Kurven er tom.\n");
            return;
        }

        System.out.print("Skriv navn (eller del af navn) på vare der skal fjernes: ");
        String søg = scanner.nextLine().toLowerCase();

        List<Vare> unikke = new ArrayList<>();
        for (Vare v : kurv) {
            if (!unikke.contains(v)) unikke.add(v);
        }

        List<Vare> fundne = new ArrayList<>();
        for (Vare v : unikke) {
            if (v.getNavn().toLowerCase().contains(søg)) {
                fundne.add(v);
            }
        }

        if (fundne.isEmpty()) {
            System.out.println("Ingen varer fundet i kurven.\n");
            return;
        }

        Vare valgt;

        if (fundne.size() == 1) {
            valgt = fundne.get(0);
        } else {
            System.out.println("Vælg vare:");
            for (int i = 0; i < fundne.size(); i++) {
                System.out.println((i + 1) + ") " + fundne.get(i).getNavn());
            }
            System.out.print("Nummer: ");
            int nr = Integer.parseInt(scanner.nextLine());
            valgt = fundne.get(nr - 1);
        }

        System.out.print("Hvor mange vil du fjerne? ");
        int antal = Integer.parseInt(scanner.nextLine());

        int fjernet = 0;
        for (int i = kurv.size() - 1; i >= 0 && fjernet < antal; i--) {
            if (kurv.get(i).equals(valgt)) {
                kurv.remove(i);
                fjernet++;
            }
        }

        System.out.println("Fjernede " + fjernet + " stk af: " + valgt.getNavn() + "\n");
    }

    // Betaling: vælg kort eller kontant. Returnerer true hvis betaling lykkes.
    public boolean betal(double total) {

        if (total <= 0) {
            System.out.println("Der er intet at betale.");
            return false;
        }

        System.out.println("Beløb der skal betales: " + total + " kr");

        while (true) {
            System.out.print("Betaling med (1) kort, (2) kontant, (0) fortryd: ");
            String valg = scanner.nextLine().trim();

            if (valg.equals("1")) {
                return betalMedKort(total);
            } else if (valg.equals("2")) {
                return betalKontant(total);
            } else if (valg.equals("0")) {
                System.out.println("Betaling afbrudt.");
                return false;
            } else {
                System.out.println("Ugyldigt valg.");
            }
        }
    }

    private boolean betalMedKort(double total) {
        System.out.println("Indsæt eller hold kortet op til terminalen...");
        System.out.print("Indtast pinkode (4 cifre): ");
        String pin = scanner.nextLine().trim();

        if (pin.length() != 4 || !pin.chars().allMatch(Character::isDigit)) {
            System.out.println("Forkert format på pinkode. Vi prøver igen.\n");
            return betalMedKort(total); // simpel rekursiv prøv-igen
        }

        System.out.println("Behandler betaling...");
        System.out.println("Betaling med kort godkendt for " + total + " kr.\n");
        return true;
    }

    private boolean betalKontant(double total) {

        double betalt = 0.0;

        while (betalt < total) {
            double rest = total - betalt;
            System.out.print("Kontant betaling (mangler " + rest + " kr): ");
            String tekst = scanner.nextLine();

            double beløb;
            try {
                beløb = Double.parseDouble(tekst.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Skriv et tal, fx 50 eller 50,00");
                continue;
            }

            if (beløb <= 0) {
                System.out.println("Beløbet skal være positivt.");
                continue;
            }

            betalt += beløb;
        }

        double bytte = betalt - total;
        System.out.println("Betaling modtaget. Byttepenge: " + bytte + " kr.\n");
        return true;
    }
}

