package com.jpiper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.tuple.Pair;

public class APICalls {
    public static String getGameNightData(String nightName) {
        // Define the API URL
        String apiUrl = "https://site.api.espn.com/apis/site/v2/" + nightName; // Replace with your API endpoint
        System.out.println("[INFO] Connecting to URL: " + apiUrl);

        // Create an HTTP client
        ExecutorService executor = Executors.newFixedThreadPool(4);
        HttpClient client = HttpClient.newBuilder()
                .executor(executor)
                .build();

        // Create an HTTP GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful
            if (response.statusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = response.body();
                //System.out.println("[INFO] Response body: " + jsonResponse);

                return jsonResponse;
            } else {
                System.out.println("[WARN] Failed to get a successful response. HTTP Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return null;
    }

    public static Pair<Integer, Integer> parseTeamIDPerNight(String jsonBody) throws JsonProcessingException {
        System.out.println("[INFO] Parsing out ESPN team IDs . . .");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonBody);

        JsonNode eventNode = rootNode.get("events");
        JsonNode eventArray = eventNode.get(eventNode.size()-1);

        JsonNode gameNode = eventArray.get("competitions");
        JsonNode gameArray = gameNode.get(gameNode.size()-1);

        JsonNode compNode = gameArray.get("competitors");

        // Extract individual values
        int teamID_1 = compNode.get(0).get("id").asInt();
        int teamID_2 = compNode.get(1).get("id").asInt();

        System.out.println("[INFO] Complete!");
        return Pair.of(teamID_1, teamID_2);
    }

    public static String getTeamData(int teamID) {
        // Define the API URL
        String apiUrl = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/" + teamID; // Replace with your API endpoint
        System.out.println("[INFO] Connecting to URL: " + apiUrl);

        // Create an HTTP client
        ExecutorService executor = Executors.newFixedThreadPool(4);
        HttpClient client = HttpClient.newBuilder()
                .executor(executor)
                .build();

        // Create an HTTP GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful
            if (response.statusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = response.body();
                //System.out.println("[INFO] Response body: " + jsonResponse);

                return jsonResponse;
            } else {
                System.out.println("[WARN] Failed to get a successful response. HTTP Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return null;
    }

    public static Pair<String, String> parseTeamData(String jsonBody) throws JsonProcessingException {
        System.out.println("[INFO] Parsing out individual team info . . .");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonBody);

        JsonNode teamNode = rootNode.get("team");
        JsonNode recordNode = teamNode.get("record");
        JsonNode itemsNode = recordNode.get("items");
        JsonNode itemsArray = itemsNode.get(0);

        // Extract individual values
        String teamName = teamNode.get("displayName").asText();
        String recordText = itemsArray.get("summary").asText();

        System.out.println("[INFO] Complete!");

        return Pair.of(teamName, recordText);
    }

    public static String getTeamStats(int teamID) {
        // Define the API URL
        String apiUrl = "https://sports.core.api.espn.com/v2/sports/football/leagues/nfl/seasons/2024/types/2/teams/" + teamID + "/statistics"; // Replace with your API endpoint
        System.out.println("[INFO] Connecting to URL: " + apiUrl);

        // Create an HTTP client
        ExecutorService executor = Executors.newFixedThreadPool(4);
        HttpClient client = HttpClient.newBuilder()
                .executor(executor)
                .build();

        // Create an HTTP GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful
            if (response.statusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = response.body();
                //System.out.println("[INFO] Response body: " + jsonResponse);

                return jsonResponse;
            } else {
                System.out.println("[WARN] Failed to get a successful response. HTTP Code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return null;
    }

    public static double[] parseTeamStats(String jsonBody) throws JsonProcessingException {
        System.out.println("[INFO] Parsing out team statistics . . .");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonBody);

        // #0 - Rushing Yards Allowed Per Attempt

        // #1 - League Average Rushing Yards Allowed Per Attempt

        // #2 - Tight End Yards Allowed

        // #3 - Total Yards Allowed

        // #4 - Yards Allowed on Passes > 20 Yards

        // #5 - Rushing Yards per Carry

        // #6 - League Average Rushing Yards per Carry

        // #7 - Passing Yards per Attempt

        // #8 - League Average Passing Yards per Attempt

        // #9 - Touchdowns Scored in Red Zone

        // #10 - Red Zone Attempts

        JsonNode teamNode = rootNode.get("team");
        JsonNode recordNode = teamNode.get("record");
        JsonNode itemsNode = recordNode.get("items");
        JsonNode itemsArray = itemsNode.get(0);

        // Extract individual values
        String teamName = teamNode.get("displayName").asText();
        String recordText = itemsArray.get("summary").asText();

        System.out.println("[INFO] Complete!");

        return null;
    }
}
