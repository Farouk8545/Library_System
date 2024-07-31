import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends DbConnection implements ActionListener {

    private String userName;
    private String password;
    JFrame frame = new JFrame("Login Page");
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JLabel userNameLabel = new JLabel("User Name");
    JLabel passwordLabel = new JLabel("Password");
    JLabel welcomeLabel = new JLabel("Welcome to our library :)");
    JLabel errorLabel = new JLabel("");
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    Background background = new Background();
    ImageIcon image = new ImageIcon("C:\\Users\\farou\\IdeaProjects\\Library Management System\\library.png");

    public LoginPage() {

        userNameField.setBounds(100,100,150,25);
        passwordField.setBounds(100,150,150,25);

        userNameLabel.setBounds(30,100,150,25);
        passwordLabel.setBounds(30,150,150,25);
        userNameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        welcomeLabel.setBounds(0,0,420,75);
        welcomeLabel.setFont(new Font(null, Font.BOLD,30));
        welcomeLabel.setForeground(Color.WHITE);

        errorLabel.setBounds(120,210,150,40);
        errorLabel.setFont(new Font(null, Font.BOLD,15));
        errorLabel.setForeground(Color.RED);

        loginButton.setBounds(75,250,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton.setBounds(225,250,100,25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setContentPane(background);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(welcomeLabel);
        frame.add(userNameLabel);
        frame.add(passwordLabel);
        frame.add(userNameField);
        frame.add(passwordField);
        frame.add(errorLabel);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.setVisible(true);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            setUserName(userNameField.getText());
            setPassword(String.valueOf(passwordField.getPassword()));
            boolean validationResult = validateUser(getUserName(), getPassword());

            if(validationResult){
                frame.dispose();
                new HomePage(getUserName());
                frame.dispose();
            }else{
                errorLabel.setText("Invalid Username or Password");
            }

        }
        if(e.getSource() == registerButton) {
            frame.dispose();
            new RegisterPage();

        }
    }
}