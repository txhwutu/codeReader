package codeReader;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends javax.swing.JFrame{

    private JMenuBar bar;
    private JMenu fileMenu;
    private JMenuItem openItem,saveItem,closeItem,exitItem;
    private JMenu editMenu;
    private JMenuItem addAnno,deleteAnno,addHL,deleteHL;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private ArrayList<Anno> annoList = new ArrayList<>();

    View(){
        bar = new JMenuBar();
        textPane = new JTextPane();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        addAnno.addActionListener(new EditListener(this,"Add Annotation"));
        deleteAnno = new JMenuItem("Delete Annotation");
        addHL = new JMenuItem("Add Highlight");
        addHL.addActionListener(new EditListener(this,"Add Highlight"));
        deleteHL = new JMenuItem("Delete Highlight");
        deleteHL.addActionListener(new EditListener(this,"Delete Highlight"));
        editMenu.add(addAnno);
        editMenu.add(deleteAnno);
        editMenu.add(addHL);
        editMenu.add(deleteHL);
        /* Bar */
        bar.add(fileMenu);
        bar.add(editMenu);
        setJMenuBar( bar );
        scrollPane.setBounds( 0, 0, 200, 200 );
        add( scrollPane );


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
    
    public void addanno(Anno r) {
		this.annoList.add(r);
	}
    
    public ArrayList<Anno> getannolist() {
		return annoList;
	}
}
