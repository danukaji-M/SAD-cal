
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame implements ActionListener {

    TextField display;
    Button[] newButtons = new Button[16];

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    //constructor
    public Calculator() {
        //setFrame properties
        setTitle("Calculator");
        setSize(400, 600);
        setLayout(new BorderLayout());

        Font font1 = new Font("IBM Mono", Font.BOLD, 18);
        Font font2 = new Font("IBM Mono", Font.BOLD, 24);

        //initialize and setup the navigation bar
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setMenuBar(menuBar);

        //Create Panel for Display (top screen)
        Panel displayPanel = new Panel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setFont(font2);
        display = new TextField();
        display.setPreferredSize(new Dimension(400, 200));
        display.setEditable(false);
        display.setText("0");
        displayPanel.add(display, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        Panel numberPadPanel = new Panel();
        numberPadPanel.setLayout(new GridLayout(4, 4, 5, 5));

        newButtons[0] = new Button("7");
        newButtons[1] = new Button("8");
        newButtons[2] = new Button("9");
        newButtons[3] = new Button("×");
        newButtons[4] = new Button("4");
        newButtons[5] = new Button("5");
        newButtons[6] = new Button("6");
        newButtons[7] = new Button("/");
        newButtons[8] = new Button("1");
        newButtons[9] = new Button("2");
        newButtons[10] = new Button("3");
        newButtons[11] = new Button("C");
        newButtons[12] = new Button("0");
        newButtons[13] = new Button("=");
        newButtons[14] = new Button("+");
        newButtons[15] = new Button("-");

        //Add action listners to number buttons
        for (int i = 0; i < 16; i++) {
            if (newButtons[i] != null) {
                newButtons[i].addActionListener(this);
                newButtons[i].setFont(font1);
                numberPadPanel.add(newButtons[i]);
            }
        }

        add(numberPadPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

    }

    public static void showAboutDialog() {
        System.out.println("About");
    }

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    System.out.println(command.charAt(0));
    
    if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
        if (display.getText().equals("0")) {
            display.setText("");
        }
        display.setText(display.getText() + command);
    } else if (command.charAt(0) == 'C') {
        display.setText("0");
    } else if (command.charAt(0) == '=') {
        num2 = Double.parseDouble(display.getText()); // Set num2 here
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '×': // Use correct operator symbol
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        display.setText(String.valueOf(result));
        num1 = result;
    } else {
        if (!display.getText().equals("")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
    }
}


}
