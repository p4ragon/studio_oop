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
public class PrenotazioneDeluxe extends Prenotazione{
    private boolean servizioNavetta;
    private int numeroAccessiSpa;
    public PrenotazioneDeluxe(String codice, String nomeOspite, LocalDate dataInizio, int numeroNotti, double prezzoPerNotte,boolean servizioNavetta,int numeroAccessiSpa){
    super(codice,nomeOspite,dataInizio,numeroNotti,prezzoPerNotte);
    this.numeroAccessiSpa=numeroAccessiSpa;
    this.servizioNavetta=servizioNavetta;
}
    public boolean usesServizioNavetta(){return servizioNavetta;}
    
    @Override
    public double calcolaCostoTotale(){
        double brungs=this.getNumeroNotti()*this.getPrezzoPerNotte();
        if(servizioNavetta){
            brungs+=40.00;
        }
        return brungs+25*numeroAccessiSpa;
    }
}
