import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Give pokemon: ");
        String selectedPokemon = input.nextLine();

        URL pokemonURL = new URL("https://pokeapi.co/api/v2/pokemon/" + selectedPokemon.toLowerCase());
        URLConnection connection = pokemonURL.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        JSONObject pokemonStats = new JSONObject(in.readLine());
        System.out.println(pokemonStats);

        String imageURL = (String) pokemonStats
                .getJSONObject("sprites").get("front_default");
        System.out.println(imageURL);

        URL pokemonImageURL = new URL(imageURL);
        Image pokemonImage = ImageIO.read(pokemonImageURL);

        JFrame frame = new JFrame();
        JLabel jlabel = new JLabel(new ImageIcon(pokemonImage));
        frame.getContentPane().add(jlabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
