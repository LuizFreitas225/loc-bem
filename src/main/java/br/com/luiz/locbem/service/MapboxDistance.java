package br.com.luiz.locbem.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapboxDistance {

    // Método para calcular a distância entre dois pontos usando a API Mapbox
    public static void getDistance(double lat1, double lon1, double lat2, double lon2, String accessToken) {
        try {
            // Monta a URL da API com as coordenadas e o token de acesso
            String url = "https://api.mapbox.com/directions/v5/mapbox/driving/"
                    + lon1 + "," + lat1 + ";" + lon2 + "," + lat2
                    + "?access_token=" + accessToken;

            // Cria um objeto URL
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Define o método de requisição como GET
            connection.setRequestMethod("GET");

            // Código de resposta da requisição
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Lê a resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Imprime a resposta completa da API
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Substitua 'your_access_token' pelo seu token de acesso real
        getDistance(-10.212802, -48.360483, -10.2129, -48.3601, "pk.eyJ1IjoibHVpem5pY29sYXUiLCJhIjoiY2x3ZnFvcjZvMXllNjJ4dHh5YXBxZHlvbCJ9.ohZx2sPrYRiRI33-t7rNhA");
    }
}
