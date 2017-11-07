/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circle;

/**
 *
 * @author Alvaro
 */
public class Montecarlo {
    private int n=0;
    private int x=0;

    public int getN() {
        return n;
    }

    public int getX() {
        return x;
    }
    
    
    private double abs(double x){
        if(x<0) return -x;
        else return x;
    }
    
    public double expected_PI(){
        return 4.0*((double) x/ (double) n);
    }
    
    public void addN(){
        n++;
    }
    
    public void addX(){
        x++;
    }
    
    public boolean below_Threshold(double threshold){
        double expected=expected_PI();
        if(abs(expected-Math.PI)<=threshold) return true;
        else return false;
    }
}
