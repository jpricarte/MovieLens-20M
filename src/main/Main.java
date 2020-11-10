/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import csvReader.reader.Parser;
import csvReader.structs.Movie;
import structs.Trie.Trie;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;
import structs.userStruct.UserNode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author guibgoulart, jpricarte
 */

// 32749 = menor numero primo maior que (num_filmes + 20%)

public class Main {
    public static void main(String args[]) {
        System.out.println("go");
        LinkedList<Movie> movies = Parser.movieParser("movie.csv");

        // Criar uma função pra fazer isso, mas a partir daqui populamos a tabela Hash
        HashTable<MovieNode> movieHashTable = new HashTable<MovieNode>(32749);
        Trie moviesTrie = new Trie();

        for (var movie : movies) {
            movieHashTable.insert(new MovieNode(movie.getMovieId(), movie.getTitle(), movie.getGenres()));
            moviesTrie.insert(movie.getTitle(), movie.getMovieId());
        }

        movies = null;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./movieTrie.dat"));){
            oos.writeObject(moviesTrie);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            moviesTrie = null;
        }



        HashTable<UserNode> userHashTable = new HashTable<UserNode>(150001);
        // Criar estrutura de User e passar essa estrutura aqui
        Parser.ratingParser("rating.csv", movieHashTable, userHashTable);

        System.out.println("Fim do parse");

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./movieHashTable.dat"));){
//            oos.writeObject(movieHashTable);
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            movieHashTable = null;
//        }
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./userHashTable.dat"));){
//            oos.writeObject(userHashTable);
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            userHashTable = null;
//        }


//        // todos filmes começando com Star Wa
//        LinkedList<Integer> l = moviesTrie.getAllMovies("Star Wa");
//
//        for(int i: l) {
//            System.out.println(movieHashTable.find(i).getMovieName() + "\t" + movieHashTable.find(i).getRatingAverage());
//        }

    }
}
