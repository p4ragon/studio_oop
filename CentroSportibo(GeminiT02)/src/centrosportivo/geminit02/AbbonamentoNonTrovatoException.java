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
public class AbbonamentoNonTrovatoException extends Exception{

    /**
     * Creates a new instance of <code>AbbonamentoNonTrovatoException</code>
     * without detail message.
     */
    public AbbonamentoNonTrovatoException() {
    }

    /**
     * Constructs an instance of <code>AbbonamentoNonTrovatoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public AbbonamentoNonTrovatoException(String msg) {
        super(msg);
    }
}
