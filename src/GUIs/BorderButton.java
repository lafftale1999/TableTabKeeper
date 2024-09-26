package GUIs;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BorderFactory;

public class BorderButton extends JButton{

    private String name;

    public BorderButton(String name){
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00507A), 2));
        this.setBackground(Color.WHITE);
        this.setFont(new Font("Verdana", Font.PLAIN,16));
        this.setForeground(new Color(0x00507A));
        this.setPreferredSize(new Dimension(200, 50));
        this.setText(name);
        this.setFocusable(false);
    }
}
