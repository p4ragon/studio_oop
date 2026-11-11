/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noleggio.geminit01;

/**
 *
 * @author Vincenzo
 */
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== TEST PARCO VEICOLI (CON ARRAYLIST) ===\n");

        AgenziaNoleggio agenzia = new AgenziaNoleggio("Salerno Rent Hub");

        // 1. Creazione veicoli
        Veicolo auto1 = new Automobile("FA123AA", "Fiat", "Panda", 35.0, 4);
        Veicolo auto2 = new Automobile("FB456BB", "Toyota", "Proace Verso", 60.0, 8);
        Veicolo furgone1 = new Furgone("FC789CC", "Iveco", "Daily", 75.0, 1500.0);
        Veicolo furgone2 = new Furgone("FD012DD", "Ford", "Transit Custom", 50.0, 800.0);

        // 2. Aggiunta alla flotta (verifica contains ed equals)
        System.out.println("--- Test Inserimento ---");
        System.out.println("Aggiunta Panda: " + agenzia.aggiungiVeicolo(auto1));           // true
        System.out.println("Aggiunta Proace: " + agenzia.aggiungiVeicolo(auto2));          // true
        System.out.println("Aggiunta Daily: " + agenzia.aggiungiVeicolo(furgone1));        // true
        System.out.println("Aggiunta Transit: " + agenzia.aggiungiVeicolo(furgone2));      // true

        // Tentativo inserimento duplicato con stessa targa ma oggetto diverso
        Veicolo clonePanda = new Automobile("FA123AA", "Fiat", "Panda Cross", 45.0, 5);
        boolean duplicatoAggiunto = agenzia.aggiungiVeicolo(clonePanda);
        System.out.println("Aggiunta veicolo con targa duplicata FA123AA: " + duplicatoAggiunto); // false

        // 3. Test Polimorfismo nel calcolo tariffe
        System.out.println("\n--- Calcolo Costo Noleggio (3 Giorni) ---");
        System.out.println(auto1.getModello() + " (Base 35, 4 posti): " + auto1.calcolaCostoNoleggio(3) + "€"); 
        // 35 * 3 = 105.0€
        System.out.println(auto2.getModello() + " (Base 60, 8 posti > 5 -> +10%): " + auto2.calcolaCostoNoleggio(3) + "€"); 
        // 60 * 3 * 1.10 = 198.0€
        System.out.println(furgone1.getModello() + " (Base 75, >1000kg -> +5€/g): " + furgone1.calcolaCostoNoleggio(3) + "€"); 
        // (75 * 3) + (5 * 3) = 240.0€

        // 4. Test Noleggio e Gestione Eccezioni
        System.out.println("\n--- Test Noleggi ed Eccezioni ---");
        try {
            agenzia.noleggiaVeicolo("FB456BB");
            System.out.println("Noleggio FB456BB avvenuto con successo.");

            // Tentativo di noleggiare lo stesso veicolo già occupato
            System.out.println("Tentativo secondo noleggio su FB456BB...");
            agenzia.noleggiaVeicolo("FB456BB");
        } catch (VeicoloNonDisponibileException e) {
            System.out.println("Eccezione catturata con successo: " + e.getMessage());
        }

        try {
            // Tentativo di noleggiare una targa inesistente
            System.out.println("Tentativo noleggio targa inventata ZZ999ZZ...");
            agenzia.noleggiaVeicolo("ZZ999ZZ");
        } catch (VeicoloNonDisponibileException e) {
            System.out.println("Eccezione catturata con successo: " + e.getMessage());
        }

        // 5. Test Filtraggio e Ordinamento (Collections.sort su List)
        System.out.println("\n--- Veicoli Disponibili Ordinati per Tariffa Crescente ---");
        List<Veicolo> disponibili = agenzia.getVeicoliDisponibiliOrdinatiPerTariffa();
        for (Veicolo v : disponibili) {
            System.out.println("- [" + v.getTarga() + "] " + v.getMarca() + " " + v.getModello() 
                               + " -> " + v.getTariffaBaseGiornaliera() + "€/giorno");
        }

        // 6. Test Stima Incassi Totali
        System.out.println("\n--- Stima Incassi Flotta Noleggiata ---");
        System.out.println("Incasso stimato per i veicoli attualmente noleggiati (4 giorni): " 
                           + agenzia.stimaIncassoTotale(4) + "€");
    }
}