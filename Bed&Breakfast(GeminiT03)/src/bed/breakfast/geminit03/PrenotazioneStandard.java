/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bed.breakfast.geminit03;

import java.time.LocalDate;

/**
 *
 * @author Vincenzo
 */
public class PrenotazioneStandard extends Prenotazione{
    private boolean colazioneInclusa;
    
    public PrenotazioneStandard(String codice, String nomeOspite, LocalDate dataInizio, int numeroNotti, double prezzoPerNotte,boolean colazioneInclusa){
    super(codice,nomeOspite,dataInizio,numeroNotti,prezzoPerNotte);
    this.colazioneInclusa=colazioneInclusa;
}
    public boolean isColazioneInclusa(){return colazioneInclusa;}
    
    @Override
    public double calcolaCostoTotale(){
        if(colazioneInclusa){
            if(this.getNumeroNotti()<7) return (this.getPrezzoPerNotte()+8.00)*this.getNumeroNotti();
            return (this.getPrezzoPerNotte()+8.00)*this.getNumeroNotti()*0.9;
        }
        if(this.getNumeroNotti()<7) return (this.getPrezzoPerNotte())*this.getNumeroNotti();
        return (this.getPrezzoPerNotte())*this.getNumeroNotti()*0.9;
    }
    
}
