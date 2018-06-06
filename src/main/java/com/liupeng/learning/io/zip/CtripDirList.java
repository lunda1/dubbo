package com.liupeng.learning.io.zip;

import java.util.List;

public class CtripDirList {
    private String currentDir;

    private List<CtripFtpFile> files;

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    public List<CtripFtpFile> getFiles() {
        return files;
    }

    public void setFiles(List<CtripFtpFile> files) {
        this.files = files;
    }
}

class CtripFtpFile {
    private String name;
    private String type;
    private Long size;
    private String user;
    private Long date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
