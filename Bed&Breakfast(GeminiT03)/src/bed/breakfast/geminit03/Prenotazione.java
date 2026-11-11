/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bed.breakfast.geminit03;

import java.time.LocalDate;

/**
 *
 * @author Vincenzo
 */
public abstract class Prenotazione implements Comparable<Prenotazione> {
    private final String codice;
    private final String nomeOspite;
    private LocalDate dataInizio;
    private int numeroNotti;
    private double prezzoPerNotte;
    public Prenotazione(String codice, String nomeOspite, LocalDate dataInizio, int numeroNotti, double prezzoPerNotte){
        this.codice=codice;
        this.nomeOspite=nomeOspite;
        this.dataInizio=dataInizio;
        this.numeroNotti=numeroNotti;
        this.prezzoPerNotte=prezzoPerNotte;
    }
    public String getCodice(){return codice;}
    public String getNomeOspite(){return nomeOspite;}
    public LocalDate getDataInizio(){return dataInizio;}
    public int getNumeroNotti(){return numeroNotti;}
    public double getPrezzoPerNotte(){return prezzoPerNotte;}
    public LocalDate getDataFine(){return dataInizio.plusDays(numeroNotti);}
    
   public boolean isInCorso(LocalDate dataRiferimento){
       if(dataRiferimento.isAfter(dataInizio)&&dataRiferimento.isBefore(this.getDataFine())) return true;
       return false;
   }
   public abstract double calcolaCostoTotale();
   
   @Override
   public int compareTo(Prenotazione p){
       int x=this.dataInizio.compareTo(p.dataInizio);
       if(x!=0) return x;
       //per comparare due int ho bisogno dei tipi wrapper
       // obj.compareTo(Object o)->non posso metterci dei primitivi -> uso l'autoboxing all'interno delle funzioni dei wrapper
       return Integer.compare(p.numeroNotti, this.numeroNotti)!=0?Integer.compare(p.numeroNotti, this.numeroNotti):this.codice.compareToIgnoreCase(p.codice);
   }
   @Override
   public boolean equals(Object o){
       if(o==null) return false;
       if(this==o) return true;
       if(this.getClass()!=o.getClass()) return false;
       Prenotazione p=(Prenotazione)o;
       return p.codice.equalsIgnoreCase(this.codice);
   }
   @Override
   public String toString(){
       StringBuilder sb=new StringBuilder();
       sb.append("Codice: "+codice+"\n");
       sb.append("Nome ospite:: "+nomeOspite+"\n");
       sb.append("Data Inizio: "+dataInizio+"\n");
       sb.append("Numero notti: "+numeroNotti+"\n");
       sb.append("Prezzo per notte: "+prezzoPerNotte+"\n");
       return sb.toString();
   }
    
}
