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
public abstract class Polizza implements Comparable<Polizza> {
    private final String numeroPolizza;
    private final String intestatario;
    private LocalDate dataScadenza;
    private double massimale;
    public Polizza(String numeroPolizza,String intestatario, LocalDate dataScadenza, double massimale){
        this.numeroPolizza=numeroPolizza;
        this.intestatario=intestatario;
        this.dataScadenza=dataScadenza;
        this.massimale=massimale;
    }
    public String getNumeroPolizza(){return numeroPolizza;}
    public String getIntestatario(){return intestatario;}
    public LocalDate getDataScadenza(){return dataScadenza;}
    public double getMassimale(){return massimale;}
    //
    public void setDataScadenza(LocalDate dataScadenza){this.dataScadenza=dataScadenza;}
    public void setMassimale(double massimale){this.massimale=massimale;}
    
    public boolean isScaduta(LocalDate dataControllo){
        return dataScadenza.isBefore(dataControllo);
    }
    
    public abstract double calcolaPremioAnnuale();
    
    @Override
    public int compareTo(Polizza altra){
       return this.dataScadenza.compareTo(altra.dataScadenza)!=0?this.dataScadenza.compareTo(altra.dataScadenza):this.numeroPolizza.compareTo(altra.numeroPolizza);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Numero polizza: "+numeroPolizza+"\n")
                .append("Intestatario: "+intestatario+"\n")
                .append("Data di scadenza: "+dataScadenza+"\n")
                .append("Massimale: "+massimale+"\n");
        return sb.toString();
    }
    
}
