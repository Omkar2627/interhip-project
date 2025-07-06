package com.app.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) {
        try {
            // Example city
            String city = "Mumbai";
            // OpenWeatherMap API URL with demo placeholder API Key
            String apiKey = "YOUR_API_KEY";
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

            // Create URL object
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON
            JSONObject jsonObj = new JSONObject(response.toString());

            // Extract data
            String weather = jsonObj.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = jsonObj.getJSONObject("main").getDouble("temp");
            int humidity = jsonObj.getJSONObject("main").getInt("humidity");

            // Display structured data
            System.out.println("Weather in " + city + ":");
            System.out.println("Condition: " + weather);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
