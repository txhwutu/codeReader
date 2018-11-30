package codeReader;

import java.awt.*;
import java.awt.event.* ;
import java.io.*;
import java.util.ArrayList;

import javax.swing.text.Highlighter.Highlight;

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

        //璁剧疆鎵撳紑鏂囦欢鍔熻兘
        public void openFile() {
            openDia = new FileDialog(view,"Open File",FileDialog.LOAD);
            openDia.setVisible(true);
            String dirPath = openDia.getDirectory();//鑾峰彇鏂囦欢璺緞
            String fileName = openDia.getFile();//鑾峰彇鏂囦欢鍚嶇О
            String content = new String();
            //System.out.println(dirPath +"++"+ fileName);

            //濡傛灉鎵撳紑璺緞 鎴� 鐩綍涓虹┖ 鍒欒繑鍥炵┖
            if(dirPath == null || fileName == null) return ;
            view.getTP().setText("");//娓呯┖鏂囨湰
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

