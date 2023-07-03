package ition_Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BitcoinRatePrinter {
    public static void main(String[] args) {
        try {
            // Create the URL object with the API endpoint
            URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");

            // Open a connection to the API URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject bpi = jsonResponse.getAsJsonObject("bpi");
            String rate = bpi.getAsJsonObject("USD").get("rate").getAsString();

            // Print the rate in words
            double rateValue = Double.parseDouble(rate.replace(",", ""));
            int rateInt = (int) rateValue;
            String rateInWords = convertToWords(rateInt);
            System.out.println(rateInWords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Converts a number to words
    private static String convertToWords(int number) {
        // The conversion logic remains the same
        // ...
    	 if (number == 0) {
             return "Zero";
         }
         String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
                 "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
         String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

         if (number < 20) {
             return units[number];
         }
         if (number < 100) {
             return tens[number / 10] + ((number % 10 != 0) ? " " : "") + units[number % 10];
         }
         if (number < 1000) {
             return units[number / 100] + " Hundred" + ((number % 100 != 0) ? " " : "") + convertToWords(number % 100);
         }
         if (number < 1000000) {
             return convertToWords(number / 1000) + " Thousand" + ((number % 1000 != 0) ? " " : "")
                     + convertToWords(number % 1000);
         }
         if (number < 1000000000) {
             return convertToWords(number / 1000000) + " Million" + ((number % 1000000 != 0) ? " " : "")
                     + convertToWords(number % 1000000);
         }
         return convertToWords(number / 1000000000) + " Billion" + ((number % 1000000000 != 0) ? " " : "")
                 + convertToWords(number % 1000000000);
     }
    }

