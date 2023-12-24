package com.mailserver.controller;

import com.mailserver.service.AttachmentService;
import com.mailserver.model.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/attachments")
    public Collection<Attachment> getAll(){
        return attachmentService.get();
    }

    @PostMapping("/attach")
    public Attachment attach(@RequestPart("data") MultipartFile file) throws IOException {
        return attachmentService.upload(file.getOriginalFilename(),file.getContentType() ,file.getBytes());
    }

    @DeleteMapping("/detach/{id}")
    public void detach(@PathVariable String id){
        Attachment attachment = attachmentService.remove(id);
        if(attachment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){
        return attachmentService.download(id);
    }

}
