import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    JFrame frame = new JFrame("Home Page");
    JLabel welcomeLabel = new JLabel();
    JLabel questionLabel = new JLabel("What do you want to do today?");
    JButton borrowButton = new JButton("Borrow");
    JButton returnButton = new JButton("Return");
    JButton logOutButton = new JButton("Log Out");
    Background background = new Background();
    ImageIcon image = new ImageIcon("C:\\Users\\farou\\IdeaProjects\\Library Management System\\library.png");
    String userNameOriginal;

    HomePage(String userName){

        userNameOriginal = userName;

        welcomeLabel.setBounds(0,0,420,50);
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setText("Welcome, " + userName + " :)");

        questionLabel.setBounds(0,50,420,50);
        questionLabel.setFont(new Font(null, Font.BOLD, 20));
        questionLabel.setForeground(Color.WHITE);

        borrowButton.setBounds(40,160,150,60);
        borrowButton.setFocusable(false);
        borrowButton.addActionListener(this);

        returnButton.setBounds(220,160,150,60);
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);

        logOutButton.setBounds(300,350,100,25);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setContentPane(background);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(welcomeLabel);
        frame.add(questionLabel);
        frame.add(borrowButton);
        frame.add(returnButton);
        frame.add(logOutButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == borrowButton){
            frame.dispose();
            new BorrowPage(userNameOriginal);
        }
        if(e.getSource() == returnButton){
            frame.dispose();
            new ReturnPage(userNameOriginal);
        }
        if(e.getSource() == logOutButton){
            frame.dispose();
            new LoginPage();
        }
    }
}