/*
 * Criei esse parser só pra aprender a mexer com arquivo de texto
 * Criamos a Estrutura certa e usamos substituimos ela no lugar
 * de LinkedList
 * A gambiarra ta grande pq n sei mexer com redex
 */
package main;

import fileStructs.Movie;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pujol
 */
public class Parser {
    public static LinkedList<Movie> movieParser(String filename)  {
        
        LinkedList<Movie> m = new LinkedList();
        
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}
