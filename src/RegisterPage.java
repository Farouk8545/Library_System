import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends DbConnection implements ActionListener {
    String userName, password, email;
    int age;

    JFrame frame = new JFrame("Register Page");
    JLabel userNameLabel = new JLabel("Enter Username:");
    JLabel passwordLabel = new JLabel("Enter Password:");
    JLabel emailLabel = new JLabel("Enter Email:");
    JLabel ageLabel = new JLabel("Enter Age:");
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailField = new JTextField();
    JTextField ageField = new JTextField();
    JButton registerButton = new JButton("Register");
    Background background = new Background();
    ImageIcon image = new ImageIcon("C:\\Users\\farou\\IdeaProjects\\Library Management System\\library.png");

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    RegisterPage(){

        userNameField.setBounds(140,100,150,25);
        passwordField.setBounds(140,150,150,25);
        emailField.setBounds(140,200,150,25);
        ageField.setBounds(140,250,150,25);

        userNameLabel.setBounds(30,100,150,25);
        passwordLabel.setBounds(30,150,150,25);
        emailLabel.setBounds(30,200,150,25);
        ageLabel.setBounds(30,250,150,25);
        userNameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        ageLabel.setForeground(Color.WHITE);

        registerButton.setBounds(150,300,100,25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(background);
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setSize(420, 420);
        frame.add(userNameLabel);
        frame.add(passwordLabel);
        frame.add(emailLabel);
        frame.add(ageLabel);
        frame.add(userNameField);
        frame.add(passwordField);
        frame.add(emailField);
        frame.add(ageField);
        frame.add(registerButton);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerButton){
            setUserName(userNameField.getText());
            setPassword(passwordField.getText());
            setEmail(emailField.getText());
            setAge(Integer.parseInt(ageField.getText()));
            if(super.saveUserInput(getUserName(),getPassword(),getEmail(),getAge())){
                JOptionPane.showMessageDialog(frame, "You have successfully registered! You can now login with your account.");
                frame.dispose();
                new LoginPage();
            }else{
                JOptionPane.showMessageDialog(frame, "Something went wrong! Please try again.", null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}