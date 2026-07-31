package com.hospital.main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.hospital.service.HospitalDataManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HospitalDataManager manager = new HospitalDataManager();
        manager.loadAllDataConcurrently("1_Patient.csv", "2_Doctor.csv", "3_Appoinment.csv", "4_Room.csv");

        // Simple built-in Java Web Server running on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "<html><head><style>" +
                        "body { font-family: sans-serif; padding: 20px; background: #f4f4f9; }" +
                        "h1 { color: #2b6cb0; }" +
                        "table { width: 100%; border-collapse: collapse; background: white; }" +
                        "th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }" +
                        "th { background: #3182ce; color: white; }" +
                        "</style></head><body>" +
                        "<h1>🏥 Hospital Management System (Java Backend)</h1>" +
                        "<p>Loaded " + manager.getPatientList().size() + " patients via Multithreading.</p>" +
                        "</body></html>";
                
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.setExecutor(null);
        System.out.println("Server started on http://localhost:8080");
        server.start();
    }
}
