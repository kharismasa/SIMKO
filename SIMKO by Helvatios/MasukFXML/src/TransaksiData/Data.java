/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TransaksiData;

/**
 *
 * @author Encapsulation,ariable & Modifier - 21523244
 */
public class Data {
// Variable & Modifier - 21523244    
    String N;
    String T;
    String J;
    String K;
    int Nom; 
    
    public Data( String N, int Nom, String T,String K, String J){
        this.T = T;
        this.N = N;
        this.Nom = Nom;
        this.K = K;
        this.J = J;
    }
// construktor
    public Data() {
    }
// Encapsulation -21523244
    public String getN() {
        return N;
    }

    public void setN(String N) {
        this.N = N;
    }

    public String getT() {
        return T;
    }

    public void setT(String T) {
        this.T = T;
    }

    public String getK() {
        return K;
    }

    public void setK(String K) {
        this.K = K;
    }

    public int getNom() {
        return Nom;
    }

    public void setNom(int Nom) {
        this.Nom = Nom;
    }

    public String getJ() {
        return J;
    }

    public void setJ(String J) {
        this.J = J;
    }


   
    
}
