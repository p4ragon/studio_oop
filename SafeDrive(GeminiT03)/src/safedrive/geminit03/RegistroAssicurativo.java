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
import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.sort;

public class RegistroAssicurativo {
    private List<Polizza> elencoPolizze;
    private String name;
    public RegistroAssicurativo(String name){
        this.name=name;
        this.elencoPolizze=new ArrayList<>();
    }
    public boolean aggiungiPolizza(Polizza p, LocalDate oggi) throws PolizzaNonValidaException{
        if(p.isScaduta(oggi)) throw new PolizzaNonValidaException("Non si possono stipulare contratti già scaduti");
        if(elencoPolizze.contains(p)) return false;
        elencoPolizze.add(p);
        return true;
    }
    public List<Polizza> getPolizzeInScadenza(LocalDate dataLimite){
        List<Polizza> scadenze=new ArrayList<>();
        for(Polizza polizza:elencoPolizze){
            if(polizza.getDataScadenza().isBefore(dataLimite)||polizza.getDataScadenza().equals(dataLimite)){
                scadenze.add(polizza);
            }
        }
        return scadenze;
    }
    public List<Polizza> getPolizzeOrdinatePerScadenza(){
        elencoPolizze.sort(null);
        return elencoPolizze;
    }
    public double calcolaTotalePremiAttivi(LocalDate oggi){
        double brungs=0.0;
        for(Polizza polizza:elencoPolizze){
            if(!polizza.isScaduta(oggi)) brungs=brungs+polizza.calcolaPremioAnnuale();
        }
        return brungs;
    }
}
