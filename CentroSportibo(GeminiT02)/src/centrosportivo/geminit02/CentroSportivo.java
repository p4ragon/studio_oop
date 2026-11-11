/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrosportivo.geminit02;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Vincenzo
 */
public class CentroSportivo {
    private String name;
    private Set<Abbonamento> elencoAbbonamenti;
    public CentroSportivo(String name){
        this.name=name;
        this.elencoAbbonamenti=new TreeSet<>();
    }
    public boolean registraAbbonamento(Abbonamento a){
        if(elencoAbbonamenti.contains(a)) return false;
        elencoAbbonamenti.add(a);
        return true;
    }
    public Abbonamento cercaAbbonamento(String codice) throws AbbonamentoNonTrovatoException{
        for(Abbonamento a:elencoAbbonamenti){
            if(a.getCodice().equalsIgnoreCase(codice)) return a;
        }
        throw new AbbonamentoNonTrovatoException("Abbonamento non trovato");
    }
    public void sospendiAbbonamento(String codice)throws AbbonamentoNonTrovatoException{
        cercaAbbonamento(codice).setActive(false);
    }
    public Set<Abbonamento> getAbbonamentiAttiviOrdinati(){
        Set<Abbonamento> temp;
        temp=new TreeSet<>();
        for(Abbonamento a:elencoAbbonamenti){
            if(a.isActive()) temp.add(a);
        }
        return temp;
    }
    public double calcolaIncassoMensileTotale(){
        Set<Abbonamento>temp=this.getAbbonamentiAttiviOrdinati();
        Double stonks=0.0;
        for(Abbonamento a:temp){
            stonks+=a.getCostoBaseMensile();
        }
        return stonks;
    }
}
