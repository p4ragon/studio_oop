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
public class AbbonamentoCorsi extends Abbonamento{
    private int numeroCorsiSettimanali;
    private double costoSingoloCorso;
    public AbbonamentoCorsi(String codice, String nomeCliente, double costoBaseMensile,int numeroCorsiSettimanali,double costoSingoloCorso){
        super(codice,nomeCliente,costoBaseMensile);
        this.numeroCorsiSettimanali=numeroCorsiSettimanali;
        this.costoSingoloCorso=costoSingoloCorso;
    }
    public int getNumeroCorsiSettimanali(){
        return numeroCorsiSettimanali;
    }
    public double getCostoSingoloCorso(){
        return costoSingoloCorso;
    }
    public void setNumeroCorsiSettimanali(int numeroCorsiSettimanali){
        this.numeroCorsiSettimanali=numeroCorsiSettimanali;
    }
    public void setCostoSingoloCorso(int costoSingoloCorso){
        this.costoSingoloCorso=costoSingoloCorso;
    }
    @Override
    public double calcolaQuotaMensile(){
    return numeroCorsiSettimanali>4?0.90*(this.getCostoBaseMensile()+(4*costoSingoloCorso*numeroCorsiSettimanali)):
    (this.getCostoBaseMensile()+(4*costoSingoloCorso*numeroCorsiSettimanali))
    ;
    }
}
