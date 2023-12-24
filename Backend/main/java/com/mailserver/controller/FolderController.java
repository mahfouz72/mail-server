package com.mailserver.controller;

import com.mailserver.service.FolderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController {
    private FolderService folderService;
    public FolderController(FolderService folderService){
        this.folderService = folderService;
    }
    @PostMapping("/folder/{email}/{folderName}")
    public void createFolder(@PathVariable  String email, @PathVariable String folderName){
        folderService.createFolder(email,folderName);
    }
    @DeleteMapping("/folder/{email}/{folderName}")
    public void deleteFolder(@PathVariable  String email, @PathVariable String folderName){
        folderService.deleteFolder(email,folderName);
    }
}
