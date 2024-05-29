import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    //components of the calculator

    private JTextField display;
    private JPanel buttonPanel;
    private String [] buttonLables ={
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","+",
            "0",".","=","-",
            "sin","cos","tan","log","sqrt","c"
    };
    private JButton[] buttons = new JButton[buttonLables.length];

    private String currentOp = "";
    private double firstOperand = 0;

    public  Calculator(){

        //Frame Setting
        setTitle("Scintific Calculator");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Display Field
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        //Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,4));
        add(buttonPanel, BorderLayout.CENTER);

        //Add Button to Panel
        for (int i=0;i<buttonLables.length;i++){
            buttons[i] =new JButton(buttonLables[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        try {
            switch (command){
                case "=":
                    calculate();
                    break;
                case "C":
                    display.setText("");
                    currentOp = "";
                    firstOperand = 0;
                    break;
                case "sin":
                case "cos":
                case "tan":
                case "log":
                case "sqrt":
                    performScitificOp(command);
                    break;
                default:
                    if ("+-*/".contains(command)){
                        firstOperand = Double.parseDouble(display.getText());
                        currentOp = command;
                        display.setText("");
                    }else {
                        display.setText(display.getText() + command);
                    }
                    break;
            }
        }catch (Exception ex){
            display.setText("Error");
        }
    }

    private void calculate(){
        double secondOprand = Double.parseDouble(display.getText());
        double result = 0;
        switch (currentOp){
            case "+":
                result = firstOperand + secondOprand;
                break;
            case "-":
                result = firstOperand - secondOprand;
                break;
            case "*":
                result = firstOperand * secondOprand;
                break;
            case "/":
                result = firstOperand / secondOprand;
                break;
        }
        display.setText(String.valueOf(result));
        currentOp = "";

    }

    private void  performScitificOp(String command){
        double value = Double.parseDouble(display.getText());
        double result = 0;
        switch (command){
            case "sin":
                result = Math.sin(Math.toRadians(value));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(value));
                break;
            case "tan":
                result =Math.tan(Math.toRadians(value));
                break;
            case "log":
                result = Math.log10(Math.toRadians(value));
                break;
            case "sqrt":
                result = Math.sqrt(Math.toRadians(value));
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(() ->{
            Calculator calculator = new  Calculator();
            calculator.setVisible(true);
        });

    }
}



