/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrosportivo.geminit02;

/**
 *
 * @author Vincenzo
 */
import java.util.Set;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== TEST CENTRO SPORTIVO (CON SET / TREESET) ===\n");

        CentroSportivo centro = new CentroSportivo("FitSalerno Campus");

        // 1. Creazione istanze
        Abbonamento open1 = new AbbonamentoOpen("AB-101", "Mario Rossi", 40.0, true);   // Quota: 40 + 15 = 55.0
        Abbonamento open2 = new AbbonamentoOpen("AB-102", "Giulia Verdi", 40.0, false); // Quota: 40.0 (stesso costo base di open1, nome diverso)
        Abbonamento corsi1 = new AbbonamentoCorsi("AB-201", "Luca Bianchi", 25.0, 2, 5.0); // Quota: 25 + 40 = 65.0
        Abbonamento corsiScontato = new AbbonamentoCorsi("AB-202", "Anna Neri", 30.0, 4, 6.0); // Quota: (30 + 96) * 0.9 = 113.4

        // 2. Registrazione su TreeSet (il Set rifiuta elementi uguali secondo compareTo/equals)
        System.out.println("--- Inserimento nel Set ---");
        System.out.println("Registrazione open1: " + centro.registraAbbonamento(open1));             // true
        System.out.println("Registrazione open2: " + centro.registraAbbonamento(open2));             // true
        System.out.println("Registrazione corsi1: " + centro.registraAbbonamento(corsi1));           // true
        System.out.println("Registrazione corsiScontato: " + centro.registraAbbonamento(corsiScontato)); // true

        // Tentativo di inserimento duplicato
        Abbonamento clone = new AbbonamentoOpen("AB-101", "Mario Rossi", 40.0, true);
        System.out.println("Registrazione duplicato identico: " + centro.registraAbbonamento(clone)); // false

        // 3. Test Polimorfismo e Calcolo Quota
        System.out.println("\n--- Calcolo Quote Mensili (Polimorfismo) ---");
        System.out.println(open1.getNomeCliente() + " (Open + SPA): " + open1.calcolaQuotaMensile() + "€");
        System.out.println(open2.getNomeCliente() + " (Open standard): " + open2.calcolaQuotaMensile() + "€");
        System.out.println(corsi1.getNomeCliente() + " (Corsi base): " + corsi1.calcolaQuotaMensile() + "€");
        System.out.println(corsiScontato.getNomeCliente() + " (Corsi con sconto): " + corsiScontato.calcolaQuotaMensile() + "€");

        // 4. Test Sospensione ed Eccezione
        System.out.println("\n--- Test Sospensione ed Eccezioni ---");
        try {
            System.out.println("Sospensione abbonamento AB-102...");
            centro.sospendiAbbonamento("AB-102");
            System.out.println("Abbonamento AB-102 sospeso con successo.");

            // Ricerca codice inesistente
            System.out.println("Ricerca codice inesistente XY-999...");
            centro.cercaAbbonamento("XY-999");
        } catch (AbbonamentoNonTrovatoException e) {
            System.out.println("Eccezione catturata correttamente: " + e.getMessage());
        }

        // 5. Test Lettura dal Set Ordinato (già ordinato naturalmente da TreeSet)
        System.out.println("\n--- Abbonamenti Attivi Estratti dal Set (Ordinati per Tariffa/Nome) ---");
        Set<Abbonamento> attivi = centro.getAbbonamentiAttiviOrdinati();
        for (Abbonamento a : attivi) {
            System.out.println("- [" + a.getCodice() + "] " + a.getNomeCliente() 
                               + " | Costo Base: " + a.getCostoBaseMensile() + "€"
                               + " | Quota Effettiva: " + a.calcolaQuotaMensile() + "€");
        }

        // 6. Test Incasso Mensile Totale (esclude il sospeso AB-102)
        System.out.println("\n--- Stima Incasso Totale Mensile ---");
        System.out.println("Incasso totale previsto (solo abbonamenti attivi): " 
                           + centro.calcolaIncassoMensileTotale() + "€");
    }
}