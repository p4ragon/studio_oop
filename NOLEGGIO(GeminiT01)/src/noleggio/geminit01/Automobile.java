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
public class Automobile extends Veicolo{
    private final int numeroPosti;
    public Automobile(String targa,String marca,String modello,double tariffaBaseGiornaliera,boolean noleggiato,int numeroPosti){
        super(targa,marca,modello,tariffaBaseGiornaliera,noleggiato);
        this.numeroPosti=numeroPosti;
    }
    public Automobile(String targa,String marca,String modello,double tariffaBaseGiornaliera,int numeroPosti){
        super(targa,marca,modello,tariffaBaseGiornaliera);
        this.numeroPosti=numeroPosti;
    }
    @Override
    public double calcolaCostoNoleggio(int giorni){
        if(numeroPosti>5) return 1.10*this.getTariffaBaseGiornaliera()*giorni;
        return this.getTariffaBaseGiornaliera()*giorni;
    }
}
