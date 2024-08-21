package br.com.luiz.locbem.service;

import br.com.luiz.locbem.dto.georeferencing.CoordinatesDTO;
import br.com.luiz.locbem.exception.MapBoxException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapboxDistanceService {

    public static final String  URL_MAPBOX_DISTANCE = "https://api.mapbox.com/directions/v5/mapbox/driving/";
    public static final String  URL_MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoibHVpem5pY29sYXUiLCJhIjoiY2x3ZnFvcjZvMXllNjJ4dHh5YXBxZHlvbCJ9.ohZx2sPrYRiRI33-t7rNhA";
    public static double getDistanceInKilometer( CoordinatesDTO userCoordinates, CoordinatesDTO offerCoordinates) {

        String url = URL_MAPBOX_DISTANCE
                + userCoordinates.getLongitude() + "," + userCoordinates.getLatitude() + ";"
                + offerCoordinates.getLongitude() + "," + offerCoordinates.getLatitude()
                + "?access_token=" + URL_MAPBOX_ACCESS_TOKEN;
        double distanceInMeters = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Response Code : " + connection.getResponseCode());

            distanceInMeters = getDistanceInMeters(in);

        } catch (Exception e) {
            throw new MapBoxException();
        }
        return distanceInMeters / 1000;
    }

    private static double getDistanceInMeters(BufferedReader in) throws IOException {
        double distanceInMeters;
        JSONObject jsonObject = new JSONObject(getResponseAsString(in));
        distanceInMeters = jsonObject.getJSONArray("routes")
                .getJSONObject(0)
                .getDouble("distance");
        return distanceInMeters;
    }

    private static String getResponseAsString(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        System.out.print(getDistanceInKilometer(new CoordinatesDTO(-10.212802,-48.360483 ),
                new CoordinatesDTO( -10.2129,-48.3601 )));
    }
}
