/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safedrive.geminit03;

import java.time.LocalDate;

/**
 *
 * @author Vincenzo
 */
public class PolizzaVita extends Polizza {
    private final int annoNascitaIntestatario;
    private boolean fumatore;
    public PolizzaVita(String numeroPolizza,String intestatario, LocalDate dataScadenza, double massimale,int annoNascitaIntestatario,boolean fumatore){
        super(numeroPolizza,intestatario,dataScadenza,massimale);
        this.fumatore=fumatore;
        this.annoNascitaIntestatario=annoNascitaIntestatario;
    }
    @Override
public double calcolaPremioAnnuale() {
    double quota = this.getMassimale() * 0.02; // 2% del massimale
    int eta = this.getDataScadenza().getYear() - this.annoNascitaIntestatario;

    if (eta > 50) {
        quota += 150.0; // 150 euro, NON 150000
    }
    if (this.fumatore) {
        quota += 100.0; // 100 euro, NON 100000
    }

    return quota;
}
    
}
