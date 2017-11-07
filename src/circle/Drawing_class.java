/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Alvaro
 */
public class Drawing_class {
    private Graphics g;
    private int width=400;
    private int margin=25;
    private Montecarlo montecarlo=new Montecarlo();

    public Drawing_class(Graphics g) {
        this.g = g;
    }
    
    private double calculate_Pixel_X(double x){
        x*=(width/2.0)-margin;
        x+=(width/2.0);
        return x;
    }
    private double calculate_Pixel_Y(double y){
        y=1-y;
        y*=(width/2.0)-margin;
        y+=margin;
        return y;
    }
    
    private void draw_Point(double x, double y){
        g.drawLine((int) calculate_Pixel_X(x), (int) calculate_Pixel_Y(y), (int) calculate_Pixel_X(x), (int) calculate_Pixel_Y(y));
    }
    
    private double semicircle(double x){
        return Math.sqrt(1-Math.pow(x, 2));
    }
    
    private void choose_Color(double x, double y){
        montecarlo.addN();
        if(y<=semicircle(x)){
            g.setColor(Color.CYAN);
            montecarlo.addX();
        }
        else g.setColor(Color.yellow);
    }
    
    public String paint(int n, double threshold){
        g.drawLine(200, 0, 200, 400);
        g.drawLine(0, 200, 400, 200);
        g.setColor(Color.RED);
        g.drawLine(0, 25, 0, 25);
        g.drawOval(25, 25, 350, 350);
        g.setColor(Color.BLACK);
        g.drawLine(200, 25, 375, 25);
        g.drawLine(375, 25, 375, 200);
        
        Random r=new Random();
        if(n!=0){
            for(int i=0; i<n; i++){
                double x=r.nextDouble();
                double y=r.nextDouble();
                System.out.println("P(x, y)=("+x+", "+y+")");
                choose_Color(x, y);
                draw_Point(x, y);
            }
        }else{
            do{
                double x=r.nextDouble();
                double y=r.nextDouble();
                System.out.println("P(x, y)=("+x+", "+y+")");
                choose_Color(x, y);
                draw_Point(x, y);
            }while(!montecarlo.below_Threshold(threshold));
        }
        return "<html>Puntos generados: "+montecarlo.getN()+"<br> Puntos bajo la circunferencia: "+montecarlo.getX()+"<br> Pi esperado: "+montecarlo.expected_PI()+"</html>";
        }
}
