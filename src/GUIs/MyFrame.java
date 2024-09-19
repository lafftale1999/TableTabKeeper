package GUIs;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

    public MyFrame(){
        
        ImageIcon image = new ImageIcon("TableTabLogo.png");

        this.setIconImage(image.getImage());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("TableTab - Keep your tabs in order!");
        this.setResizable(false);

    }
}
