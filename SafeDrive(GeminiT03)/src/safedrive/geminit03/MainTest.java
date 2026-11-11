/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safedrive.geminit03;

/**
 *
 * @author Vincenzo
 */
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== TEST REGISTRO ASSICURATIVO (LocalDate) ===\n");

        RegistroAssicurativo agenzia = new RegistroAssicurativo("Campania Assicura");

        // Fissiamo una data di riferimento per il test
        LocalDate oggi = LocalDate.of(2026, Month.OCTOBER, 26);
        System.out.println("Data di riferimento odierna: " + oggi + "\n");

        // 1. Creazione delle polizze con diverse scadenze
        Polizza auto1 = new PolizzaAuto(
            "POL-A01", "Ciro Esposito", LocalDate.of(2027, Month.MARCH, 15), 20000.0, 4 // Massimale 20000, classe virtuosa
        ); // Quota: 20000 * 0.01 = 200.0€

        Polizza autoRischio = new PolizzaAuto(
            "POL-A02", "Marco De Rosa", LocalDate.of(2026, Month.DECEMBER, 1), 15000.0, 12 // Classe 12 (>10) -> +20%
        ); // Quota base: 15000 * 0.01 = 150.0€ -> +20% = 180.0€

        Polizza vitaOver50 = new PolizzaVita(
            "POL-V01", "Giovanni Bianchi", LocalDate.of(2028, Month.JUNE, 30), 100000.0, 1968, true // Nato 1968, fumatore
        ); // Quota base: 100000 * 0.02 = 2000.0€ | Età al 2028: 60 (>50) -> +150€ | Fumatore -> +100€ | Tot: 2250.0€

        Polizza autoScaduta = new PolizzaAuto(
            "POL-A03", "Anna Coppola", LocalDate.of(2026, Month.JANUARY, 10), 10000.0, 1
        ); // Scaduta rispetto alla data odierna

        // 2. Test Inserimento ed Eccezioni su Data Scaduta
        System.out.println("--- Test Registrazione Polizze ---");
        try {
            System.out.println("Registrazione auto1: " + agenzia.aggiungiPolizza(auto1, oggi));           // true
            System.out.println("Registrazione autoRischio: " + agenzia.aggiungiPolizza(autoRischio, oggi)); // true
            System.out.println("Registrazione vitaOver50: " + agenzia.aggiungiPolizza(vitaOver50, oggi));   // true

            // Tentativo duplicato numero polizza
            Polizza clone = new PolizzaAuto("POL-A01", "Altro Nome", LocalDate.of(2027, Month.MAY, 1), 18000.0, 2);
            System.out.println("Registrazione duplicato (POL-A01): " + agenzia.aggiungiPolizza(clone, oggi)); // false

            // Tentativo inserimento polizza già scaduta -> deve lanciare eccezione
            System.out.println("Tentativo registrazione polizza già scaduta (POL-A03)...");
            agenzia.aggiungiPolizza(autoScaduta, oggi);
        } catch (PolizzaNonValidaException e) {
            System.out.println("Eccezione catturata correttamente: " + e.getMessage());
        }

        // 3. Test Polimorfismo nel calcolo dei premi
        System.out.println("\n--- Calcolo Premi Annuali ---");
        System.out.println("Premio auto1: " + auto1.calcolaPremioAnnuale() + "€");
        System.out.println("Premio autoRischio (+20% classe 12): " + autoRischio.calcolaPremioAnnuale() + "€");
        System.out.println("Premio vitaOver50 (Over 50 + Fumatore): " + vitaOver50.calcolaPremioAnnuale() + "€");

        // 4. Test Ordinamento Cronologico (compareTo su LocalDate)
        System.out.println("\n--- Polizze nel Registro Ordinate per Data Scadenza Crescente ---");
        List<Polizza> ordinate = agenzia.getPolizzeOrdinatePerScadenza();
        for (Polizza p : ordinate) {
            System.out.println("- [" + p.getNumeroPolizza() + "] Scadenza: " + p.getDataScadenza() 
                               + " | Intestatario: " + p.getIntestatario()
                               + " | Premio: " + p.calcolaPremioAnnuale() + "€");
        }

        // 5. Test Filtraggio Scadenze a Breve Termine
        LocalDate soglia = LocalDate.of(2027, Month.JANUARY, 1);
        System.out.println("\n--- Polizze in scadenza prima o entro il " + soglia + " ---");
        List<Polizza> prossimeScadenze = agenzia.getPolizzeInScadenza(soglia);
        for (Polizza p : prossimeScadenze) {
            System.out.println("-> Scadenza imminente: " + p.getNumeroPolizza() + " il " + p.getDataScadenza());
        }

        // 6. Test Totale Premi Attivi
        System.out.println("\n--- Incasso Totale Premi Attivi ---");
        System.out.println("Totale incasso calcolato: " + agenzia.calcolaTotalePremiAttivi(oggi) + "€");
    }
}

