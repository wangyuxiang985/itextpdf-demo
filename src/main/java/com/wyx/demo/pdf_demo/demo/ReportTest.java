package com.wyx.demo.pdf_demo.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.wyx.demo.pdf_demo.utils.PdfFontUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReportTest
 * @Description TODO
 * @Author yuxiang
 * @Date 2020/3/31
 * @Version 1.0
 **/
public class ReportTest {


    public static void main(String[] args) {
        try {
            Document doc = CreatePdfText.createPdf("C:\\Users\\wb-wyx657769\\Desktop\\pdftest\\test_report.pdf");
            //生成  合同文件
            createFile(doc);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFile(Document doc) throws Exception {
        //页面大小
        doc.add(PdfFontUtils.getFont(1, "数据分析报告"));
        doc.add(PdfFontUtils.getFont(6, "姓名：" + "张三"));
        doc.add(PdfFontUtils.getFont(6, "单位：" + "才华有限公司"));
        doc.add(PdfFontUtils.getFont(6, "ID：" + "fafadf-adfad-adfasd-adfasdf"));
        doc.add(PdfFontUtils.getFont(6, "日期：" + "2020-03-31"));
        doc.add(PdfFontUtils.getFont(6, "\n"));

//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);// 五号
        Paragraph ret = new Paragraph(PdfFontUtils.getFont(6,"一、数据统计"));
        PdfPTable tableBox = new PdfPTable(8);
        tableBox.setWidths(new float[] { 0.2f, 0.125f, 0.125f,0.125f,0.125f,0.125f,0.1f,0.1f });
        tableBox.setWidthPercentage(80f);
        //获取txt文本集合

        // 创建表格格式及内容
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"分析结果")), false, 8, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Sample")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Raw_Reads")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Raw_Bases")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Valid_Reads")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Valid_Bases")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Valid%")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Q20%")), false, 1, 1));
        tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Q30%")), false, 1, 1));
        // 遍历查询出的结果

        //获取txt文本集合
        List<Txt> txts = new ArrayList<Txt>() {{
            this.add(new Txt(1L, 1L, "PATH", "TXTpATH", "样本名1", 20000L, 18000L, 290139L));
            this.add(new Txt(2L, 2L, "PATH1", "TXTpATH1", "样本名2", 20000L, 18000L, 290139L));
            this.add(new Txt(3L, 3L, "PATH2", "TXTpATH2", "样本名3", 20000L, 18000L, 290139L));
        }};
        for (Txt txt : txts) {
            tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,txt.getSampleName())), false, 1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(PdfFontUtils.getFont(5, "" + txt.getAllReadsNum()))), false, 1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(PdfFontUtils.getFont(5, "" + txt.getMatchNum()))), false, 1, 1));
        }

        for (int i = 0; i < 20; i++) {
            for (Txt txt : txts) {
                tableBox.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,txt.getSampleName())), false, 1, 1));
                tableBox.addCell(getCell(new Phrase(String.valueOf(PdfFontUtils.getFont(5, "" + txt.getAllReadsNum()))), false, 1, 1));
                tableBox.addCell(getCell(new Phrase(String.valueOf(PdfFontUtils.getFont(5, "" + txt.getMatchNum()))), false, 1, 1));
            }
        }

        ret.add(tableBox);
        doc.add(ret);

        //表格完成
        doc.add(PdfFontUtils.getFont(6, "参数说明："));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "\n"));

        //生成第二个表格 基因拼接
        Paragraph ret2 = new Paragraph(PdfFontUtils.getFont(6,"二、分析结果"));
        PdfPTable tableBox2 = new PdfPTable(8);
        tableBox2.setWidths(new float[]{0.2f,0.1f,0.1f, 0.2f, 0.1f,0.1f,0.1f, 0.2f});
        tableBox2.setWidthPercentage(80f);

        List<Asem> asems = new ArrayList<Asem>() {{
            this.add(new Asem(1L, 1L, "PATH", "TXTpATH", "样本名1", "99.9%", "383838", "88%", "asemFaName", "", 222L));
            this.add(new Asem(2L, 2L, "PATH1", "TXTpATH1", "样本名2", "99.99%", "383838", "88%", "asemFaName", "", 222L));
            this.add(new Asem(3L, 3L, "PATH2", "TXTpATH2", "样本名3", "99.97%", "383838", "88%", "asemFaName", "", 222L));
        }};
        // 创建表格格式及内容
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"病毒分析结果")), false, 8, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Sample")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Coverage%")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Length")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Similarity%")), false, 1, 1));
        tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,"Similarity%")), false, 1, 1));

        for (Asem asem : asems) {
            tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,asem.getSampleName())), false, 1, 1));
            tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,asem.getGenomeCoverage())), false, 1, 1));
            tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,asem.getSequenceLength())), false, 1, 1));
            tableBox2.addCell(getCell(new Phrase(PdfFontUtils.getFont(5,asem.getGeneSimilarity())), false, 1, 1));
        }
        ret2.add(tableBox2);
        doc.add(ret2);

        //第二个表格完成
        doc.add(PdfFontUtils.getFont(6, "参数说明："));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "Sample：测序样本名"));
        doc.add(PdfFontUtils.getFont(6, "\n"));

        //第三部分
        doc.add(PdfFontUtils.getFont(6, "三、建议结论"));
        doc.add(PdfFontUtils.getFont(6, "奋斗吧骚年"));




    }

    //每个cell的格式，合并单元格情况
    private static PdfPCell getCell(Phrase phrase, boolean yellowFlag, int colSpan, int rowSpan) {
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

class Asem {

    private Long id;

    /**
     * task表的主键
     */
    private Long taskId;

    /**
     * 数据路径
     */
    private String sequencerPath;

    /**
     * 文件路径
     */
    private String txtPath;

    /**
     * 样本名称
     */
    private String sampleName;

    /**
     * 基因组覆盖度
     */
    private String genomeCoverage;
    /**
     *拼接的序列长度
     */
    private String sequenceLength;

    /**
     * 基因相似度
     */
    private String geneSimilarity;

    /**
     * 文件名1-B01-20200207.B01.clean.virus.final.fa
     */
    private String asemFaName;

    /**
     * 目标final.fa转成json格式数据
     */
    private String asemFaJson;

    /**
     * 创建时间
     */
    private Long createTime;

    public Asem() {
    }

    public Asem(Long id, Long taskId, String sequencerPath, String txtPath, String sampleName, String genomeCoverage, String sequenceLength, String geneSimilarity, String asemFaName, String asemFaJson, Long createTime) {
        this.id = id;
        this.taskId = taskId;
        this.sequencerPath = sequencerPath;
        this.txtPath = txtPath;
        this.sampleName = sampleName;
        this.genomeCoverage = genomeCoverage;
        this.sequenceLength = sequenceLength;
        this.geneSimilarity = geneSimilarity;
        this.asemFaName = asemFaName;
        this.asemFaJson = asemFaJson;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getSequencerPath() {
        return sequencerPath;
    }

    public void setSequencerPath(String sequencerPath) {
        this.sequencerPath = sequencerPath;
    }

    public String getTxtPath() {
        return txtPath;
    }

    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getGenomeCoverage() {
        return genomeCoverage;
    }

    public void setGenomeCoverage(String genomeCoverage) {
        this.genomeCoverage = genomeCoverage;
    }

    public String getSequenceLength() {
        return sequenceLength;
    }

    public void setSequenceLength(String sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    public String getGeneSimilarity() {
        return geneSimilarity;
    }

    public void setGeneSimilarity(String geneSimilarity) {
        this.geneSimilarity = geneSimilarity;
    }

    public String getAsemFaName() {
        return asemFaName;
    }

    public void setAsemFaName(String asemFaName) {
        this.asemFaName = asemFaName;
    }

    public String getAsemFaJson() {
        return asemFaJson;
    }

    public void setAsemFaJson(String asemFaJson) {
        this.asemFaJson = asemFaJson;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

class Txt {
    /**
     * 主键
     */
    private Long id;

    /**
     * 主键
     */
    private Long taskId;

    /**
     * 路径
     */
    private String sequencerPath;

    /**
     * txt文本路径
     */
    private String txtPath;

    /**
     * 样本名称
     */
    private String sampleName;

    /**
     * 总reads数量
     */
    private Long allReadsNum;

    /**
     * 匹配到基因组的数量
     */
    private Long matchNum;

    /**
     * 创建时间
     */
    private Long createTime;
    public Txt() {
    }

    public Txt(Long id, Long taskId, String sequencerPath, String txtPath, String sampleName, Long allReadsNum, Long matchNum, Long createTime) {
        this.id = id;
        this.taskId = taskId;
        this.sequencerPath = sequencerPath;
        this.txtPath = txtPath;
        this.sampleName = sampleName;
        this.allReadsNum = allReadsNum;
        this.matchNum = matchNum;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getSequencerPath() {
        return sequencerPath;
    }

    public void setSequencerPath(String sequencerPath) {
        this.sequencerPath = sequencerPath;
    }

    public String getTxtPath() {
        return txtPath;
    }

    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public Long getAllReadsNum() {
        return allReadsNum;
    }

    public void setAllReadsNum(Long allReadsNum) {
        this.allReadsNum = allReadsNum;
    }

    public Long getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(Long matchNum) {
        this.matchNum = matchNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
