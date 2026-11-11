/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safedrive.geminit03;

/**
 *
 * @author Vincenzo
 */
public class PolizzaNonValidaException extends Exception{

    /**
     * Creates a new instance of <code>PolizzaNonValidaException</code> without
     * detail message.
     */
    public PolizzaNonValidaException() {
    }

    /**
     * Constructs an instance of <code>PolizzaNonValidaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PolizzaNonValidaException(String msg) {
        super(msg);
    }
}
