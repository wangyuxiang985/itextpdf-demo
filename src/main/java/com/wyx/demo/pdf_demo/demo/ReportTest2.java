package com.wyx.demo.pdf_demo.demo;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.wyx.demo.pdf_demo.utils.PdfFontUtil2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReportTest2
 * @Description 利用itext2.1.7版本生成pdf
 * @Author yuxiang
 * @Date 2020/4/14
 * @Version 1.0
 **/
public class ReportTest2 {
    public static void main(String[] args) {
        try {
            Document doc = PdfFontUtil2.createPdf("C:\\Users\\wb-wyx657769\\Desktop\\pdftest\\test_report.pdf");
            //生成  合同文件
            createFile(doc);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFile(Document doc) throws Exception {
        //页面大小
        doc.add(PdfFontUtil2.getFont(1, "数据分析报告"));
        doc.add(PdfFontUtil2.getFont(6, "名字：" + "张三"));
        doc.add(PdfFontUtil2.getFont(6, "单位：" + "才华有限公司"));
        doc.add(PdfFontUtil2.getFont(6, "ID：" + "fafadf-adfad-adfasd-adfasdf"));
        doc.add(PdfFontUtil2.getFont(6, "生成时间：" + "2020-03-31"));
        doc.add(PdfFontUtil2.getFont(6, "\n"));

//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);// 五号
        Paragraph ret = new Paragraph(PdfFontUtil2.getFont(6,"一、数据统计"));
        PdfPTable tableBox = new PdfPTable(8);
        tableBox.setWidths(new float[] { 0.2f, 0.125f, 0.125f,0.125f,0.125f,0.125f,0.1f,0.1f });
        tableBox.setWidthPercentage(90f);
        //获取txt文本集合

        // 创建表格格式及内容
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"分析结果")), false, 8, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Sample")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Raw_Reads")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Raw_Bases")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Valid_Reads")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Valid_Bases")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Valid%")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Q20%")), false, 1, 1));
        tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Q30%")), false, 1, 1));
        // 遍历查询出的结果

        //获取txt文本集合
        List<Txt> txts = new ArrayList<Txt>() {{
            this.add(new Txt(1L, 1L, "PATH", "TXTpATH", "样本名1", 20000L, 18000L, 290139L));
            this.add(new Txt(2L, 2L, "PATH1", "TXTpATH1", "样本名2", 20000L, 18000L, 290139L));
            this.add(new Txt(3L, 3L, "PATH2", "TXTpATH2", "样本名3", 20000L, 18000L, 290139L));
        }};
        for (Txt txt : txts) {
            tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,txt.getSampleName())), false, 1, 1));
            tableBox.addCell(PdfFontUtil2.getCell(new Phrase(String.valueOf(PdfFontUtil2.getFont(5, "" + txt.getAllReadsNum()))), false, 1, 1));
            tableBox.addCell(PdfFontUtil2.getCell(new Phrase(String.valueOf(PdfFontUtil2.getFont(5, "" + txt.getMatchNum()))), false, 1, 1));
        }
        //测试分页效果
        for (int i = 0; i < 20; i++) {
            for (Txt txt : txts) {
                tableBox.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,txt.getSampleName())), false, 1, 1));
                tableBox.addCell(PdfFontUtil2.getCell(new Phrase(String.valueOf(PdfFontUtil2.getFont(5, "" + txt.getAllReadsNum()))), false, 1, 1));
                tableBox.addCell(PdfFontUtil2.getCell(new Phrase(String.valueOf(PdfFontUtil2.getFont(5, "" + txt.getMatchNum()))), false, 1, 1));
            }
        }

        ret.add(tableBox);
        doc.add(ret);

        //表格完成
        doc.add(PdfFontUtil2.getFont(6, "参数说明："));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "\n"));

        //生成第二个表格 基因拼接
        Paragraph ret2 = new Paragraph(PdfFontUtil2.getFont(6,"二、分析结果"));
        PdfPTable tableBox2 = new PdfPTable(8);
        tableBox2.setWidths(new float[]{0.2f,0.1f,0.1f, 0.2f, 0.1f,0.1f,0.1f, 0.2f});
        tableBox2.setWidthPercentage(100f);

        List<Asem> asems = new ArrayList<Asem>() {{
            this.add(new Asem(1L, 1L, "PATH", "TXTpATH", "样本名1", "99.9%", "383838", "88%", "asemFaName", "", 222L));
            this.add(new Asem(2L, 2L, "PATH1", "TXTpATH1", "样本名2", "99.99%", "383838", "88%", "asemFaName", "", 222L));
            this.add(new Asem(3L, 3L, "PATH2", "TXTpATH2", "样本名3", "99.97%", "383838", "88%", "asemFaName", "", 222L));
        }};
        // 创建表格格式及内容
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"分析结果")), false, 8, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Sample")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Coverage%")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Length")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,"Similarity%")), false, 1, 1));

        for (Asem asem : asems) {
            tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,asem.getSampleName())), false, 1, 1));
            tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,asem.getGenomeCoverage())), false, 1, 1));
            tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,asem.getSequenceLength())), false, 1, 1));
            tableBox2.addCell(PdfFontUtil2.getCell(new Phrase(PdfFontUtil2.getFont(5,asem.getGeneSimilarity())), false, 1, 1));
        }
        ret2.add(tableBox2);
        doc.add(ret2);

        //第二个表格完成
        doc.add(PdfFontUtil2.getFont(6, "参数说明："));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtil2.getFont(6, "\n"));

        //第三部分
        doc.add(PdfFontUtil2.getFont(6, "三、建议结论"));
        doc.add(PdfFontUtil2.getFont(6, "深入 学习"));

    }
}
