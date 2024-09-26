package GUIs.panels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SideBottomPanel extends JPanel{

    public SideBottomPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }
}
