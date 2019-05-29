package com.dmma.diploma.pdf_builder;


import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportPdfDocumentBuilder {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UnsuccessfulLessonRepository unsuccessfulLessonRepository;

    public ByteArrayInputStream createTeacherReport(Teacher teacher) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            BaseFont helvetica = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 3});

            Font headFont = new Font(helvetica, 12, Font.BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Аудитория", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Предмет", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Дата", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Номер занятия", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            List<Lesson> lessons = lessonRepository.findByTeacher(teacher);
            int unsuccessfulLessonCount = 0;
            for (Lesson lesson : lessons) {
                System.out.println("lesson: " + lesson);
                UnsuccessfulLesson unsuccessfulLesson = unsuccessfulLessonRepository.findByLesson(lesson);
                System.out.println("unsuccessfulLesson: " + unsuccessfulLesson);

                if (unsuccessfulLesson != null) {
                    unsuccessfulLessonCount++;
                    PdfPCell cell;

//                Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//                PDPageContentStream contentStream = new PDPageContentStream(document, page);
//                PDImageXObject image
//                        = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);
//                contentStream.drawImage(image, 0, 0);
//
                    cell = new PdfPCell(new Phrase(lesson.getClassRoom().getBody() + "/" + lesson.getClassRoom().getNumber()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(lesson.getDiscipline().getName()));
                    cell.setPaddingLeft(5);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    String date = unsuccessfulLesson.getImage().get(0).substring(0,10);
                    cell = new PdfPCell(new Phrase(String.valueOf(date)));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setPaddingRight(5);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.valueOf(lesson.getLessonNumber())));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }
            }

            PdfWriter.getInstance(document, out);
            Paragraph head = new Paragraph("Отчет о непроведеных занятиях", new Font(helvetica, 14, Font.BOLD));
            head.setAlignment(Element.ALIGN_CENTER);
            document.open();

            document.add(head);
            document.add(new Paragraph("Непроведеные занятия для преподавателя: " + teacher.getSurname() + " " + teacher.getName() + " " + teacher.getLastname(), headFont));
            document.add(new Paragraph("Всего занятий: " + lessons.size(), headFont));
            document.add(new Paragraph("Непроведеных занятий: " + unsuccessfulLessonCount, headFont));
            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

        } catch (DocumentException | IOException ex) {
            System.out.println(ex);
            System.out.println(ex.getStackTrace());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


    public final static String COMMON_PATH = "D:/diploma/diploma";

    public Document createNewDocument(Lesson lesson) throws FileNotFoundException, DocumentException, com.itextpdf.text.DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(COMMON_PATH + lesson.getId() + ".pdf"));

        document.open();
        document.addTitle(lesson.getId().toString());

        return document;
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
