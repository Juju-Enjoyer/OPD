package com.example.opd.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class PhotoController {
    @GetMapping("/static/photoRaven/{imageName:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/photoRaven/" + imageName);
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/jpeg"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }
    @GetMapping("/AllMight/{imageName:.+}")
    public ResponseEntity<byte[]> getImageFinal(@PathVariable String imageName) throws IOException {
        InputStream in = getClass().getResourceAsStream("/templates/" + imageName);
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/jpg"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

}
