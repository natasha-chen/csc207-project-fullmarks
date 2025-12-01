package view;

import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && placeholder != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, 5, getHeight() / 2 + getFont().getSize() / 2 - 3);
            g2.dispose();
        }
    }
}
