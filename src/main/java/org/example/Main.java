package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static String ROOT_URL = "https://pokeapi.co/api/v2/pokemon-form/";
    public static HashMap<Integer, PokemonDTO> pokemonHashMap = new HashMap<>();
    final static String HTML_FILE_PATH = "index.html";

    public static void main(String[] args) {
        // for pokemon with ids within range a to b
        int startingPokemonID = 1;
        int endingPokemonID = 151;
        try {
            populateHashMap(pokemonHashMap, ROOT_URL, startingPokemonID, endingPokemonID);

            Scanner scanner = new Scanner(System.in);
            // ask for what type pokemon to display
            System.out.print("Enter a type (fire, water, grass, psychic, electric, normal, flying, rock, ground, poison): ");
            String userInput = scanner.nextLine();

            String content = "";
            for (PokemonDTO pokemonObject : getPokemonByType(pokemonHashMap, userInput)) {
                content += getContentFromDTO(pokemonObject);
            }
            // writes to HTML file -> can be viewed within intellij or opened in whatever browser
            writeToFile(content, HTML_FILE_PATH);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // for single pokemon requests
//        try {
//            int pokemonId = 250;
//            String response = getHTTPResponse(formatRequestString(ROOT_URL, pokemonId));
//            String content = getContent(response);
//            writeToFile(content, HTML_FILE_PATH);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Something went wrong :(");
//        }
    }

    public static void populateHashMap(HashMap<Integer, PokemonDTO> hashMap, String URL, int idLow, int idHigh) throws Exception {
        for (int i = idLow; i <= idHigh; i++) {
            String url = formatRequestString(URL, i);
            String response = getHTTPResponse(url);
            hashMap.put(i, getPokemonObject(response));
        }
    }

    public static ArrayList<PokemonDTO> getPokemonByType(HashMap<Integer, PokemonDTO> hashMap, String type) {
        ArrayList<PokemonDTO> pokemon = new ArrayList<>();
        for (PokemonDTO pokemonObject : hashMap.values()) {
            if (getPokemonTypes(pokemonObject).contains(type)) {
                pokemon.add(pokemonObject);
            }
        }
        return pokemon;
    }

    public static ArrayList<String> getPokemonTypes(PokemonDTO pokemonObject) {
        ArrayList<String> types = new ArrayList<>();
        for (PokemonDTO.Types type : pokemonObject.getTypes()) {
            types.add(type.getType().getName());
        }
        return types;
    }

    public static String formatRequestString(String URL, int id) {
        return URL + id;
    }

    public static String getHTTPResponse(String URL) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URL);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Accept", "application/json").GET().build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = httpResponse.statusCode();
        if (statusCode == 200) {
            return httpResponse.body();
        } else {
            return String.format("GET request failed: %d status code received", statusCode);
        }
    }

    public static PokemonDTO getPokemonObject(String response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PokemonDTO pokemonObject = objectMapper.readValue(response, PokemonDTO.class);
        return pokemonObject;
    }

    public static String getContentFromDTO(PokemonDTO pokemonObject) {
        String defaultFrontImage = pokemonObject.getSprites().getFrontDefault();
        String defaultBackImage = pokemonObject.getSprites().getBackDefault();
        String shinyFrontImage = pokemonObject.getSprites().getFrontShiny();
        String shinyBackImage = pokemonObject.getSprites().getBackShiny();

        return generateImageElementsString(new String[] {defaultFrontImage, defaultBackImage, shinyFrontImage, shinyBackImage});
    }

    public static String generateImageElementsString(String[] images) {
        StringBuilder content = new StringBuilder();
        for(String imageURL : images) {
            content.append("<img src=\"").append(imageURL).append("\">");
        }
        content.append("<br>");
        return content.toString();
    }

    public static void writeToFile(String content, String filePath) throws Exception {
        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("<html>\n" +
                "<head><title>Playing with APIs</title></head>\n" +
                "<body>" + content + "</body>\n" +
                "</html>");
        writer.close();
    }
}