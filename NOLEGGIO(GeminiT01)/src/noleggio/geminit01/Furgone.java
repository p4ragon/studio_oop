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
public class Furgone extends Veicolo{
    private final double capacitaCaricoKg;
    public Furgone(String targa,String marca,String modello,double tariffaBaseGiornaliera,boolean noleggiato,double capacitaCaricoKg){
        super(targa,marca,modello,tariffaBaseGiornaliera,noleggiato);
        this.capacitaCaricoKg=capacitaCaricoKg;
    }
    public Furgone(String targa,String marca,String modello,double tariffaBaseGiornaliera,double capacitaCaricoKg){
        super(targa,marca,modello,tariffaBaseGiornaliera);
        this.capacitaCaricoKg=capacitaCaricoKg;
    }
    @Override
    public double calcolaCostoNoleggio(int giorni){
        if(capacitaCaricoKg>1000) return (this.getTariffaBaseGiornaliera()+5)*giorni;
        return this.getTariffaBaseGiornaliera()*giorni;
    }
}
