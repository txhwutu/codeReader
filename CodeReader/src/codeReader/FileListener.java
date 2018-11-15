package codeReader;

import java.awt.*;
import java.awt.event.* ;
import java.io.*;


public class FileListener implements ActionListener {
    View view;
    String fileOp;
    static private File file;
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

        //设置打开文件功能
        public void openFile() {
            openDia = new FileDialog(view,"Open File",FileDialog.LOAD);
            openDia.setVisible(true);
            String dirPath = openDia.getDirectory();//获取文件路径
            String fileName = openDia.getFile();//获取文件名称
            //System.out.println(dirPath +"++"+ fileName);

            //如果打开路径 或 目录为空 则返回空
            if(dirPath == null || fileName == null) return ;
            view.getTA().setText("");//清空文本
            file = new File(dirPath,fileName);
            try {
                BufferedReader bufr = new BufferedReader(new FileReader(file));
                String line = null;
                while( (line = bufr.readLine())!= null) {
                    view.getTA().append(line +"\r\n");
                }
                bufr.close();
            }
            catch (IOException ex) {
                throw new RuntimeException("File reading failed!");
            }
        }

    public void saveFile(){
        saveDia = new FileDialog(view,"Save File",FileDialog.SAVE);
        if(file == null){//文件不存在情况下 创建文件
            saveDia.setVisible(true);
            String dirPath = saveDia.getDirectory();
            String fileName = saveDia.getFile();
            if(dirPath == null || fileName == null)
                return ;
            file = new File(dirPath,fileName);
        }
        try {
            BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
            String text = view.getTA().getText();
            bufw.write(text);
            bufw.close();
        }
        catch (IOException ex) {
            throw new RuntimeException("File saving failed! ");
        }
    }

    public void closeFile(){
        view.getTA().setText("");
    }

    public void exitItem(){
        System.exit(0);
    }
    }

