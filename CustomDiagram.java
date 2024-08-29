import javax.swing.*;
import java.awt.*;

public class CustomDiagram extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Set initial coordinates and dimensions
        int x = 50;
        int y = 50;
        int width = 100;
        int height = 60;
        int space = 20;  // space between rectangles

        // Arrays holding the data
        String[] patterns = {"EG", "ED", "EG", "EH", "EF"};
        String[] pids = {"P5P6P9", "O", "P6", "O", "P9"};
        String[] gains = {"2,500", "1,400", "1,600", "1,400", "1,500"};
        
        for (int i = 0; i < patterns.length; i++) {
            // Draw the rectangle
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, width, height);

            // Draw the border
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);

            // Draw the text inside the rectangle
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString(patterns[i], x + 30, y + 20);
            g.drawString(pids[i], x + 30, y + 35);
            g.drawString(gains[i], x + 30, y + 50);

            // Draw the arrow to the next rectangle
            if (i < patterns.length - 1) {
                int arrowStartX = x + width;
                int arrowStartY = y + height / 2;
                int arrowEndX = arrowStartX + space;
                int arrowEndY = arrowStartY;

                // Draw line
                g.drawLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY);
                // Draw arrowhead
                g.drawLine(arrowEndX, arrowEndY, arrowEndX - 5, arrowEndY - 5);
                g.drawLine(arrowEndX, arrowEndY, arrowEndX - 5, arrowEndY + 5);
            }

            // Move x-coordinate for the next rectangle
            x += width + space;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Diagram");
        CustomDiagram diagram = new CustomDiagram();
        frame.add(diagram);
        frame.setSize(800, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}