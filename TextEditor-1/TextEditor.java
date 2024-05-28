

import javax.swing.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TextEditor extends JFrame implements ActionListener{
    // Component of the text editor

    private JTextArea textArea;
    private JFrame frame;
    private UndoManager undoManager;

    public TextEditor(){

        //create the Frame
        frame = new JFrame("text Editor");

        // Craete TexArea
        textArea = new JTextArea();
        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);

        // create  the Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // create  the file Menu
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        // Create File Menu items
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        //Create Edit Menu Items
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");


        // Add Action Listener to the menu items
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add Action Listener to the Edit menu items
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        cutItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);

        // Add the File Menu items to the file Menu

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add the Edit Menu items to the edit Menu
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(cutItem);
        editMenu.addSeparator();
        editMenu.add(undoItem);
        editMenu.add(redoItem);

        // Add the Menus to the Menu Bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        //set the Menu Bar to the Frame
        frame.setJMenuBar(menuBar);

        // Add the textArea to the Frame
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // set the Frame properties
        frame.setSize(900,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    //Action listener for Menu Items

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        switch (command){
            case "New":
                newFile();
                break;
            case  "Open":
                openFile();
                break;
            case "Save":
                saveFile();
                break;
            case "Exit":
                exitEditor();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Cut":
                textArea.cut();
                break;
            case "Undo":
                if (undoManager.canUndo()){
                    undoManager.undo();
                }
                break;
            case "Redo":
                if (undoManager.canRedo()){
                    undoManager.canRedo();
                }
                break;
        }
    }

    // Method to create a newFile
    private void newFile(){
        textArea.setText("");
    }
    // Method to create an exiting File
    private void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();

            try ( BufferedReader reader = new BufferedReader(new FileReader(file))){
                textArea.read(reader,null);
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    // Method to save the current File

    private void saveFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION){

            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                textArea.write(writer);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
    // Method to exit the Editor

    private void exitEditor(){
        frame.dispose();
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(TextEditor::new);
    }
}

