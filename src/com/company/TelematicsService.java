package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class TelematicsService {
    static void report(VehicleInfo vehicleInfo) {

        // Creates mapper object to be used for going from Java to JSON and vice-versa

        ObjectMapper mapper = new ObjectMapper();

        String json;
        try {
            File newJson = new File(vehicleInfo.getVIN() + ".json");
            json = mapper.writeValueAsString(vehicleInfo);
            FileWriter createFile = new FileWriter(newJson);
            createFile.write(json);
            createFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads the JSON files and allows for an average to be found for the values

        double odometerTotal = 0;
        double consumptionTotal = 0;
        double oilChangeTotal = 0;
        double engineSizeTotal = 0;
        int totalVehicles = 0;

        File file = new File(".");

        // Read the JSON files and get the totals
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;
                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    odometerTotal += vi.getOdometer();
                    consumptionTotal += vi.getConsumption();
                    oilChangeTotal += vi.getLastOilChangeMiles();
                    engineSizeTotal += vi.getEngineSize();
                    totalVehicles++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        double odometerAvg = Math.round((odometerTotal / totalVehicles) * 10.0) / 10.0;
        double consumptionAvg = Math.round((consumptionTotal / totalVehicles) * 10.0) / 10.0;
        double oilChangeAvg = Math.round((oilChangeTotal / totalVehicles) * 10.0) / 10.0;
        double engineAvg = Math.round((engineSizeTotal / totalVehicles) * 10.0) / 10.0;

        // html string variable
        String htmlString = "<html>\n" +
                "<title>Vehicle Telematics Dashboard</title>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for " + totalVehicles + " vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">" + odometerAvg + "</td><td align=\"center\">" + consumptionAvg + "</td><td align=\"center\">" + oilChangeAvg + "</td align=\"center\"><td align=\"center\">" + engineAvg + "</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<h1 align=\"center\">History</h1>\n" +
                "<table align=\"center\" border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n";


        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;
                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    htmlString += "    <tr>\n" +
                            "        <td align=\"center\">" + vi.getVIN() + "</td><td align=\"center\">" + vi.getOdometer() + "</td><td align=\"center\">" + vi.getConsumption() + "</td><td align=\"center\">"+ vi.getLastOilChangeMiles() + "</td align=\"center\"><td align=\"center\">" + vi.getEngineSize() + "</td>\n" +
                            "    </tr>\n";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            File newHtml = new File("dashboard.html");
            FileWriter createHtml = new FileWriter(newHtml);
            htmlString += "</table>\n" +
                    "</body>\n" +
                    "</html>";
            createHtml.write(htmlString);
            createHtml.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
