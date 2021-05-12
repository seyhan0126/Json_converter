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

//from data  class
public class JSONDataReader {

    public void readFileFromCommandLine()
//            throws IOException
    {
        try{
        Scanner sc = new Scanner(System.in);
        String dataFileName = "C:\\Users\\Seyhan\\IdeaProjects\\Mentormate_task\\src\\main\\resources\\" + sc.nextLine() + ".json";
        Path pathToFile = Paths.get(dataFileName);
        List<EmployeeData> data = readDataFile(pathToFile);

        String reportDefinitionFile = "C:\\Users\\Seyhan\\IdeaProjects\\Mentormate_task\\src\\main\\resources\\" + sc.nextLine() + ".json";
        Path pathToFile1 = Paths.get(reportDefinitionFile);
        ReportDefinition reportDefinition = readReportDefinitionFile(pathToFile1);

        List<Report> reports = createReport(data, reportDefinition);

        FileWriter csvWriter = new FileWriter("reports.csv");
        csvWriter.append("Name");
        csvWriter.append("\t\t\t\t");
        csvWriter.append("Score");
        csvWriter.append("\n");



        for (Report row : reports) {
            csvWriter.append(row.getName());
            csvWriter.append("\t\t\t\t");
            csvWriter.append(row.getScore().toString());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        }catch (Exception e){
         e.printStackTrace();
        }

    }

    private List<EmployeeData> readDataFile(Path filePath)
            throws IOException
    {
        InputStream input = Files.newInputStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<EmployeeData> list = mapper.readValue(input, new TypeReference<List<EmployeeData>>() {
        });
        return list;
    }

    private ReportDefinition readReportDefinitionFile(Path filePath) throws IOException {
        InputStream input = Files.newInputStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        ReportDefinition reportDefinition = mapper.readValue(input, ReportDefinition.class);
        return reportDefinition;
    }

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

    private Double calculateScore(EmployeeData employeeData, boolean useExprienceMultiplier) {
        double score = 0;
        if (useExprienceMultiplier) {
            score = employeeData.getTotalSales() / employeeData.getSalesPeriod();
        } else {
            score = (employeeData.getTotalSales() / employeeData.getSalesPeriod()) * employeeData.getExperienceMultiplier();
        }
        return score;
    }
}

