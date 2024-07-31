import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BorrowPage implements ActionListener {

    String[] genres = {"Fiction", "Historical Fiction", "Romance", "Mystery", "Science Fiction"};
    String[] fiction = {"Nineteen Eighty-Four", "The Alchemist", "The Black Bird Oracle: A Novel", "The Great Gatsby"};
    String[] historicalFiction = {"Pachinko", "Wolf Hall", "The Book Thief", "The Nightingale", "Homegoing"};
    String[] romance = {"Pride and Prejudice", "Outlander", "The Notebook", "Me Before You", "Gone with the Wind"};
    String[] mystery = {"The Girl with the Dragon Tattoo", "Gone Girl", "The Da Vinci Code", "The Hound of the Baskervilles", "And Then There Were None"};
    String[] scienceFiction = {"Dune", "1984", "The Hitchhiker's Guide to the Galaxy", "Ender's Game", "Neuromancer"};
    JFrame frame = new JFrame("Borrow Page");
    JLabel label = new JLabel();
    JLabel label1 = new JLabel("Genre:");
    JLabel label2 = new JLabel("Book:");
    JComboBox genre = new JComboBox(genres);
    JComboBox book = new JComboBox(fiction);
    JButton button = new JButton("Borrow");
    JButton backButton = new JButton("Back");
    String userNameOriginal;
    Background background = new Background();
    ImageIcon image = new ImageIcon("C:\\Users\\farou\\IdeaProjects\\Library Management System\\library.png");
    DbConnection db = new DbConnection();

    BorrowPage(String userName) {

        userNameOriginal = userName;

        label.setBounds(0,0,420,50);
        label.setText("What Book do you want to borrow? " + userName + ".");
        label.setFont(new Font(null, Font.BOLD, 15));
        label.setForeground(Color.WHITE);

        label1.setBounds(25, 50, 75, 25);
        label1.setForeground(Color.WHITE);

        label2.setBounds(25, 100, 75, 25);
        label2.setForeground(Color.WHITE);

        genre.setFont(new Font(null, Font.BOLD, 15));
        genre.setBounds(80, 50, 150, 25);
        genre.addActionListener(this);
        genre.setEditable(true);

        book.setFont(new Font(null, Font.BOLD, 15));
        book.setBounds(80, 100, 300, 25);
        book.setEditable(true);

        button.setBounds(200,200,100,25);
        button.setFocusable(false);
        button.addActionListener(this);

        backButton.setBounds(395,350,75,25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setSize(500,420);
        frame.setLayout(null);
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(genre);
        frame.add(book);
        frame.add(button);
        frame.add(backButton);
        frame.setVisible(true);
    }

    BorrowPage(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == genre){
            updateBookComboBox();
        }
        if(e.getSource() == backButton) {
            frame.dispose();
            new HomePage(userNameOriginal);
        }
        if(e.getSource() == button) {
            try {
                if(db.canBorrowMoreBooks(userNameOriginal)){
                    if(db.storeBorrowedBook(userNameOriginal, (String)book.getSelectedItem())){
                        JOptionPane.showMessageDialog(frame, "You have borrowed " + book.getSelectedItem() + " successfully!");
                    }else{
                        JOptionPane.showMessageDialog(frame, "Error borrowing the book! Please try again.", null, JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "You can only borrow 5 books at once!",null ,JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    void updateBookComboBox() {
        String selectedGenre = (String) genre.getSelectedItem();
        book.removeAllItems();

        switch (selectedGenre) {
            case "Fiction":
                addItemsToComboBox(fiction);
                break;
            case "Historical Fiction":
                addItemsToComboBox(historicalFiction);
                break;
            case "Romance":
                addItemsToComboBox(romance);
                break;
            case "Mystery":
                addItemsToComboBox(mystery);
                break;
            case "Science Fiction":
                addItemsToComboBox(scienceFiction);
                break;
        }
    }

    void addItemsToComboBox(String[] items) {
        for (String item : items) {
            book.addItem(item);
        }
    }
}