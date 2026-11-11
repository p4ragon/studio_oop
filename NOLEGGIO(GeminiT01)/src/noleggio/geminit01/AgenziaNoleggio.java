/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noleggio.geminit01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincenzo
 */
public class AgenziaNoleggio {
    private final String name;
    private final List<Veicolo> parcoVeicoli;
    public AgenziaNoleggio(String name){
        this.name=name;
        this.parcoVeicoli=new ArrayList<>();
    }
    public boolean aggiungiVeicolo(Veicolo v){
        if(parcoVeicoli.contains(v)) return false;
        parcoVeicoli.add(v);
        return true;
    }
    public Veicolo cercaVeicolo(String targa){
        for(Veicolo v:parcoVeicoli){
            if(v.getTarga().equalsIgnoreCase(targa)) return v;
        }
        return null;
    }
    public void noleggiaVeicolo(String targa) throws VeicoloNonDisponibileException{
        Veicolo v=this.cercaVeicolo(targa);
        if(v==null||v.isRented()) throw new VeicoloNonDisponibileException("Veicolo non presente oppure già noleggiato");
        v.setNoleggiato(true);
    }
    public void restituisciVeicolo(String targa){
        Veicolo v=this.cercaVeicolo(targa);
        if(v!=null) v.setNoleggiato(false);
    }
    public List<Veicolo> getVeicoliDisponibiliOrdinatiPerTariffa(){
        List<Veicolo> lista=new ArrayList<>();
        for(Veicolo v:parcoVeicoli){
            if(!v.isRented())lista.add(v);
        }
        lista.sort(null);
        return lista;
    }
    public double stimaIncassoTotale(int giorniNoleggio){
        double stonks=0.0;
        for(Veicolo v:parcoVeicoli){
            if(v.isRented()) stonks+=v.calcolaCostoNoleggio(giorniNoleggio);
        }
        return stonks;
    }
            
}
