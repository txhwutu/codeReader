package codeReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListener implements ActionListener {
    View view;
    String editOp;
    public EditListener(View view, String editOp) {
        this.view = view;
        this.editOp = editOp;
    }
    @Override public void actionPerformed( ActionEvent e ){
        if("Add Annotation" == editOp) addAnno();
        else if("Delete Annotation" == editOp) deleteAnno();
        else if ("Add Highlight" == editOp) addHL();
        else deleteHL();
    }
    public void addAnno(){

    }
    public void deleteAnno(){

    }
    public void addHL(){
        int from = view.getTA().getSelectionStart();
        int to = view.getTA().getSelectionEnd();
        view.getTA().setSelectionColor(Color.YELLOW);
    }
    public void deleteHL(){

    }
}
