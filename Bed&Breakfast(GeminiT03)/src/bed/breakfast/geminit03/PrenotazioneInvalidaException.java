/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bed.breakfast.geminit03;

/**
 *
 * @author Vincenzo
 */
public class PrenotazioneInvalidaException extends Exception{

    /**
     * Creates a new instance of <code>PrenotazioneInvalidaException</code>
     * without detail message.
     */
    public PrenotazioneInvalidaException() {
    }

    /**
     * Constructs an instance of <code>PrenotazioneInvalidaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PrenotazioneInvalidaException(String msg) {
        super(msg);
    }
}
