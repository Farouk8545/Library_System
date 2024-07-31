import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background extends JPanel {
    Image background;
    Background() {
        try {
            background = ImageIO.read(new File("C:\\Users\\farou\\IdeaProjects\\Library Management System\\pngtree-large-library-with-wooden-bookshelves-and-wooden-floors-with-a-dark-image_2642910.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(background != null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}