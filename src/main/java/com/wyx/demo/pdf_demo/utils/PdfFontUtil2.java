package com.wyx.demo.pdf_demo.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName PdfFontUtil2
 * @Description 利用itextpdf2.1.7版本生成pdf
 * @Author yuxiang
 * @Date 2020/4/14
 * @Version 1.0
 **/
public class PdfFontUtil2 {
    // 字体
    private static BaseFont baseFont = null;

    static{
        try {
            /**
             * 设置字体
             *
             * windows路径字体
             * FONT_TYPE=C:/Windows/fonts/simsun.ttc
             * linux路径字体 宋体 (如果没有这个字体文件，就将windows的字体传上去)
             * FONT_TYPE=/usr/share/fonts/win/simsun.ttc
             */
            //可以用配置文件读取
            //获取配置
            //PropertiesLoader pl = new PropertiesLoader("/config/config.properties");
            //拼接文件web访问路径
            //String FONT_TYPE = pl.getProperty("FONT_TYPE");
            //解决中文问题  幼圆
            String path = "C:\\Windows\\Fonts\\simsun.ttc";//使用win字体 simsun.ttc
            path =  "/fonts/simsun.ttc";
            baseFont = BaseFont.createFont(path + ",1" , BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            baseFont = BaseFont.createFont("com/wyx/demo/pdf_demo/demo/simsunb.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文档超级  排版
     * @param type 1-标题 2-标题一  3-标题二 4-标题三  5-正文  6-左对齐
     */
    public static Paragraph getFont(int type, String text){
        Font font = new Font(baseFont);
        if(1 == type){//1-标题
            font.setSize(16f);
            font.setStyle(Font.BOLD);
        } else if(2 == type){//2-标题一
            font.setSize(16f);
            font.setStyle(Font.BOLD);
        } else if(3 == type){//3-标题二
            font.setSize(14f);
            font.setStyle(Font.BOLD);
        } else if(4 == type){//4-标题三
            font.setSize(14f);
        } else if(5 == type){//5-正文
//            font.setSize(10.5f);
            font.setSize(8f);
        } else if(6 == type){//6-左对齐
            font.setSize(10.5f);
        } else {
            font.setSize(10.5f);//默认大小
        }
        //注： 字体必须和 文字一起new
        Paragraph paragraph = new Paragraph(text, font);
        if(1 == type){
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);//居中
            paragraph.setSpacingBefore(10f);//上间距
            paragraph.setSpacingAfter(10f);//下间距
        } else if(2 == type){//2-标题一
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED); //默认
            paragraph.setSpacingBefore(2f);//上间距
            paragraph.setSpacingAfter(2f);//下间距
        } else if(3 == type){
            paragraph.setSpacingBefore(2f);//上间距
            paragraph.setSpacingAfter(1f);//下间距
        } else if(4 == type){//4-标题三
            //paragraph.setAlignment(Element.ALIGN_RIGHT);//右对齐
            paragraph.setSpacingBefore(2f);//上间距
            paragraph.setSpacingAfter(2f);//下间距
        } else if(5 == type){
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setFirstLineIndent(24);//首行缩进
            paragraph.setSpacingBefore(1f);//上间距
            paragraph.setSpacingAfter(1f);//下间距
        } else if(6 == type){//左对齐
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setSpacingBefore(1f);//上间距
            paragraph.setSpacingAfter(1f);//下间距
        }
        //paragraph.setIndentationLeft(50);//整体缩进左边
        //paragraph.setFirstLineIndent(40);//首行缩进
        return paragraph;
    }

    /**
     * 创建一个pdf并打开
     * @param outpath  pdf路径
     */
    public static Document createPdf(String outpath) throws DocumentException, IOException {
        //页面大小
        //Rectangle rect = new Rectangle(PageSize.A4.rotate());//文档横方向
        Rectangle rect = new Rectangle(PageSize.A4);//文档竖方向
        //如果没有则创建
        File saveDir = new File(outpath);
        File dir = saveDir.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Document doc = new Document(rect);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outpath));
        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        //文档属性
        doc.addTitle("Title@wpixel");
        doc.addAuthor("Author@wpixel");
        doc.addSubject("Subject@wpixel");
        doc.addKeywords("Keywords@wpixel");
        doc.addCreator("Creator@wpixel");
        //页边空白
        doc.setMargins(20, 20, 20, 20);
        //打开文档
        doc.open();
        return doc;
    }

    //每个cell的格式，合并单元格情况
    public static PdfPCell getCell(Phrase phrase, boolean yellowFlag, int colSpan, int rowSpan) {
        PdfPCell cells = new PdfPCell(phrase);
        cells.setUseAscender(true);
        cells.setMinimumHeight(20f);
        cells.setHorizontalAlignment(1);
        cells.setVerticalAlignment(5);
        cells.setColspan(colSpan);
        cells.setRowspan(rowSpan);
        cells.setNoWrap(false);
        return cells;
    }

}
