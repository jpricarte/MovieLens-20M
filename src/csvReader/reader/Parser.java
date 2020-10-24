/*
 * Criei esse parser só pra aprender a mexer com arquivo de texto
 * Criamos a Estrutura certa e usamos substituimos ela no lugar
 * de LinkedList
 * A gambiarra ta grande pq n sei mexer com redex
 */
package csvReader.reader;

import csvReader.structs.Movie;
import csvReader.structs.Rating;
import csvReader.structs.Tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author guibgoulart, jpricarte
 */
public class Parser {
    public static LinkedList<Movie> movieParser(String filename)  {
        
        LinkedList<Movie> m = new LinkedList<Movie>();
        
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter(",\"|\n");
            sc.nextLine();
            
            while (sc.hasNext()) {
                int id = sc.nextInt();
                String title = sc.next().replace('"', '\0');
                String genres = sc.next().replace('"', '\0');
                List<String> genresList = Arrays.asList(genres.split("\\|"));
                
                m.add( new Movie(id, title, genresList) );
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    public static LinkedList<Rating> ratingParser(String filename) {
        File file = new File(filename);
        LinkedList<Rating> ratingList = new LinkedList<Rating>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Inicia e ignora 1a linha
            String buff = br.readLine();
            while((buff = br.readLine()) != null){
                String[] list = buff.split(",");

                int userId = Integer.parseInt(list[0]);
                int movieId = Integer.parseInt(list[1]);
                double rating  = Double.parseDouble(list[2]);

                ratingList.add(new Rating(userId, movieId, rating, list[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ratingList;
    }

    public static LinkedList<Tag> tagParser(String filename) {
        File file = new File(filename);
        LinkedList<Tag> tagList = new LinkedList<Tag>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            //Inicia e ignora 1a linha
            String buff = br.readLine();
            while((buff = br.readLine()) != null){
                String[] list = buff.split(",");


                int userId = Integer.parseInt(list[0]);
                int movieId = Integer.parseInt(list[1]);

                tagList.add(new Tag(userId, movieId, list[2].replace("\"", ""), list[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tagList;
    }
}
