/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lde;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author josed
 */
public class ListaDC {

    private Nodo primero;
    private Nodo ultimo;
    private int tama;

    public ListaDC() {
        this.primero = null;
        this.tama = 0;
    }

    public void InsertarUltimo(int d) {
        Nodo nuevo = new Nodo(d);
        if (primero == null) {
            primero = nuevo;
            primero.setSiguiente(primero);
            primero.setAnterior(primero);
            tama++;
        } else if (tama == 1) {
            ultimo = nuevo;
            primero.setSiguiente(ultimo);
            primero.setAnterior(ultimo);
            ultimo.setAnterior(primero);
            ultimo.setSiguiente(primero);
            tama++;
        } else {
            Nodo aux = ultimo;
            ultimo = nuevo;
            aux.setSiguiente(ultimo);
            ultimo.setAnterior(aux);
            primero.setAnterior(ultimo);
            ultimo.setSiguiente(primero);
            tama++;
        }
    }

    private Nodo Buscar(int d) {
        Nodo aux = primero;
        if (primero != null) {
            while (aux!=null) {                
                if (aux.getDato()==d) {
                    return aux;
                }
                aux=aux.getSiguiente();
            }
        } else {
            System.out.println("Lista vacia");
            return null;
        }
    return null;
    }
    public void Eliminar(int d){
        if (primero!=null) {
            Nodo aux=Buscar(d);
            if (aux!=null) {
                if (aux.getDato()==primero.getDato()) {
                    primero=primero.getSiguiente();
                    primero.setAnterior(ultimo);
                    ultimo.setSiguiente(primero);
                    tama--;
                } else if (aux.getDato()==ultimo.getDato()) {
                    ultimo=aux.getAnterior();
                    ultimo.setSiguiente(primero);
                    aux.setAnterior(null);
                    aux.setSiguiente(null);
                    primero.setAnterior(ultimo);
                    tama--;
                }else{
                    Nodo auxant=aux.getAnterior(), auxsig=aux.getSiguiente();
                    aux.setAnterior(null);
                    aux.setSiguiente(null);
                    auxant.setSiguiente(auxsig);
                    auxsig.setAnterior(auxant);
                    tama--;
                }
            } else {
                System.out.println("dato no existe");
            }
        } else {
            System.out.println("Lista vacia");        
        }
    }
    public void  imprimir(){
        if (primero!=null) {
        Nodo aux=primero;
        int contador=0;
            while (contador<tama) {                
                System.out.print(" Dato:"+aux.getDato()+"->");
                aux=aux.getSiguiente();
                contador++;
            }
            System.out.println("");
            System.out.println("Tamaño: "+tama);
        }else{
            System.out.println("Lista vacia");
        }
    }
       public void GenerarGraphyz(){
        try {
            //escribir dot
            FileWriter codigo = new FileWriter("listadoble" + ".dot");
            PrintWriter documento = new PrintWriter(codigo);
            documento.println("digraph G {\n");
            documento.println("node[shape=box];\n");
            documento.println("\t\t//generar lista simple\n");
            documento.println(Gcodigo());

            documento.println("}");
            codigo.close();

            //compilar dot y generar imagen
            File miDir = new File(".");
            String ruta = miDir.getCanonicalPath() + "\\";//ruta actual
            String salida = "dot -Tpng " + ruta + "listadoble" + ".dot -o " + ruta + "listadoble" + ".png";
            Runtime rt = Runtime.getRuntime();
            rt.exec(salida);
            //abrir imagen
            miDir = new File(ruta +"listadoble" + ".png");
            Desktop.getDesktop().open(miDir);

        } catch (IOException e) {
            System.out.println(e);
        }

    }
    private String Gcodigo(){
     String codigo = "";
        if (primero != null) {
            Nodo aux = primero;
            String rank1 = "";

            //==============================================crecacion de nodos  nodos=================================
            int contador1 = 0;
            while (contador1<tama) {
                codigo = codigo + "RR" + contador1 + "[label=\"Dato: " + aux.getDato()+"\",color=\"burlywood\"];\n";
                aux = aux.getSiguiente();
                contador1++;
            }
            //=============================================================== conexcion de nodos==========================================
            int retro=0;
            contador1 = 0;
            while (contador1 < tama) {
                if (contador1==0) {
                    rank1 = rank1 + "RR" + contador1;
                } else {
                    rank1 = rank1 + "->" + "RR" + contador1;
                }
                retro=contador1;
                contador1++;
            }
            codigo = codigo + "{rank=same; " + rank1 +"->RR0;};\n";
            rank1="";
            while (retro>=0) {                
                if (retro==0) {
                    rank1=rank1+"RR"+retro;
                } else {
                    rank1=rank1+"RR"+retro+"->";
                }
                retro--;
            }
            rank1=rank1+"->RR"+(contador1-1);
            codigo=codigo+rank1;
            return codigo;
        } else {
            return codigo = "RR[label = \"Lista vacia\"   width = 1.5 style = filled,shape=box,style=filled,color=\"red\"]; \n";
        }
    }
}
