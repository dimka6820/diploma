package com.dmma.diploma.controller;

import com.dmma.diploma.common.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;

@Controller
public class ImageController {

    @RequestMapping("/cover/{bookID}")
    public ResponseEntity<byte[]> testphoto(@PathVariable(value = "bookID") String bookId) throws IOException {
        System.out.println("bookId: " + bookId);
        String newBookId = Constants.FOLDER + bookId + Constants.POSTFIX;
        System.out.println("newBookId: " + newBookId);
        ByteArrayOutputStream out = null;
        InputStream input = null;
        try {
            out = new ByteArrayOutputStream();
            input = new BufferedInputStream(new FileInputStream(newBookId));
            int data = 0;
            while ((data = input.read()) != -1) {
                out.write(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != input) {
                input.close();
            }
            if (null != out) {
                out.close();
            }
        }
        byte[] bytes = out.toByteArray();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }


}
