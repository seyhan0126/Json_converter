package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.parser.*;

import java.io.FileReader;
import java.util.Iterator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        try{
            JSONDataReader jsonDataReader = new JSONDataReader();
            jsonDataReader.readFileFromCommandLine();
        } catch (Exception e){e.printStackTrace();}

    }
}
