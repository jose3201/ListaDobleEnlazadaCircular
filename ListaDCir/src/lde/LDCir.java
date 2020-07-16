/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lde;

import static java.lang.Thread.sleep;

/**
 *
 * @author josed
 */
public class LDCir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        ListaDC n= new ListaDC();
        n.InsertarUltimo(15);
        n.InsertarUltimo(20);
        n.InsertarUltimo(30);
        n.InsertarUltimo(8);
        n.InsertarUltimo(19);
        n.InsertarUltimo(31);
        n.InsertarUltimo(32);
        n.imprimir();
        n.GenerarGraphyz();
        n.Eliminar(30);
        n.imprimir();
         sleep(1000);
        n.GenerarGraphyz();
    }
    
}
