package com.mailserver.service;

import com.mailserver.model.Attachment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AttachmentService {
    private final Map<String, Attachment> db = new HashMap<>();

    public Collection<Attachment> get() {
        return db.values();
    }

    public Attachment get(String id) {
        return db.get(id);
    }

    public Attachment remove(String id) {
        return db.remove(id);
    }

    public Attachment upload(String fileName, String contentType, byte[] data) {
        String id = UUID.randomUUID().toString();
        Attachment attachment = new Attachment(id, fileName, contentType, data);
        db.put(attachment.getId(), attachment);
        return attachment;
    }

    public ResponseEntity<byte[]> download(String id) {
        Attachment attachment = db.get(id);
        if (attachment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = attachment.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(attachment.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(attachment.getFileName())
                .build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
