package codeReader;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.Highlighter.Highlight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.CustomBalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;

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

        String inputValue = JOptionPane.showInputDialog("Please input an annotation");
        Rectangle rectangle = new Rectangle();
        try {
            rectangle = view.getTP().modelToView(view.getTP().getCaretPosition());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        view.addanno(new Anno(rectangle.getX(), rectangle.getY(), rectangle.getHeight(), rectangle.getWidth(), inputValue));

        EdgedBalloonStyle style = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
        // Now construct the balloon tip

        final CustomBalloonTip balloonTip =new CustomBalloonTip(
                view.getTP(),
                new JLabel(inputValue),
                rectangle,
                new EdgedBalloonStyle(Color.WHITE, Color.BLUE),
                BalloonTip.Orientation.LEFT_ABOVE,  BalloonTip.AttachLocation.ALIGNED,
                0, 20,
                false
        );
        // Add a close button that permanently close it
        balloonTip.setCloseButton(BalloonTip.getDefaultCloseButton(), true);

    }
    
    public void deleteAnno(){
        //暂时用来实现写xml

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
        System.out.println( start == end);
        Highlight[] h = view.getTP().getHighlighter().getHighlights();
        for (Highlight hl:h) {
            if (start >= hl.getStartOffset() && end <= hl.getEndOffset())
            view.getTP().getHighlighter().removeHighlight(hl);
        }
    }
}
