package com.czx.bookproject.Bean;

import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Data
public class Music {
    private Integer id;
    private String name;
    private String author;
    private String imgPath;
    private String audioPath;

}
