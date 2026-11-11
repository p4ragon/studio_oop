/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bed.breakfast.geminit03;

/**
 *
 * @author Vincenzo
 */
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;

public class GestorePrenotazioni {
    private String name;
    private Set<Prenotazione> elencoPrenotazioni;
    
    public GestorePrenotazioni(String name){
        this.name=name;
        this.elencoPrenotazioni=new TreeSet<>();
    }
    public boolean aggiungiPrenotazione(Prenotazione p,LocalDate dataMinima)throws PrenotazioneInvalidaException{
    if(p.getNumeroNotti()<=0||p.getDataInizio().isBefore(dataMinima)) throw new PrenotazioneInvalidaException("Prenotazione non valida!");
    if(!elencoPrenotazioni.contains(p)){
        elencoPrenotazioni.add(p);
        return true;
    }
    return false;
    }
    public Set<Prenotazione> getPrenotazioniAttive(LocalDate oggi){
        Set<Prenotazione> active=new TreeSet<>();
        for(Prenotazione p:elencoPrenotazioni){
            if(p.isInCorso(oggi)) active.add(p);
        }
        return active;
    }
    public Set<Prenotazione> getPrenotazioniOrdinate(){
        return elencoPrenotazioni;
    }
    public double calcolaIncassoTotale(){
        double stonks=0.00;
        for(Prenotazione p:elencoPrenotazioni){
            stonks+=p.calcolaCostoTotale();
        }
        return stonks;
    }
}
