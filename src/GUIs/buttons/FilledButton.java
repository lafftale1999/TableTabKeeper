package GUIs.buttons;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;


public class FilledButton extends JButton{

    public FilledButton(){
        this.setBackground(new Color(0x00507A));
        this.setFont(new Font("Verdana", Font.PLAIN,16));
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 50));
        this.setFocusable(false);
    }
}
