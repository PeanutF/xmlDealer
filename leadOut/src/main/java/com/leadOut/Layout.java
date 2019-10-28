package com.leadOut;

import com.eltima.components.ui.DatePicker;
import com.leadOut.dao.PhotoFileDao;
import com.leadOut.impl.PhotoFileDaoImpl;
import com.leadOut.service.AudioService;
import com.leadOut.service.PhotoService;
import com.leadOut.tool.Config;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Layout extends JFrame {

    SqlSession sqlSession;
    PhotoFileDao photoFileDao;


    private static Calendar c=Calendar.getInstance();//通过calendar去获取具体时间
    private static DatePicker startTime, endTime;

    private final static int audioType = 0;
    private final static int photoType = 1;

    private static String xmlDirectoryPath;

    public static int process = 0;
    public static boolean empty = false;

    public void init(){

        process = 0;
        xmlDirectoryPath = Config.xmlDirectoryPath;
        String resource = "mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        this.photoFileDao = new PhotoFileDaoImpl(sqlSession);
    }

    public static void main(String args[]){


        Config config = new Config();
        Layout layout = new Layout();
    }

    public Layout(){
        init();

        this.setTitle("三峡数据转换工具");

        JLabel label=new JLabel("文件类型:");
        this.add(label);
        final JComboBox comboBox=new JComboBox();
        comboBox.setPreferredSize(new Dimension(150,25));
        comboBox.addItem("视频");
        comboBox.addItem("图片");
        this.add(comboBox);

        String DefaultFormat = "yyyy-MM-dd";//如果设置成yyyy-MM-dd则获取年月日
        Date date = new Date();
        Font font = new Font("Times New Roman", Font.BOLD, 14);//字体、大小
        Dimension dimension = new Dimension(177, 24);//控件大小
        startTime = new DatePicker(date, DefaultFormat, font, dimension);
        startTime.setBounds(266, 83, 121, 21);
        startTime.setLocale(Locale.CHINA);
        startTime.setTimePanleVisible(true);
        JLabel jLabel = new JLabel();
        jLabel.setText("起始时间:");
        this.add(jLabel);
        this.add(startTime);//面板中加入控件

        endTime = new DatePicker(date, DefaultFormat, font, dimension);
        endTime.setBounds(266, 83, 121, 21);
        endTime.setLocale(Locale.CHINA);
        endTime.setTimePanleVisible(true);
        JLabel jLabel1 = new JLabel();
        jLabel1.setText("结束时间:");
        this.add(jLabel1);
        this.add(endTime);//面板中加入控件

        //路径选择
        JButton setPathButton = new JButton("选择路径");

        setPathButton.setBounds(50,50,50,50);
        this.add(setPathButton);
        final JTextField pathS = new JTextField(20);
        pathS.setEditable(false);
        pathS.setBounds(50,50,200,50);
        pathS.setText(xmlDirectoryPath);
        this.add(pathS);
        final Layout test = this;
        setPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPath(test,pathS);
            }
        });

        //保存按钮和进度条
        JButton queryAll = new JButton("开始导出");
        queryAll.setBounds(50,50,100,100);

        final JProgressBar bar = new JProgressBar();
        bar.setMaximum(0);
        bar.setMaximum(100);
        bar.setStringPainted(true);
        queryAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new PhotoService().createAllPhotoXML();
//                dealer.createPhotoXMLFile(photoFiles,new File("D:\\dom4j.xml"));
                empty = false;
                String startTimeS = startTime.getText();
                String endTimeS = endTime.getText();
                startTimeS = startTimeS.replace("-","");
                endTimeS = endTimeS.replace("-","");

                new Progress(bar).start();

                new Execute(startTimeS,endTimeS,test,comboBox.getSelectedIndex()).start();

            }
        });
        this.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
        this.add(queryAll);
        this.add(bar);
        this.setSize(300,400);
        this.setLocation(200,200);
        this.setVisible(true);
    }


    public String getPath(Component parent,JTextField msgTextArea){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"保存", 则获取选择的保存路径
            File file = fileChooser.getSelectedFile();
            xmlDirectoryPath = file.getAbsolutePath();
            msgTextArea.setText(xmlDirectoryPath);
            return xmlDirectoryPath;
        }

        return null;
    }

    private class Progress extends Thread
    {
        JProgressBar progressBar;
        Progress(JProgressBar progressBar)
        {
            this.progressBar=progressBar;
        }
        public void run()
        {
            while (process < 100){
                progressBar.setValue(process);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            progressBar.setValue(process);
            process = 0;
        }
    }


    //用于处理数据转换的进程
    private class Execute extends Thread{
        String startTimeS,endTimeS;
        Layout layout;
        int type;
        Execute(String startTimeS,String endTimeS, Layout test, int type){
            this.startTimeS = startTimeS;
            this.endTimeS = endTimeS;
            this.layout = test;
            this.type = type;
        }
        public void run(){

            if (type == photoType)
                new PhotoService().createXMLByTime(startTimeS,endTimeS, xmlDirectoryPath);
            else
                new AudioService().createXMLByTime(startTimeS,endTimeS, xmlDirectoryPath);
            if (empty){
                JOptionPane.showMessageDialog(layout,"数据为空","提示",0);
            }
        }
    }
}
