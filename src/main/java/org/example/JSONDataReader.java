package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.EmployeeData;
import org.example.model.Report;
import org.example.model.ReportDefinition;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONDataReader {

    public void readFileFromCommandLine() {

        try {
            /*
            Here is the path to the file, you just need to write the exact name of the file.
            With Path interface we get the file with the extensions and work on its content.
            Try to type right the File names, because if u don't write exact you will get an IOException
            for the methods readDataFile and readReportDefinitionFile
            This applies to both piece of lines:
            -First from  String dataFileName to List<EmployeeData> data
            -Second from String reportDefinitionFile to ReportDefinition reportDefinition
            */
            Scanner sc = new Scanner(System.in);

            //Change directories with your
            String dataFileName = "C:\\Users\\Seyhan\\IdeaProjects\\Mentormate_task\\src\\main\\resources\\" + sc.nextLine() + ".json";
            Path pathToFile = Paths.get(dataFileName);
            List<EmployeeData> data = readDataFile(pathToFile);

            //Change directories with your
            String reportDefinitionFile = "C:\\Users\\Seyhan\\IdeaProjects\\Mentormate_task\\src\\main\\resources\\" + sc.nextLine() + ".json";
            Path pathToFile1 = Paths.get(reportDefinitionFile);
            ReportDefinition reportDefinition = readReportDefinitionFile(pathToFile1);

            //Here we create list of reports
            List<Report> reports = createReport(data, reportDefinition);

            /*
             With this class bellow we create out csv File located in resources directory,
             where we generate it after some calculations
             Change directory with your to choose where to be located(created) your report file ("csv")
             */
            FileWriter csvWriter = new FileWriter("C:\\Users\\Seyhan\\IdeaProjects\\Mentormate_task\\src\\main\\resources\\reports.csv");
            csvWriter.append("Name");
            csvWriter.append("\t\t\t\t");
            csvWriter.append("Score");
            csvWriter.append("\n");

            for (Report row : reports) {
                csvWriter.append(row.getName());
                csvWriter.append(row.getScore().toString());
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //In this method we read the File and objects in the file for later calculations with the values in that file
    private List<EmployeeData> readDataFile(Path filePath) throws IOException {
        //Spell the name of file exact to not get any Exception
        InputStream input = Files.newInputStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<EmployeeData> list = mapper.readValue(input, new TypeReference<List<EmployeeData>>() {
        });
        return list;
    }

    //In this method we read the File and objects in the file for later calculations with the values in that file
    private ReportDefinition readReportDefinitionFile(Path filePath) throws IOException {
        //Spell the name of file exact to not get any Exception
        InputStream input = Files.newInputStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        ReportDefinition reportDefinition = mapper.readValue(input, ReportDefinition.class);
        return reportDefinition;
    }

    //Here we create list of reports for Employees and add it to a list depend on their performance
    private List<Report> createReport(List<EmployeeData> EmplData, ReportDefinition reportDefinition) {
        List<Report> reports = new ArrayList<>();
        for (EmployeeData data : EmplData) {
            if (data.getSalesPeriod() <= reportDefinition.getPeriodLimit()) {
                double score = calculateScore(data, reportDefinition.isUseExprienceMultiplier());
                if (score > reportDefinition.getTopPerformersThreshold()) {
                    Report report = new Report();
                    report.setName(data.getName());
                    report.setScore(score);
                    reports.add(report);
                }
            }
        }
        return reports;
    }

    //This is the calculation method for useExprienceMultiplier
    //it depends on the score of the EmployeeData -> useExprienceMultiplier - status -> true or false
    private Double calculateScore(EmployeeData employeeData, boolean useExprienceMultiplier) {
        double score = 0;
        if (useExprienceMultiplier) {
            score = employeeData.getTotalSales() / employeeData.getSalesPeriod();
        } else {
            score = (employeeData.getTotalSales() / employeeData.getSalesPeriod()) * employeeData.getExperienceMultiplier();
        }
        return score;
    }
    /*
    * I think all is explained well to work with the project without errors
    * If you get some difficulties
    * Please contact with me Seyhan
    */
}

