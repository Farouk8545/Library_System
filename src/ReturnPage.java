import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReturnPage implements ActionListener {

    String userNameOriginal;
    DbConnection db = new DbConnection();
    JFrame frame = new JFrame("Return Page");
    JLabel label = new JLabel();
    JLabel label1 = new JLabel("You have no borrowed books.");
    JButton backButton = new JButton("Back");
    Background background = new Background();
    ImageIcon image = new ImageIcon("C:\\Users\\farou\\IdeaProjects\\Library Management System\\library.png");
    JButton[] buttons = new JButton[5];
    String[] borrowedBooks;
    ArrayList<String> borrowedBooksList;


    ReturnPage(String userName){

        userNameOriginal = userName;
        borrowedBooksList = new ArrayList<>(db.getBorrowedBooks(userNameOriginal));
        borrowedBooks = borrowedBooksList.toArray(new String[borrowedBooksList.size()]);

        label.setBounds(0,0,420,50);
        label.setText("What Book do you want to return? " + userName + ".");
        label.setFont(new Font(null, Font.BOLD, 15));
        label.setForeground(Color.WHITE);

        label1.setBounds(25, 165, 350, 50);
        label1.setFont(new Font(null, Font.BOLD, 25));
        label1.setForeground(Color.WHITE);

        backButton.setBounds(325,350,75,25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.add(label);
        frame.add(backButton);
        int space = 50;
        for(int i = 0; i < borrowedBooks.length; i++){
            buttons[i] = new JButton(borrowedBooks[i]);
            buttons[i].setBounds(25, (i*space+50), 200, 25);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
            frame.setVisible(true);
        }
        if(borrowedBooks.length == 0){
            frame.add(label1);
        }
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            frame.dispose();
            new HomePage(userNameOriginal);
        }
        for(int i = 0; i < borrowedBooks.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (db.returnBooks(userNameOriginal, borrowedBooks[i])) {
                    JOptionPane.showMessageDialog(frame, "You returned " + borrowedBooks[i] + " successfully! Come again soon :)");
                    frame.remove(buttons[i]);
                }else{
                    JOptionPane.showMessageDialog(frame, "Couldn't return " + borrowedBooks[i] + "! Please try again.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}