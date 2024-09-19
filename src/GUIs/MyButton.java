package GUIs;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton extends JButton implements ActionListener{
    
    MyButton(JFrame container, String buttonName){

        // setting the size relative to the parentcomponent
        this.setPreferredSize(new DimensionUIResource(200,100));

        // creates the text for button
        this.setText(buttonName);

        // removes wierd border around clicked buttons
        this.setFocusable(false);

        // setting the font
        this.setFont(new FontUIResource("Verdana", FontUIResource.PLAIN,20));
        
        // setting colors
        this.setForeground(Color.GRAY);
        this.setBackground(Color.LIGHT_GRAY);

        // creating borders
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        this.addActionListener(this);
        
        // adding it to a container
        container.add(this);
    }

    // Function for ActionListener
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this) {
            System.out.println("Tab Paid");
        }
        
    }
}
