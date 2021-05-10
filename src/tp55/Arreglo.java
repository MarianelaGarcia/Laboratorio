/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp55;

/**
 *
 * @author Usuario
 */
public class Arreglo {
    static int aux;

    public static void sumarLista (int a []){
        for (int i : a) {
           aux += i;
        }
        System.out.println(aux);
        aux=0;
    } 

    public static void numMayor (int b [][]){
        for (int[] b1 : b) {
            for (int y = 0; y < b1.length; y++) {
                if (b1[y] >= aux) {
                    aux = b1[y];
                }
            }
        }            
        System.out.println(aux);
        aux=0;
    }

    public static void cuentaVocal (String c){
        for (int i = 0 ; i < c.length() ; i++){
            if((c.charAt(i)=='a') || (c.charAt(i)=='e') || (c.charAt(i)=='i') || (c.charAt(i)=='o') || (c.charAt(i)=='u')|| (c.charAt(i)=='A') || (c.charAt(i)=='E') || (c.charAt(i)=='I') || (c.charAt(i)=='O') || (c.charAt(i)=='U')){
                aux += 1;
            }
        }
        System.out.println(aux);
        aux=0;
    }

    public static void cuentaCaracter (String d, char e){
        String au = String.valueOf(e);
        for (int i = 0 ; i < d.length() ; i++){   
            if (d.charAt(i) == Character.toUpperCase(e) || d.charAt(i) == Character.toLowerCase(e)){
                aux += 1;
            }            
        }
        System.out.println(aux);
        
    }

    

}

