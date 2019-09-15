package br.uefs.ecomp.forteseguro.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Linha extends JLabel {

    int x1, y1, x2, y2;

    public Linha(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        g.drawLine(x1, y1, x2, y2);
    }

}
