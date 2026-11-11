package noleggio.geminit01;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vincenzo
 */
public abstract class Veicolo implements Comparable<Veicolo>{
    private final String targa;
    private final String marca;
    private final String modello;
    private double tariffaBaseGiornaliera;
    private boolean noleggiato;
    public Veicolo(String targa,String marca,String modello,double tariffaBaseGiornaliera,boolean noleggiato){
        this.targa=targa;
        this.marca=marca;
        this.modello=modello;
        this.tariffaBaseGiornaliera=tariffaBaseGiornaliera;
        this.noleggiato=noleggiato;
    }
    public Veicolo(String targa,String marca,String modello,double tariffaBaseGiornaliera){
        this.targa=targa;
        this.marca=marca;
        this.modello=modello;
        this.tariffaBaseGiornaliera=tariffaBaseGiornaliera;
        this.noleggiato=false;
    }
    public String getMarca(){return marca;}
    public String getTarga(){return targa;}
    public String getModello(){return modello;}
    public double getTariffaBaseGiornaliera(){return tariffaBaseGiornaliera;}
    public boolean isRented(){return noleggiato;}
    //setter non disponibili per targa,marca e modello
    public void setTariffaBaseGiornaliera(double tariffaBaseGiornaliera){this.tariffaBaseGiornaliera=tariffaBaseGiornaliera;}
    public void setNoleggiato(boolean noleggiato){
        this.noleggiato=noleggiato;
    }
    public void rent() throws VeicoloNonDisponibileException{
        if(noleggiato) throw new VeicoloNonDisponibileException("Veicolo non disponibile.");
        noleggiato=true;
    }
   
    public abstract double calcolaCostoNoleggio(int giorni);
    @Override
    public final boolean equals(Object o){
        if(o==null) return false;
        if(this==o) return true;
        if(this.getClass()!=o.getClass())return false;
        Veicolo v=(Veicolo)o;
        return v.targa.equals(this.targa);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Targa: "+targa+"\n");
        sb.append("Marca: "+marca+"\n");
        sb.append("Modello: "+modello+"\n");
        sb.append("Tariffa base giornaliera: "+tariffaBaseGiornaliera+"\n");
        sb.append("Noleggiato: "+noleggiato+"\n");
        return sb.toString();
    }
    @Override
    public int compareTo(Veicolo v){
        if(v==null) throw new NullPointerException();
        /*
        NOTA: tariffa è double su cui non è definita la compareTo
        
        return this.tariffaBaseGiornaliera.compareTo(v.tariffaBaseGiornaliera);
        */
        return Double.compare(this.tariffaBaseGiornaliera, v.tariffaBaseGiornaliera);
}
}
