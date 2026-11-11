package bed.breakfast.geminit03;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== TEST GESTORE PRENOTAZIONI (CON TREESET) ===\n");

        GestorePrenotazioni gestore = new GestorePrenotazioni("Costiera Sunset B&B");
        LocalDate dataAperturaStagione = LocalDate.of(2026, Month.JUNE, 1);
        LocalDate oggi = LocalDate.of(2026, Month.JULY, 10);

        // 1. Creazione istanze
        // Standard: 3 notti a 70€ con colazione (+8€/notte) -> (70 * 3) + (8 * 3) = 210 + 24 = 234.0€
        Prenotazione std1 = new PrenotazioneStandard(
            "PR-001", "Paolo Rossi", LocalDate.of(2026, Month.JULY, 8), 3, 70.0, true
        );

        // Standard lunga: 7 notti a 50€ senza colazione -> (50 * 7) = 350€ -> Sconto 10% (>=7 notti) -> 315.0€
        Prenotazione stdLunga = new PrenotazioneStandard(
            "PR-002", "Elena De Luca", LocalDate.of(2026, Month.AUGUST, 1), 7, 50.0, false
        );

        // Deluxe: 4 notti a 120€, 2 ingressi SPA (+50€), Navetta (+40€) -> (120 * 4) + 50 + 40 = 480 + 90 = 570.0€
        Prenotazione del1 = new PrenotazioneDeluxe(
            "PR-003", "Giuseppe Verdi", LocalDate.of(2026, Month.JULY, 9), 4, 120.0, true, 2
        );

        // Prenotazione con data antecedente all'apertura (deve lanciare eccezione)
        Prenotazione invalidaData = new PrenotazioneStandard(
            "PR-004", "Mario Neri", LocalDate.of(2026, Month.MAY, 20), 2, 60.0, false
        );

        // 2. Inserimento nel TreeSet ed Eccezioni
        System.out.println("--- Test Registrazione nel TreeSet ---");
        try {
            System.out.println("Inserimento std1: " + gestore.aggiungiPrenotazione(std1, dataAperturaStagione));       // true
            System.out.println("Inserimento stdLunga: " + gestore.aggiungiPrenotazione(stdLunga, dataAperturaStagione)); // true
            System.out.println("Inserimento del1: " + gestore.aggiungiPrenotazione(del1, dataAperturaStagione));       // true

            // Test codice duplicato: il TreeSet usa compareTo/equals per rifiutare duplicati
            Prenotazione duplicato = new PrenotazioneStandard(
                "PR-001", "Altro Ospite", LocalDate.of(2026, Month.JULY, 15), 2, 80.0, false
            );
            System.out.println("Inserimento duplicato (PR-001): " + gestore.aggiungiPrenotazione(duplicato, dataAperturaStagione)); // false

            // Test eccezione su data non valida
            System.out.println("Tentativo inserimento prenotazione antecedente apertura...");
            gestore.aggiungiPrenotazione(invalidaData, dataAperturaStagione);
        } catch (PrenotazioneInvalidaException e) {
            System.out.println("Eccezione catturata correttamente: " + e.getMessage());
        }

        // 3. Test Polimorfismo nel Calcolo Costi
        System.out.println("\n--- Calcolo Costo Singole Prenotazioni (Polimorfismo) ---");
        System.out.println(std1.getNomeOspite() + " (Std con colazione): " + std1.calcolaCostoTotale() + "€"); 
        System.out.println(stdLunga.getNomeOspite() + " (Std 7 notti -10%): " + stdLunga.calcolaCostoTotale() + "€"); 
        System.out.println(del1.getNomeOspite() + " (Deluxe con SPA e Navetta): " + del1.calcolaCostoTotale() + "€"); 

        // 4. Test Soggiorni in Corso al 10 Luglio 2026
        // std1: 8 Luglio -> fine 11 Luglio (IN CORSO)
        // del1: 9 Luglio -> fine 13 Luglio (IN CORSO)
        // stdLunga: 1 Agosto -> fine 8 Agosto (FUTURA)
        System.out.println("\n--- Prenotazioni in Corso al " + oggi + " ---");
        Set<Prenotazione> inCorso = gestore.getPrenotazioniAttive(oggi);
        for (Prenotazione p : inCorso) {
            System.out.println("- [" + p.getCodice() + "] " + p.getNomeOspite() 
                               + " | Arrivo: " + p.getDataInizio() 
                               + " | Partenza: " + p.getDataFine());
        }

        // 5. Test Lettura dal Set (già ordinato cronologicamente da TreeSet tramite compareTo)
        System.out.println("\n--- Tutte le Prenotazioni nel Set (Ordinamento Naturale) ---");
        Set<Prenotazione> ordinate = gestore.getPrenotazioniOrdinate();
        for (Prenotazione p : ordinate) {
            System.out.println("- [" + p.getCodice() + "] Dal " + p.getDataInizio() 
                               + " (" + p.getNumeroNotti() + " notti) - Ospite: " + p.getNomeOspite());
        }

        // 6. Test Incasso Totale
        // 234.0 (std1) + 315.0 (stdLunga) + 570.0 (del1) = 1119.0€
        System.out.println("\n--- Incasso Totale Previsto ---");
        System.out.println("Totale incasso calcolato: " + gestore.calcolaIncassoTotale() + "€");
    }
}