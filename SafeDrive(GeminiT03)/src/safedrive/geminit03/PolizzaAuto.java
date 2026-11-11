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
public class PolizzaAuto extends Polizza {
    private int classeDiMerito;
    public PolizzaAuto(String numeroPolizza,String intestatario, LocalDate dataScadenza, double massimale,int classeDiMerito){
        super(numeroPolizza,intestatario,dataScadenza,massimale);
        this.classeDiMerito=classeDiMerito;
    }
    @Override
public double calcolaPremioAnnuale() {
    double quotaBase = this.getMassimale() * 0.01;
    if (this.classeDiMerito > 10) {
        return quotaBase * 1.20; // +20% sulla quota base
    }
    return quotaBase;
}
}
