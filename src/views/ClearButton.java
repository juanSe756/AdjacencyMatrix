package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClearButton extends JButton {
    public ClearButton(String text, Font font,Color colorFont,int width,int height,Color inMouse){
        super(text);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(width,height));
        setFont(font);
        setForeground(colorFont);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(inMouse);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.WHITE);
            }
        });
    }
}
