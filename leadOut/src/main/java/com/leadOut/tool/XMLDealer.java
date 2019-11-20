package com.leadOut.tool;

import com.leadOut.entity.AudioFile;
import com.leadOut.entity.PhotoFile;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLDealer {



    public void addAttribute(Element entityData, String itemCode,String itemName,String value)
    {
        Element attributeItem2 = entityData.addElement("AttributeItem");
        attributeItem2.addElement("ItemCode").addText(itemCode);
        attributeItem2.addElement("ItemName").addText(itemName);
        if(value != "" && !value.substring(0,1).equals(" ")){
            if (value.equals("内部公开") && itemCode.equals("privilege"))
                attributeItem2.addElement("Value").addText("公开");
            else if (itemCode.equals("createdate")){

                int a = value.length();
                if (value.length() == 4 || value.substring(4,6).equals("  "))
                    attributeItem2.addElement("Value").addText(value.substring(0,4) + "-01-01");
                else if (value.length() == 6 || value.substring(6,8).equals("  "))
                    attributeItem2.addElement("Value").addText(value.substring(0,4) + "-" + value.substring(4,6) + "-01");
                else if (value.length() == 8 || value.length() == 10)
                    attributeItem2.addElement("Value").addText(value.substring(0,4) + "-" + value.substring(4,6) + "-" + value.substring(6,8));
            }
            else
                attributeItem2.addElement("Value").addText(value);
        }

        else
            attributeItem2.addElement("Value");
    }
    public Element addEntity(AudioFile audioFile, Element entityData) {
        addAttribute(entityData,"clipguid","素材GUID",audioFile.getGuid());
//        addAttribute(entityData,"name","素材名称",audioFile.getName_());
//        addAttribute(entityData,"clipname","素材名称",audioFile.getName_());
        addAttribute(entityData,"creatorcode","创建人编码","c64b768cca1a4bc0b36b136f1b4cfe0b");
//        Element attributeItem2 = entityData.addElement("AttributeItem");
//        attributeItem2.addElement("ItemCode").addText("loginname");
//        attributeItem2.addElement("Value");

        Element attributeItem1 = entityData.addElement("AttributeItem");
        attributeItem1.addElement("ItemCode").addText("columncode");
        attributeItem1.addElement("Value");
        addAttribute(entityData,"columnname","栏目名称","");


        addAttribute(entityData,"name","题名",audioFile.getName_());
        addAttribute(entityData,"createdate","拍摄时间",audioFile.getCreateDate());
        addAttribute(entityData,"camerist","拍摄地点",audioFile.getCamerist());
        addAttribute(entityData,"baodaorenwu","记录人物",audioFile.getBaodaorenwu());
        addAttribute(entityData,"description","描述",audioFile.getDescription());
        addAttribute(entityData,"categorycode","组织结构",audioFile.getCategoryCode());
        addAttribute(entityData,"yewu","业务板块",audioFile.getYewu());
        addAttribute(entityData,"privilege","等级",audioFile.getPrivilege_());
        addAttribute(entityData,"creator","摄像",audioFile.getCreator());
        addAttribute(entityData,"createUser","入库者","张祖新");
        addAttribute(entityData,"createDate","入库时间",audioFile.getCreateDate_());



       return entityData;
    }

    public Element addEntity(PhotoFile photoFile, Element entityData) {
        addAttribute(entityData,"clipguid","素材GUID",photoFile.getGuid());
//        addAttribute(entityData,"name","素材名称",photoFile.getName_());
//        addAttribute(entityData,"clipname","素材名称",photoFile.getName_());
        addAttribute(entityData,"creatorcode","创建人编码","c64b768cca1a4bc0b36b136f1b4cfe0b");
//        Element attributeItem2 = entityData.addElement("AttributeItem");
//        attributeItem2.addElement("ItemCode").addText("loginname");
//        attributeItem2.addElement("Value");

        Element attributeItem1 = entityData.addElement("AttributeItem");
        attributeItem1.addElement("ItemCode").addText("columncode");
        attributeItem1.addElement("Value");
        addAttribute(entityData,"columnname","栏目名称","");



        addAttribute(entityData,"name","图片题名",photoFile.getName_());
        addAttribute(entityData,"createdate","拍摄时间",photoFile.getCreateDate());
        addAttribute(entityData,"camerist","拍摄地点",photoFile.getCamerist());
        addAttribute(entityData,"baodaorenwu","记录人物",photoFile.getBaodaorenwu());
        addAttribute(entityData,"description","内容描述",photoFile.getDescription());
        addAttribute(entityData,"categorycode","组织结构",photoFile.getCategoryCode());
        addAttribute(entityData,"yewu","业务板块",photoFile.getYewu());
        addAttribute(entityData,"privilege","等级",photoFile.getPrivilege_());
        addAttribute(entityData,"keyword","关键字",photoFile.getKeyWord());
        addAttribute(entityData,"creator","拍摄人员",photoFile.getCreator());
        addAttribute(entityData,"createUser","入库者","张祖新");
        addAttribute(entityData,"createDate","入库时间",photoFile.getCreateDate_());
        addAttribute(entityData,"filesize","文件大小",String.valueOf(photoFile.getFileSize()));
        addAttribute(entityData,"fileext","单行文本","JPG");



        return entityData;
    }


    public void createPhotoXMLFile(PhotoFile photoFile, String fileName, int order){
        try {
            File file = new File(fileName);


            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("UnifiedExchangeProtocol");

            Element head = root.addElement("Head");
            head.addElement("TaskGUID").addText(photoFile.getGuid());
            head.addElement("TaskName").addText("宜昌媒资数据导出任务" + order);

            Element contentInfo = root.addElement("ContentInfo");
            contentInfo.addElement("ContentID").addText(photoFile.getGuid());
            contentInfo.addElement("TypeID").addText("picture");
            contentInfo.addElement("TypeName").addText("图片");
            Element contentData = contentInfo.addElement("ContentData");
            Element contentFile = contentData.addElement("ContentFile");
            Element entityData = contentData.addElement("EntityData");

            Element fileItem = contentFile.addElement("FileItem");
            String guid = new GUID().toString().replace("-","");;
            fileItem.addElement("FileGUID").addText(guid);
            Element fileName1 = fileItem.addElement("FileName");
            fileName1.addElement("FullPath").addText(photoFile.getOriginalPath().substring(1).replace("/","\\"));


            //EntityData 的处理部分
            entityData = addEntity(photoFile, entityData);


            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void createAudioXMLFile(AudioFile audioFile, String fileName, int order){
        try {
            File file = new File(fileName);


            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("UnifiedExchangeProtocol");

            Element head = root.addElement("Head");
            head.addElement("TaskGUID").addText(audioFile.getGuid());
            head.addElement("TaskName").addText("宜昌媒资数据导出任务" + order);

            Element contentInfo = root.addElement("ContentInfo");
            contentInfo.addElement("ContentID").addText(audioFile.getGuid());
            contentInfo.addElement("TypeID").addText("Clip");
            contentInfo.addElement("TypeName").addText("视音频");
            Element contentData = contentInfo.addElement("ContentData");
            Element contentFile = contentData.addElement("ContentFile");
            Element entityData = contentData.addElement("EntityData");
//
//            Element fileItem = contentFile.addElement("FileItem");
//            String guid = new GUID().toString().replace("-","");
//            fileItem.addElement("FileGUID").addText(guid);
//            Element fileName1 = fileItem.addElement("FileName");
//            fileName1.addElement("FullPath").addText(audioFile.getStreamMedia().substring(1).replace("/","\\"));

            Element fileItem2 = contentFile.addElement("FileItem");
            String guid2 = new GUID().toString().replace("-","");
            fileItem2.addElement("FileGUID").addText(guid2);
            Element fileName2 = fileItem2.addElement("FileName");
            String fullPath = audioFile.getDigitalMater();
            fileName2.addElement("FullPath").addText(fullPath.substring(1).replace("/","\\"));

            //EntityData 的处理部分
            entityData = addEntity(audioFile, entityData);


            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
