package codeReader;
import javax.swing.*;
import java.awt.*;

public class View extends javax.swing.JFrame{

    private JMenuBar bar;
    private JMenu fileMenu;
    private JMenuItem openItem,saveItem,closeItem,exitItem;
    private JMenu editMenu;
    private JMenuItem addAnno,deleteAnno,addHL,deleteHL;
    private JTextPane textPane;
    private JScrollPane scrollPane;

    View(){
        bar = new JMenuBar();

        /* File Menu */
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        openItem.addActionListener(new FileListener(this,"OPEN"));
        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new FileListener(this,"SAVE"));
        closeItem = new JMenuItem("Close");
        closeItem.addActionListener(new FileListener(this,"CLOSE"));
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new FileListener(this,"EXIT"));
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.add(exitItem);
        /* Annotation Menu */
        editMenu = new JMenu("Edit");
        addAnno = new JMenuItem("Add Annotation");
        deleteAnno = new JMenuItem("Delete Annotation");
        addHL = new JMenuItem("Add Highlight");
        deleteHL = new JMenuItem("Delete Highlight");
        editMenu.add(addAnno);
        editMenu.add(deleteAnno);
        editMenu.add(addHL);
        addHL.addActionListener(new EditListener(this, "Add Highlight"));
        editMenu.add(deleteHL);
        /* Bar */
        bar.add(fileMenu);
        bar.add(editMenu);
        setJMenuBar( bar );
        textPane = new JTextPane();
        add( textPane );
        /* Frame */
        setSize(1600,900);
        setVisible( true );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override public void run() {
                new View() ;
            }
        } ) ;
    }

    public JTextPane getTP(){
        return textPane;
    }

}
