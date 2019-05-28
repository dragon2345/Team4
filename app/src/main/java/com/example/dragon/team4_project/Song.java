package com.example.dragon.team4_project;

public class Song {
    private String Title;
    private String File;

    public Song(String title, String file){
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }
}
