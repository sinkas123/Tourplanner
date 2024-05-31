package com.example.jpademo.service.impl;

import com.example.jpademo.api.MapApi;
import com.example.jpademo.persistence.entities.TourEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapApiImpl implements MapApi {

    private static final String API_KEY = "5b3ce3597851110001cf624846203adf1286454dbbb676c6f68ec13d";

    @Override
    public String searchAddress(String text) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openrouteservice.org/geocode/search?boundary.country=AT&api_key=" + API_KEY + "&text=" + text,
                String.class);

        String coordinate = response.getBody().substring(response.getBody().indexOf("coordinates") + 14, response.getBody().indexOf("properties") - 4);
        System.out.println(response);
        return coordinate;
    }

    @Override
    public TourEntity searchTourInformation(TourEntity tourEntity){
        String start = this.searchAddress(tourEntity.getStartLocation());
        String end = this.searchAddress(tourEntity.getEndLocation());

        String profile = tourEntity.getTransportType();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openrouteservice.org/v2/directions/" + profile + "?api_key=" + API_KEY + "&start=" + start + "&end=" + end,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            System.out.println(jsonResponse);

            JSONArray features = jsonResponse.getJSONArray("features");
            if (features.length() < 1) {
                return null;
            }

            JSONObject properties = features.getJSONObject(0).getJSONObject("properties");
            JSONArray segments = properties.getJSONArray("segments");
            JSONObject firstSegment = segments.getJSONObject(0);
            double distance = firstSegment.getDouble("distance");
            double seconds = firstSegment.getDouble("duration");

            long wholeMinutes = (long) (seconds / 60);
            Duration duration = Duration.ofMinutes(wholeMinutes);

            tourEntity.setTourDistance(distance);
            tourEntity.setEstimatedTime(duration);
        }

            return tourEntity;
            //properties -> summary
    }
    @Override
    public List<double[]> searchDirection(String start, String end) {
        String[] profiles = {"driving-car", "cycling-regular", "foot-walking"};
        String profile = profiles[2];
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openrouteservice.org/v2/directions/" + profile + "?api_key=" + API_KEY + "&start=" + start + "&end=" + end,
                String.class);

        List<double[]> routeCoordinates = new ArrayList<>();
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            System.out.println(jsonResponse);

            JSONArray features = jsonResponse.getJSONArray("features");
            if (features.length() < 1) {
                return null;
            }
            JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
            JSONArray coordinates = geometry.getJSONArray("coordinates");

            // Swapping longitude and latitude for each coordinate
            for (int i = 0; i < coordinates.length(); i++) {
                JSONArray coord = coordinates.getJSONArray(i);
                double longitude = coord.getDouble(0);
                double latitude = coord.getDouble(1);
                routeCoordinates.add(new double[]{latitude, longitude});
            }
        }
        return routeCoordinates;
    }

    @Override
    public void getMap() {
        // TODO implement me
    }

    /*
    public static Tile latlon2Tile(double lat_deg, double lon_deg, int zoom) {
        double lat_rad = Math.toRadians(lat_deg);
        double n = Math.pow(2.0, zoom);
        int x_tile = (int) Math.floor((lon_deg + 180.0) / 360.0 * n);
        int y_tile = (int) Math.floor((1.0 - Math.log(Math.tan(lat_rad) + 1 /
                Math.cos(lat_rad)) / Math.PI) / 2.0 * n);
        return new Tile(x_tile, y_tile);
    }
     */
}
