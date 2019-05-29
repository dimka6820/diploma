package com.dmma.diploma.controller.report;

import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.pdf_builder.ReportPdfDocumentBuilder;
import com.dmma.diploma.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class PdfReportController {
    @Autowired
    private ReportPdfDocumentBuilder reportPdfDocumentBuilder;
    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/createPdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> teacherReport(@RequestParam Long teacherId) throws IOException {
        Teacher teacher = teacherRepository.findOne(teacherId);


        ByteArrayInputStream bis = reportPdfDocumentBuilder.createTeacherReport(teacher);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=teacherReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

//    @RequestMapping(value = "/qwert", method = RequestMethod.GET)
//    public String qwerty(ModelMap model) {
//        try {
//            Lesson lesson = lessonRepository.findAll().get(0);
//            Document newDocument = reportPdfDocumentBuilder.createNewDocument(lesson);
//            Document listHeader = reportPdfDocumentBuilder.createListHeader(lesson, newDocument);
//            reportPdfDocumentBuilder.finishDocument(listHeader);
//
//        } catch (IOException | DocumentException e) {
//            System.out.println(e);
//        }
//
//        return "welcome";
//    }
}
