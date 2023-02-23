
package basicpainter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class lineaB extends JPanel {
    
    public void drawLineDDA(Point p1,Point p2) {
        double xInicio=p1.getX();
        double yInicio=p1.getY();
        double xfin=p2.getX();
        double yfin=p2.getY();
        Shape figurita;
        double dx=xfin-xInicio;
        double dy=yfin-yInicio;
        int steps;
        float xIncrement;
        float yIncrement;
        double x=xInicio;
        double y=yInicio;
        
        if (abs (dx)>abs(dy)){
            steps= (int) abs(dx);
        }else{
            steps=(int) abs(dy);
        }
        xIncrement=(float) (dx/(float)steps);
        yIncrement=(float) (dy/(float)steps);
        
        figurita= new Rectangle2D.Double (x,y,1,1);
        repaint();
        for(int k=0;k<steps;k++){
            x+=xIncrement;
            y+=yIncrement;
            figurita= new Rectangle2D.Double (x,y,1,1);
            repaint();          
        }
        //return figurita;
    }
   /* public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }*/
    
  /*  public Shape drawLineBresenham(Point p1,Point p2) {
        double xInicio=p1.getX();
        double yInicio=p1.getY();
        double xfin=p2.getX();
        double yfin=p2.getY();
        
        double dx=xfin-xInicio;
        double dy=yfin-yInicio;
        double m;
        
        if(abs(dy)==abs(dx)){
            m=dy/dx;            
            if(x1>x2){
                x=x2;
                y=y2;
                xend=x1;
            }else{
                x=x1;
                y=y1;
                xend=x2;
            }
            if(m==1){
                y=h-y; //ajuste para las coordenadas
                canvas.setRGB(x,y,color);

                while(x<xend){
                    x++;
                    y--;
                    canvas.setRGB(x,y,color); 
                }

            }else{
                y=h-y; //ajuste para las coordenadas
                canvas.setRGB(x,y,color);

                while(x<xend){
                    x++;
                    y++;
                    canvas.setRGB(x,y,color); 
                }

            }  
        } else if(dy==0){
            if(x1>x2){
                x=x2;
                y=y2;
                xend=x1;
            }else{
                x=x1;
                y=y1;
                xend=x2;
            }
            y=h-y; //ajuste para las coordenadas
            canvas.setRGB(x,y,color);

            while(x<xend){
                x++;
                canvas.setRGB(x,y,color); 
            }

        }else if(dx==0){
            if(y1>y2){
                x=x2;
                y=y2;
                yend=y1;
            }else{
                x=x1;
                y=y1;
                yend=y2;
            }
            y=h-y; //ajuste para las coordenadas
            yend=h-yend; //ajuste para las coordenadas
            canvas.setRGB(x,y,color);

            while(y>yend){
                y--;
                canvas.setRGB(x,y,color); 
            }

        }else{            
            m=dy/dx;

            if(x1>x2){
                x=x2;
                y=y2;
                xend=x1;
                yend=y1;
            }else{
                x=x1;
                y=y1;
                xend=x2;
                yend=y2;
            }
            dy=abs(dy);
            dx=abs(dx);
            if(m<1&&m>0){                
                y=h-y;//ajuste de coordenadas
                float p=2*dy-dx;
                float twoDy=2*dy;
                float twoDyDx=2*(dy-dx);                
                canvas.setRGB(x,y,color);

                while (x<xend){
                    x++;                    
                    if(p<0){
                        p+=twoDy;
                    }else{
                        y--; 
                        p+=twoDyDx;
                    }
                    canvas.setRGB(x,y,color);
                }

                repaint();
            }else if (m<0&&m>-1){
                y=h-y;//ajuste de coordenadas
                float p=2*dy-dx;
                float twoDy=2*dy;
                float twoDyDx=2*(dy-dx);                
                canvas.setRGB(x,y,color);

                while (x<xend){
                    x++;                    
                    if(p<0){
                        p+=twoDy;
                    }else{
                        y++; 
                        p+=twoDyDx;
                    }
                    canvas.setRGB(x,y,color);
                }

                repaint();            
            }else if(m>1){
                y=h-y;//ajuste de coordenadas
                yend=h-yend;//ajuste de coordenadas                
                float p=2*dx-dy;
                float twoDx=2*dx;
                float twoDxDy=2*(dx-dy);                
                canvas.setRGB(x,y,color);

                while (y>yend){
                    y--;                    
                    if(p<0){
                        p+=twoDx;
                    }else{
                        x++; 
                        p+=twoDxDy;
                    }
                    canvas.setRGB(x,y,color);              
                    }

                repaint();  
            }else{
                y=h-y;//ajuste de coordenadas
                yend=h-yend;//ajuste de coordenadas 
                float p=2*dx-dy;
                float twoDx=2*dx;
                float twoDxDy=2*(dx-dy);                
                canvas.setRGB(x,y,color);

                while (y<yend){
                    y++;                    
                    if(p<0){
                        p+=twoDx;
                    }else{
                        x++; 
                        p+=twoDxDy;
                    }
                    canvas.setRGB(x,y,color);              
                }                  
            }
        }
    }*/
    
}
