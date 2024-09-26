package GUIs.buttons;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class FunctionButton extends JButton{

    public FunctionButton(int width, int height, String name){
        this.setFocusable(false);
        this.setPreferredSize( new Dimension(width, height));
        this.setFont(new Font("Verdana", Font.PLAIN,16));
        this.setText(name);
    }

}
