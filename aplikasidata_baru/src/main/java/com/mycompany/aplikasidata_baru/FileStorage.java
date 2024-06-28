/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasidata_baru;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author User
 */
public class FileStorage implements DataStorage{
    private String filepath;

    public FileStorage(String filepath) {
        this.filepath = filepath;
    }
    
    @Override
    public void writeData(String data) {
        try(FileWriter writer = new FileWriter(filepath)) {
            writer.write(data);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
}