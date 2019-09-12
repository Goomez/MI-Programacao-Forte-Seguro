/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.forteseguro.view;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Desenhar extends JPanel{
    int x1, y1, x2, y2;
    public Desenhar(int x1, int y1, int x2, int y2){
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(x1, y1, x2, y2);
    }
}
