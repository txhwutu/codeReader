package codeReader;

import java.awt.*;
import java.awt.event.* ;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.CustomBalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.sf.json.JSON;
import net.sf.json.JSONObject;


public class FileListener implements ActionListener {
    View view;
    String fileOp;
    static private File file;
    private myFile mfile = new myFile();
    private FileDialog openDia,saveDia;
    public FileListener(View view, String fileOp){
        this.view = view;
        this.fileOp = fileOp;
    }

    @Override public void actionPerformed( ActionEvent e ){
        if("OPEN" == fileOp) openFile();
        else if("SAVE" == fileOp) saveFile();
        else if ("CLOSE" == fileOp) closeFile();
        else exitItem();
    }

        //ç’�å‰§ç–†éŽµæ’³ç´‘é�‚å›¦æ¬¢é�”ç†»å…˜
        public void openFile() {
            openDia = new FileDialog(view,"Open File",FileDialog.LOAD);
            openDia.setVisible(true);
            String dirPath = openDia.getDirectory();//é‘¾å³°å½‡é�‚å›¦æ¬¢ç’ºîˆšç·ž
            String fileName = openDia.getFile();//é‘¾å³°å½‡é�‚å›¦æ¬¢é�šå¶‡Ðž
            String content = new String();
            //System.out.println(dirPath +"++"+ fileName);

            //æ¿¡å‚›ç�‰éŽµæ’³ç´‘ç’ºîˆšç·ž éŽ´ï¿½ é�©î†¼ç¶�æ¶“è™¹â”– é�’æ¬’ç¹‘é�¥ç‚µâ”–
            if(dirPath == null || fileName == null) return ;
            view.getTP().setText("");//å¨“å‘¯â”–é�‚å›¨æ¹°
            file = new File(dirPath,fileName);
            try {
                BufferedReader bufr = new BufferedReader(new FileReader(file));
                String line;
                while( (line = bufr.readLine())!= null) {
                   content += line +"\r\n";
                }
                bufr.close();
            }
            catch (IOException ex) {
                throw new RuntimeException("File reading failed!");
            }
            JSONObject jsonObject=JSONObject.fromObject(content);
            myFile mFile = (myFile)JSONObject.toBean(jsonObject, myFile.class);
            view.getTP().setText(mFile.getText());
            Highlighter.HighlightPainter redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
            for (Pair i:mFile.getHlList()) {
                try {
                	view.getTP().getHighlighter().addHighlight(i.getStart(), i.getEnd(), redPainter);
                } catch (BadLocationException ble) {
                } 	
            }
            for (Anno i:mFile.getAnnoList()) {
            	Rectangle r = new Rectangle(i.getStart().intValue(), i.getEnd().intValue(), (int)i.getWidth(), (int)i.getHight());        	
                final CustomBalloonTip balloonTip =new CustomBalloonTip(
                        view.getTP(),
                        new JLabel(i.getContent()),
                        r,
                        new EdgedBalloonStyle(Color.WHITE, Color.BLUE),
                        BalloonTip.Orientation.LEFT_ABOVE,  BalloonTip.AttachLocation.ALIGNED,
                        0, 20,
                        false
                );
                // Add a close button that permanently close it
                balloonTip.setCloseButton(BalloonTip.getDefaultCloseButton(), true);
            }
        }

    public void saveFile(){
        saveDia = new FileDialog(view,"Save File",FileDialog.SAVE);
        Highlight[] h;
        saveDia.setVisible(true);
        String dirPath = saveDia.getDirectory();
        String fileName = saveDia.getFile();
        if(dirPath == null || fileName == null)
           return ;
        file = new File(dirPath,fileName);
        
        ArrayList<Pair> hlList = new ArrayList<>();
    	h = view.getTP().getHighlighter().getHighlights();
    	for (Highlight hl:h) {
    		hlList.add(new Pair(hl.getStartOffset(), hl.getEndOffset()));
    	}
    	mfile.setHlList(hlList);
    	mfile.setText(view.getTP().getText());
    	mfile.setAnnoList(view.getannolist());
    	JSONObject jsonObject = JSONObject.fromObject(mfile);
        System.out.println(jsonObject.toString());
        try {
            BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
            bufw.write(jsonObject.toString());
            bufw.close();
        }
        catch (IOException ex) {
            throw new RuntimeException("File saving failed! ");
        }
    }

    public void closeFile(){
        view.getTP().setText("");
    }

    public void exitItem(){
        System.exit(0);
    }
    }

