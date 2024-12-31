package com.jpiper;

import com.jpiper.Calculations;
import com.jpiper.APICalls;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        printVersion();

        //Gets the ESPN team IDs for the participants in the Monday Night Football game.
        String gameNightData = APICalls.getGameNightData("sundaynightfootball");
        Pair<Integer, Integer> teamIDs = APICalls.parseTeamIDPerNight(gameNightData);

        String teamData_1 = APICalls.getTeamData(teamIDs.getLeft());
        String teamData_2 = APICalls.getTeamData(teamIDs.getRight());

        Pair<String, String> teamInfo_1 = APICalls.parseTeamData(teamData_1);
        Pair<String, String> teamInfo_2 = APICalls.parseTeamData(teamData_2);

        System.out.println("[DATA] This game features the " + teamInfo_1.getLeft() + " (" + teamInfo_1.getRight() + ") vs. the " + teamInfo_2.getLeft() + " (" + teamInfo_2.getRight() + ")");

        String teamStats_1 = APICalls.getTeamStats(teamIDs.getLeft());
        String teamStats_2 = APICalls.getTeamStats(teamIDs.getRight());

        APICalls.parseTeamStats(teamStats_1, teamInfo_1.getLeft());
        APICalls.parseTeamStats(teamStats_2, teamInfo_2.getLeft());
    }

    private static void printVersion() {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("[WARN] Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
            String version = properties.getProperty("version");

            System.out.println("  /$$   /$$ /$$$$$$$$ /$$             /$$   /$$ /$$$$$$$$ /$$       /$$$$$$$  /$$$$$$$$ /$$$$$$$");
            System.out.println(" | $$$ | $$| $$_____/| $$            | $$  | $$| $$_____/| $$      | $$__  $$| $$_____/| $$__  $$");
            System.out.println(" | $$$$| $$| $$      | $$            | $$  | $$| $$      | $$      | $$  \\ $$| $$      | $$  \\ $$");
            System.out.println(" | $$ $$ $$| $$$$$   | $$            | $$$$$$$$| $$$$$   | $$      | $$$$$$$/| $$$$$   | $$$$$$$/");
            System.out.println(" | $$  $$$$| $$__/   | $$            | $$__  $$| $$__/   | $$      | $$____/ | $$__/   | $$__  $$");
            System.out.println(" | $$\\  $$$| $$      | $$            | $$  | $$| $$      | $$      | $$      | $$      | $$  \\ $$");
            System.out.println(" | $$ \\  $$| $$      | $$$$$$$$      | $$  | $$| $$$$$$$$| $$$$$$$$| $$      | $$$$$$$$| $$  | $$");
            System.out.println(" |__/  \\__/|__/      |________/      |__/  |__/|________/|________/|__/      |________/|__/  |__/");
            System.out.println("\nNFL Parlay Helper: v" + version + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}