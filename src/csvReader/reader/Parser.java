/*
 * Criei esse parser só pra aprender a mexer com arquivo de texto
 * Criamos a Estrutura certa e usamos substituimos ela no lugar
 * de LinkedList
 * A gambiarra ta grande pq n sei mexer com redex
 */
package csvReader.reader;

import csvReader.structs.Movie;
import csvReader.structs.Tag;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;
import structs.userStruct.UserNode;

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

    public static void ratingParser(String filename, HashTable<MovieNode> movies, HashTable<UserNode> users) {
        File file = new File(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {


            int perc = 0; // Só pra fazer uma loading bar, tirar na versão final

            //Inicia e ignora 1a linha
            String buff = br.readLine();
            while((buff = br.readLine()) != null){
                perc++;
                String[] list = buff.split(",");

                int userId = Integer.parseInt(list[0]);
                int movieId = Integer.parseInt(list[1]);
                float rating  = Float.parseFloat(list[2]);

                ((MovieNode) movies.find(movieId)).newRating(rating);

                try {
                    UserNode user = users.find(userId);
                    user.addRating(movieId, rating);
                } catch (NullPointerException e) {
                    users.insert(new UserNode(userId));
                    users.find(userId).addRating(movieId, rating);
                }


                //loading bar
                if( (perc%2000000) == 0)
                    System.out.print("|");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
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
