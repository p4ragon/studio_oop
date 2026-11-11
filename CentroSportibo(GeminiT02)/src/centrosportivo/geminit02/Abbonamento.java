/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrosportivo.geminit02;

/**
 *
 * @author Vincenzo
 */
public abstract class Abbonamento implements Comparable<Abbonamento>{
    private final String codice;
    private final String nomeCliente;
    private double costoBaseMensile;
    private boolean attivo=true;
    public Abbonamento(String codice, String nomeCliente, double costoBaseMensile){
        this.codice=codice;
        this.nomeCliente=nomeCliente;
        this.costoBaseMensile=costoBaseMensile;
    }
    public String getCodice(){
        return codice;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }
    public double getCostoBaseMensile(){return costoBaseMensile;}
    public boolean isActive(){return attivo;}
    
    public void setCostoBaseMensile(double costoBaseMensile){this.costoBaseMensile=costoBaseMensile;}
    public void setActive(boolean status){this.attivo=status;}
    
    public abstract double calcolaQuotaMensile();
    @Override
    public final boolean equals(Object o){
        if(o==null) return false;
        if(this==o) return true;
        if(this.getClass()!=o.getClass()) return false;
        Abbonamento ab=(Abbonamento)o;
        return ab.codice.equalsIgnoreCase(this.codice);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Codice: "+codice+"\n");
        sb.append("Nome cliente: "+nomeCliente+"\n");
        sb.append("Costo base mensile: "+costoBaseMensile+"\n");
        sb.append("Attivo: "+attivo+"\n");
        return sb.toString();
    }
    @Override
    public int compareTo(Abbonamento o){
        int x=Double.compare(this.costoBaseMensile,o.costoBaseMensile)!=0?Double.compare(this.costoBaseMensile,o.costoBaseMensile)
                :this.nomeCliente.compareTo(o.nomeCliente);
        return x;
    }

    
    
    
}
