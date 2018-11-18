package codeReader;

import javax.swing.text.*;
import javax.swing.text.Highlighter.Highlight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.sun.glass.ui.Cursor.setVisible;

public class EditListener implements ActionListener {
    View view;
    String editOp;
    public EditListener(View view, String editOp) {
        this.view = view;
        this.editOp = editOp;
    }
    
    @Override 
    public void actionPerformed( ActionEvent e ){
        if("Add Annotation" == editOp) addAnno();
        else if("Delete Annotation" == editOp) deleteAnno();
        else if ("Add Highlight" == editOp) {addHL(); }
        else deleteHL();
    }
    
    public void addAnno(){

    }
    
    public void deleteAnno(){

    }
    
    public void addHL(){
        int start = view.getTP().getSelectionStart();
        int end = view.getTP().getSelectionEnd();
        Highlighter.HighlightPainter redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
        try {
        	view.getTP().getHighlighter().addHighlight(start, end, redPainter);
        } catch (BadLocationException ble) {
        }
    }
    
    public void deleteHL(){
        int start = view.getTP().getSelectionStart();
        int end = view.getTP().getSelectionEnd();
    }
}
