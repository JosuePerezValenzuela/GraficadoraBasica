
package basicpainter;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Label;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BasicPainter extends JFrame implements ActionListener {
    JMenu archivo,tipolinea,grosor,ayuda;
    JMenuItem salir,nuevo,guardar,abrir;
    JRadioButtonMenuItem normal,medio,grueso,segmentada,continua;
    JColorChooser colorChooser=new JColorChooser();
    JSpinner escala,angulo;
    ButtonGroup btn,btn2;
    JButton rellenar,triangulo,cuadrado,circulo,color,agrupar,trasladar,rotacion;
    MiPanel miPanel;
    Color coloractual;
    Graphics2D g2D;
    Label mostrarColor,nombreEscala,nombreAngulo;
    Boolean fill=false;
    int seed=0;

    public BasicPainter(){
        crearmenu();
        addlisteners();
        miPanel=new MiPanel();        
        this.add (miPanel);
        this.setSize(1100, 800);
        miPanel.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Graficadora");
    }
    private void addlisteners(){
        
        salir.addActionListener(this);
        nuevo.addActionListener(this);
        guardar.addActionListener(this);
        abrir.addActionListener(this);
        color.addActionListener(this);
        triangulo.addActionListener(this);
        cuadrado.addActionListener(this);
        circulo.addActionListener(this);
        grosor.addActionListener(this);
        normal.addActionListener(this);
        medio.addActionListener(this);
        grueso.addActionListener(this);
        rellenar.addActionListener(this);
        tipolinea.addActionListener(this);
        segmentada.addActionListener(this);
        continua.addActionListener(this);
        agrupar.addActionListener(this);
        trasladar.addActionListener(this);
        rotacion.addActionListener(this);
    }
    public void crearmenu(){
        JMenuBar menu=new JMenuBar();
        mostrarColor= new Label();
        nombreEscala= new Label("Escala:");
        nombreAngulo= new Label("Angulo (rad):");
        archivo=new JMenu("Archivo");
        nuevo = new JMenuItem ("Nuevo");
        abrir= new JMenuItem ("Abrir");
        guardar= new JMenuItem ("Guardar");
        salir= new JMenuItem ("Salir");
        triangulo=new JButton("Triangulo");        
        cuadrado=new JButton("Cuadrado");
        circulo=new JButton("Circulo");
        rellenar=new JButton("Rellenar");
        color=new JButton("Color");
        agrupar=new JButton("Agrupar");
        trasladar=new JButton("Trasladar");
        rotacion=new JButton("Rotacion");
        mostrarColor.setSize(50, 50);
        escala=new JSpinner(new SpinnerNumberModel(1,0.2,5,0.2));        
        angulo=new JSpinner(new SpinnerNumberModel(0,-6.2,6.2,0.1));
        mostrarColor.setBackground(Color.BLACK);
        cuadrado.setBackground(Color.white);
        circulo.setBackground(Color.white);
        rellenar.setBackground(Color.white);
        color.setBackground(Color.white);
        triangulo.setBackground(Color.LIGHT_GRAY);
        archivo.add (nuevo);
        archivo.add (abrir);
        archivo.add (guardar);
        archivo.add (salir);
        menu.add (archivo);
        menu.add(triangulo);
        menu.add (cuadrado);
        menu.add(circulo);
        menu.add(agrupar);
        menu.add(rellenar);
        menu.add(color);
        menu.add(mostrarColor);
        menu.add(nombreEscala);
        menu.add(escala);
        menu.add(rotacion);
         menu.add(nombreAngulo);
        menu.add(angulo);
        menu.add(trasladar);
        grosor= new JMenu("Tamaño");
        btn =new ButtonGroup();
        normal= new JRadioButtonMenuItem("Normal");
        medio = new JRadioButtonMenuItem("Medio");
        grueso= new JRadioButtonMenuItem("Grueso");
        btn.add(normal);
        btn.add(medio);        
        btn.add(grueso);
        btn.setSelected(normal.getModel(),true);
        grosor.add (normal);
        grosor.add (medio);
        grosor.add (grueso);
        menu.add(grosor);
        tipolinea= new JMenu("Trazado");
        btn2 =new ButtonGroup();
        continua= new JRadioButtonMenuItem("Continua");
        segmentada = new JRadioButtonMenuItem("Segmentada");
        btn2.add(continua);        
        btn2.add(segmentada);
        btn2.setSelected(continua.getModel(),true);
        tipolinea.add (continua);
        tipolinea.add (segmentada);
        menu.add(tipolinea);        
        this.setJMenuBar(menu);
    }
    public static void main (String[] args){
        BasicPainter miVentana = new BasicPainter();
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource()==nuevo){
            miPanel.resetAll();        
        }
        if (e.getSource()==rellenar){            
            fill=true;
            miPanel.linea=true;
            miPanel.cuadrado=false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            rellenar.setBackground(Color.LIGHT_GRAY);
            triangulo.setBackground(Color.white);
            cuadrado.setBackground(Color.white);
            circulo.setBackground(Color.white);
        }
        if (e.getSource()==abrir){
            miPanel.abrir();        
        }
        if (e.getSource()==guardar){
            miPanel.guardar();        
        }
        if (e.getSource()==salir){
            System.exit(0);
        }
        if (e.getSource()==triangulo){
            miPanel.linea=true;
            miPanel.cuadrado=false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;
            miPanel.boolCircunferencia=false;            
            fill=false;
            rellenar.setBackground(Color.white);
            triangulo.setBackground(Color.LIGHT_GRAY);
            cuadrado.setBackground(Color.white);
            circulo.setBackground(Color.white);
        }
        if (e.getSource()==cuadrado){
            miPanel.linea=false;
            miPanel.cuadrado=true;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;
            miPanel.boolCircunferencia=false;
            fill=false;
            rellenar.setBackground(Color.white);
            triangulo.setBackground(Color.white);
            cuadrado.setBackground(Color.LIGHT_GRAY);
            circulo.setBackground(Color.white);
        }
        if (e.getSource()==circulo){
            miPanel.linea=false;
            miPanel.cuadrado=false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;
            miPanel.boolCircunferencia=true;
            fill=false;
            rellenar.setBackground(Color.white);
            triangulo.setBackground(Color.white);
            cuadrado.setBackground(Color.white);
            circulo.setBackground(Color.LIGHT_GRAY);
        }
        if (e.getSource()==color){
            Color color =JColorChooser.showDialog(this,"Elija Color",this.miPanel.getColorActual());
            this.miPanel.setColorActual(color);
            mostrarColor.setBackground(color);
        }
        if (e.getSource()==normal){
            miPanel.boolNormal=true;
            miPanel.boolMedio =false;
            miPanel.boolGrueso =false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false; 
            fill=false;            
        }
        if (e.getSource()==medio){
            miPanel.boolNormal=false;
            miPanel.boolMedio =true;
            miPanel.boolGrueso =false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false; 
            fill=false;            
        }        
        if (e.getSource()==grueso){
            miPanel.boolNormal=false;
            miPanel.boolMedio =false;
            miPanel.boolGrueso =true;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false; 
            fill=false;            
        }
        if (e.getSource()==continua){
            miPanel.boolContinua =true;
            miPanel.boolSegmentda =false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;
            fill=false;            
        }        
        if (e.getSource()==segmentada){
            miPanel.boolContinua =false;
            miPanel.boolSegmentda =true;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;             
            fill=false;             
        }
        if (e.getSource()==agrupar){
            miPanel.boolAgrupar =true;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =false;
            miPanel.linea=false;
            miPanel.cuadrado=false;
            miPanel.boolCircunferencia=false;
            fill=false;            
        }        
        if (e.getSource()==trasladar){
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =true;
            miPanel.boolRotacion =false;
            miPanel.linea=true;
            miPanel.cuadrado=false;
            miPanel.boolCircunferencia=false;
            fill=false;             
        }
        if (e.getSource()==rotacion){
            miPanel.linea=true;
            miPanel.cuadrado=false;
            miPanel.boolCircunferencia=false;
            miPanel.boolAgrupar =false;
            miPanel.boolTrasladar =false;
            miPanel.boolRotacion =true;
            fill=false;             
        }
    }
    class MiPanel extends JPanel{
        lineaB alinea=new lineaB();
        Point p1;
        Point p2;
        int xSuperior=0;
        int ySuperior=0;        
        Shape figura;
        Random R=new Random ();
        public Color coloractual= Color.black;
        BufferedImage myImage;
        Graphics2D g2D;
        boolean cuadrado=false;
        boolean linea =true;
        boolean relleno =false;
        boolean boolCircunferencia=false;
        boolean boolNormal=true;
        boolean boolMedio =false;
        boolean boolGrueso =false;
        boolean boolContinua =true;
        boolean boolSegmentda =false;
        boolean boolAgrupar =false;
        boolean boolTrasladar =false;
        boolean boolRotacion =false;
        int [][] colores;
        public MiPanel(){
            OyenteDeRaton miOyente = new OyenteDeRaton();
            addMouseListener(miOyente);
        }
        public Color getColorActual(){
            return coloractual;
        }
        public void setColorActual (Color color){
            coloractual=color;
        }
        public Graphics2D crearGraphics2D (){
            Graphics2D g2 =null;

            if (myImage == null||myImage.getWidth()!=getSize().width ||myImage.getHeight()!=getSize().height){
                myImage = (BufferedImage)createImage (getSize().width,getSize().height);
            }
            if (myImage!=null){
                g2=myImage.createGraphics();
                g2.setColor(coloractual);
                g2.setBackground(getBackground());
            }
            g2.clearRect (0,0,getSize().width,getSize().height);
            return g2;

        }
        public void paintComponent (Graphics g){
            super.paintComponent (g);
            if (myImage == null){
                g2D = crearGraphics2D();
            }
            if (figura!=null){
                if (relleno){
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                    g2D.fill(figura);
                }else{
                    g2D.setColor(coloractual);
                    g2D.draw(figura);                    
                }
                if (myImage!=null&& isShowing()){
                    g.drawImage(myImage,0,0,this);
                }
                figura=null;
            }        
        }
        public void resetAll(){
            myImage=null;
            repaint();
        }      
        
        public void lineaEngrosada(Point p1,Point p2,int a, boolean espacio) {
        double xInicio=p1.getX();
        double yInicio=p1.getY();
        double xfin=p2.getX();
        double yfin=p2.getY();
        double dx=xfin-xInicio;
        double dy=yfin-yInicio;
        int steps;
        float xIncrement;
        float yIncrement;
        double x=xInicio;
        double y=yInicio;
        boolean flag=true;
        int intervalo=0;
        
        if (abs (dx)>abs(dy)){
            steps= (int) abs(dx);
        }else{
            steps=(int) abs(dy);
        }
        xIncrement=(float) (dx/(float)steps);
        yIncrement=(float) (dy/(float)steps);
        
        figura= new Rectangle2D.Double (x,y,a,a);
        g2D.fill(figura);
        g2D.draw(figura);        
        repaint();
        for(int k=0;k<steps;k++){
            x+=xIncrement;
            y+=yIncrement;
            if(k%7==0&&espacio){
                intervalo++;
                if(intervalo%2==0){
                    flag=false;
                }else{
                    flag=true;
                }
            }
            if(flag){
                figura= new Rectangle2D.Double (x,y,a,a);
                g2D.draw(figura);
            }            
            repaint();          
        }
    }   
        public Point puntoEscalado (Point p1,Point p2,double scale){
            double xInicio=p1.getX();
            double yInicio=p1.getY();
            double xfin=p2.getX();
            double yfin=p2.getY();
            double xEscalado;
            double yEscalado;
            Point p3=new Point();
            xEscalado=xInicio+(xfin-xInicio)*scale;
            yEscalado=yInicio+(yfin-yInicio)*scale;
            p3.setLocation(xEscalado, yEscalado);
            return p3;
        }
        
        public Point puntoSuperiorIzquierdo (Point p1,Point p2){
            double xInicio=p1.getX();
            double yInicio=p1.getY();
            double xfin=p2.getX();
            double yfin=p2.getY();
            double xsi;
            double ysi;
            Point p3=new Point();
            if (xfin>=xInicio){
                xsi=xInicio;
            }else{
                xsi=xfin;
            }
            if (yfin>=yInicio){
                ysi=yInicio;
            }else{
                ysi=xfin;
            }
            p3.setLocation(xsi, ysi);
            return p3;
        }
        public void agrupar(Point p1,Point p2) {            
            Point p3=puntoSuperiorIzquierdo(p1,p2) ;
            int xsi=(int) p3.getX();
            int ysi=(int) p3.getY();
            xSuperior=xsi;
            ySuperior=ysi;
            int xInicio=(int) p1.getX();
            int yInicio=(int) p1.getY();
            int xfin=(int) p2.getX();
            int yfin=(int) p2.getY();
            int fila=abs(xfin-xInicio);
            int columna=abs(yfin-yInicio);
            int actual=0;
            colores = new int[abs(xfin-xInicio)][abs(yfin-yInicio)];
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {                
                    colores[i][j] = myImage.getRGB(xsi+i,ysi+j);
                }
            }            
        }
        public void trasladar (Point p1){
            int xsi=(int) p1.getX();
            int ysi=(int) p1.getY();
            for (int i = 0; i < colores.length; i++) { 
                for (int j = 0; j < colores[i].length; j++) {                    
                    myImage.setRGB(xsi+i,ysi+j,colores[i][j]);                    
                }
            }            
        }
        public void rotacion (Point p1){
            int xPivote=(int) p1.getX();
            int yPivote=(int) p1.getY();
            int xrotacion=0;
            int yrotacion=0;            
            for (int i = 0; i < colores.length; i++) { 
                for (int j = 0; j < colores[i].length; j++) {
                    xrotacion=(int) (xPivote+(xSuperior+i-xPivote)*Math.cos((double)angulo.getValue())-(ySuperior+j-yPivote)*Math.sin((double)angulo.getValue()));
                    yrotacion=(int) (yPivote+(xSuperior+i-xPivote)*Math.sin((double)angulo.getValue())+(ySuperior+j-yPivote)*Math.cos((double)angulo.getValue()));
                    myImage.setRGB(xrotacion,yrotacion,colores[i][j]);                    
                }
            }
        }
        public void cuadrado(Point p1,Point pAux2,int a,boolean espacio) {
            Point p3 = new Point();
            Point p4 = new Point();
            Point p2 = new Point();
            double xInicio=p1.getX();
            double yInicio=p1.getY();
            double xfin=pAux2.getX();
            p2.setLocation(xfin, yInicio + (xfin - xInicio));
            double xfin2 = p2.getX();
            double yfin2 = p2.getY();
            p3.setLocation(xfin2, yInicio);
            p4.setLocation(xInicio, yfin2);
                lineaEngrosada(p1,p4,a,espacio);
                lineaEngrosada(p1,p3,a,espacio);
                lineaEngrosada(p2,p3,a,espacio);
                lineaEngrosada(p2,p4,a,espacio);            
        }
        
        public void triangulo(Point p1,Point p2,int a,boolean espacio) {
            Point p3=new Point();            
            double xInicio=p1.getX();
            double yfin=p2.getY();
            p3.setLocation(xInicio, yfin);
                lineaEngrosada(p1,p2,a,espacio);
                lineaEngrosada(p1,p3,a,espacio);
                lineaEngrosada(p3,p2,a,espacio);    
                  
        }
        
        public void boundaryfill4(Point p1)
        {   int xInicio=(int)p1.getX();
            int yInicio=(int)p1.getY();
            int colorFill=coloractual.getRGB();
            int current=myImage.getRGB(xInicio,yInicio);
         if (current==seed){
                myImage.setRGB(xInicio,yInicio,colorFill);
                Point p11=new Point(xInicio+1,yInicio);
                Point p12=new Point(xInicio-1,yInicio);
                Point p13=new Point(xInicio,yInicio+1);
                Point p14=new Point(xInicio,yInicio-1);
                boundaryfill4(p11);
                boundaryfill4(p12);
                boundaryfill4(p13);
                boundaryfill4(p14);
            }
         repaint();
        }
        
        public void circunferenciaBresenham(Point p1,Point p2,int a,boolean espacio){
            ArrayList <Point> puntos = new ArrayList();
            double xCentro = p1.getX();
            double yCentro = p1.getY();
            double radio = p1.distance(p2);
            double pk = 3 - 2 * radio;
            double x1 = 0;
            double y1 = radio;
            while (x1 <= y1){
              if(pk < 0){
                x1 = x1 + 1;
                y1 = y1;
                pk = pk + 4 * x1 + 6;
              }else{
                x1 = x1 + 1;
                y1 = y1 - 1;
                pk = pk + 4 * (x1 - y1) + 10;
              }
             Point paux = new Point();
             paux.setLocation(x1, y1);
             puntos.add(paux);
            }
            puntos = Octantes(puntos);
            double xCentroVent = 550;
            double yCentroVent = 400;
            double xCal = xCentroVent + xCentro;
            double yCal = yCentroVent - yCentro;
            for(int i = 0; i< puntos.size(); i++){
               double x = puntos.get(i).getX() + xCal;
               double y = puntos.get(i).getY() + yCal;
               puntos.get(i).setLocation(x, y);
               lineaEngrosada(puntos.get(1), puntos.get(1), a, espacio);
            }
        }
        
        public ArrayList<Point> Octantes(ArrayList <Point> puntos){
            ArrayList<Point> puntosFinal = new ArrayList<>();
            for(int i = 0; i < puntos.size(); i++){
                puntosFinal.add(puntos.get(i));
                double x = puntos.get(i).getX();
                double y = puntos.get(i).getY();
                Point paux1 = new Point();
                paux1.setLocation(x, y);
                Point paux2 = new Point();
                paux2.setLocation(-x, y);
                Point paux3 = new Point();
                paux3.setLocation(-x, -y);
                Point paux4 = new Point();
                paux4.setLocation(x, -y);
                Point paux5 = new Point();
                paux5.setLocation(y, x);
                Point paux6 = new Point();
                paux6.setLocation(-y, x);
                Point paux7 = new Point();
                paux7.setLocation(-y, -x);
                Point paux8 = new Point();
                paux8.setLocation(y, -x);
                puntosFinal.add(paux2);
                puntosFinal.add(paux3);
                puntosFinal.add(paux4);
                puntosFinal.add(paux5);
                puntosFinal.add(paux6);
                puntosFinal.add(paux7);
                puntosFinal.add(paux8);
            }
            return puntosFinal;
        }
        
        class OyenteDeRaton extends MouseAdapter{
            public void mousePressed (MouseEvent evento){
                MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                p1=evento.getPoint();
                if(fill){
                    double xSeed=p1.getX();
                    double ySeed=p1.getY();
                    seed=myImage.getRGB((int)xSeed,(int)ySeed);
                    boundaryfill4(p1);
                }else if(boolTrasladar){
                    trasladar(p1);
                }else if(boolRotacion){
                    rotacion(p1);
                }
            }
            public void mouseReleased (MouseEvent evento){
                MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if(boolAgrupar){
                    p2=evento.getPoint();
                    agrupar(p1,p2);
                }else if (cuadrado){
                    p2=evento.getPoint();
                    
                    if(boolContinua){
                            if(boolNormal){
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),0,false);
                            }else if(boolMedio){
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),2,false);
                            }else{
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),4,false);
                            }
                        }else{
                            if(boolNormal){
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),0,true);
                            }else if(boolMedio){
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),2,true);
                            }else{
                                cuadrado(p1,puntoEscalado(p1,p2,(double)escala.getValue()),4,true);
                            }                        
                        }                   
                }else{
                    if(linea){
                        p2=evento.getPoint();
                        if(boolContinua){
                            if(boolNormal){
                                triangulo(p1,puntoEscalado(p1,p2, (double) escala.getValue()),0,false);
                            }else if(boolMedio){
                                triangulo(p1,puntoEscalado(p1,p2,(double)escala.getValue()),2,false);
                            }else{
                                triangulo(p1,puntoEscalado(p1,p2,(double)escala.getValue()),4,false);
                            }
                        }else{
                            if(boolNormal){
                                triangulo(p1,puntoEscalado(p1,p2,(double)escala.getValue()),0,true);
                            }else if(boolMedio){
                                triangulo(p1,puntoEscalado(p1,p2,(double)escala.getValue()),2,true);
                            }else{
                                triangulo(p1,puntoEscalado(p1,p2,(double)escala.getValue()),4,true);
                            }                        
                        }                                                
                    }else if (boolCircunferencia) {
                        p2=evento.getPoint();
                        if(boolContinua){
                            if(boolNormal){
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),0,false);
                            }else if(boolMedio){
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),1,false);
                            }else{
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),3,false);
                            }
                        }else{
                            if(boolNormal){
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),0,true);
                            }else if(boolMedio){
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),1,true);
                            }else{
                                circunferenciaBresenham(p1,puntoEscalado(p1,p2,(double)escala.getValue()),3,true);
                            }                        
                        }                                                
                    }                                        
                }            
            }
            
        }
        public void abrir(){
            try {
                JFileChooser jfc=createJFileChooser();
                jfc.showOpenDialog(this);
                File file =jfc.getSelectedFile();
                if (file==null){
                    return;
                }
                myImage=javax.imageio.ImageIO.read(file);
                int w=myImage.getWidth(null);
                int h=myImage.getHeight(null);
                if (myImage.getType()!=BufferedImage.TYPE_INT_RGB){
                    BufferedImage bi2 = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
                    Graphics big = bi2.getGraphics();
                    big.drawImage(myImage,0,0,null);
                }
                g2D=(Graphics2D)myImage.getGraphics();
                repaint();                
            } catch (IOException e){
                System.exit(1);
            }
        }
        
        public void guardar(){
            try {
                JFileChooser jfc=createJFileChooser();
                jfc.showOpenDialog(this);
                File file =jfc.getSelectedFile();
                if (file==null){
                    return;
                }
                javax.swing.filechooser.FileFilter ff = jfc.getFileFilter();
                String fileName=file.getName();
                String extension="jpg";
                if(ff instanceof MyFileFilter){
                    extension =((MyFileFilter)ff).getExtension();
                }
                fileName =fileName+"."+extension;
                file=new File (file.getParent(),fileName);
                javax.imageio.ImageIO.write(myImage,extension,file);
                               
            } catch (Exception e){
                System.out.println(e);
            }
        }
        public JFileChooser createJFileChooser(){
            JFileChooser jfc= new JFileChooser();
            jfc.setAcceptAllFileFilterUsed(false);
            String [] fileTypes= getFormats();
            for (int i =0;i<fileTypes.length;i++){
                jfc.addChoosableFileFilter(new MyFileFilter(fileTypes[i],fileTypes [i]+" file"));
            }
            return jfc;
        }
        public String [] getFormats (){
            String [] formats= javax.imageio.ImageIO.getWriterFormatNames();
            java.util.TreeSet<String> formatSet = new java.util.TreeSet<String>();
            for (String s: formats){
                formatSet.add(s.toLowerCase());                
            }
            return formatSet.toArray(new String [0]);            
        }
        class MyFileFilter extends javax.swing.filechooser.FileFilter{
            private String extension;
            private String description;
            public MyFileFilter(String extension,String description){
                this.extension=extension;
                this.description=description;                
            }
            public boolean accept (File f){
                return f.getName().toLowerCase().endsWith("."+extension) || f.isDirectory();               
            }
            public String getDescription (){
                return description;
            }
            public String getExtension(){
                return extension;
            }
        }
    }
}


