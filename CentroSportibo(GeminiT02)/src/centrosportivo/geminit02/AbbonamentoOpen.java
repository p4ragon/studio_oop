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
public class AbbonamentoOpen extends Abbonamento{
    boolean accessoSpa;
    public AbbonamentoOpen(String codice, String nomeCliente, double costoBaseMensile,boolean abbonamentoSpa){
        super(codice,nomeCliente,costoBaseMensile);
        this.accessoSpa=accessoSpa;
    }
    public void setAccessoSpa(boolean accessoSpa){this.accessoSpa=accessoSpa;}
    public boolean getAccessoSpa(){return accessoSpa;}
    @Override
    public double calcolaQuotaMensile(){
        return accessoSpa?15.00+this.getCostoBaseMensile():this.getCostoBaseMensile();
    }
}
