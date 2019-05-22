package com.dmma.diploma.pdf_builder;


import com.dmma.diploma.model.Lesson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class ReportPdfDocumentBuilder {

    public final static String COMMON_PATH = "D:/diploma/diploma";

    public Document createNewDocument(Lesson lesson) throws FileNotFoundException, DocumentException, com.itextpdf.text.DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(COMMON_PATH + lesson.getId() + ".pdf"));

        document.open();
        document.addTitle(lesson.getId().toString());

        return document;
    }

    public static ByteArrayInputStream citiesReport(List<Lesson> lessons) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Population", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Lesson city : lessons) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(city.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(city.getId()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(city.getClassRoom())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            System.out.println(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public Document createListHeader(Lesson lesson, Document document) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        BaseFont helvetica = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(helvetica, 11, Font.NORMAL);
        Font fontBold = new Font(helvetica, 11, Font.BOLD);

        document.add(new Paragraph("Лист № " + lesson.getClassRoom(), fontBold));
        document.add(new Paragraph("Описание : " + lesson.getLessonNumber(), font));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Нарушения: ", fontBold));
        document.add(Chunk.NEWLINE);
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        table.setTableEvent(new BorderEvent());

//        for (com.springboot.model.PointExemplar pointEx : pointWithAnswers) {
//            if(CollectionUtils.isNotEmpty(pointEx.getChildrenPoints())){
//                for (com.springboot.model.PointExemplar pointExemplar: pointEx.getChildrenPoints()) {
//                    if("Нет".equals(pointExemplar.getAnswer())){
//                        createCellForPoint(table, pointExemplar.getPointTemplate(), font, fontBold);
//                    }
//                }
//            } else {
//                createCellForPoint(table, pointEx.getPointTemplate(), font, fontBold);
//            }
//        }
        document.add(table);
        document.newPage();
        return document;
    }

//    private static void createCellForPoint(PdfPTable table, PointTemplate pointTemplate, Font font, Font fontBold){
//        PdfPCell cell = new PdfPCell();
//        String description = pointTemplate.getDescription();
//        String act = pointTemplate.getAct();
//        if(pointTemplate.getParentPoint() != null){
//            description = pointTemplate.getParentPoint().getDescription() + " " + description;
//            act = pointTemplate.getParentPoint().getAct() + " " + act;
//        }
//        addNewParagraph("Пункт ", pointTemplate.getName(), cell, font, fontBold);
//        addNewParagraph("Описание : ", description, cell, font, fontBold);
//        addNewParagraph("Нормативно правовой акт : ", act, cell, font, fontBold);
//        addNewParagraph("Штраф : ", pointTemplate.getFine().toString(), cell, font, fontBold);
//        table.addCell(cell);
//    }

    private static void addNewParagraph(String header, String text, PdfPCell cell, Font font, Font fontBold) {
        Paragraph paragraph = new Paragraph();
        Chunk newHeader = new Chunk(header, fontBold);
        Chunk newText = new Chunk(text, font);
        paragraph.add(newHeader);
        paragraph.add(newText);
        cell.addElement(paragraph);
        cell.addElement(Chunk.NEWLINE);
    }


    public void finishDocument(Document document) {
        document.close();
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        document.close();
    }

    public static class BorderEvent implements PdfPTableEvent {
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            float width[] = widths[0];
            float x1 = width[0];
            float x2 = width[width.length - 1];
            float y1 = heights[0];
            float y2 = heights[heights.length - 1];
            PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
            cb.rectangle(x1, y1, x2 - x1, y2 - y1);
            cb.stroke();
        }
    }

}
