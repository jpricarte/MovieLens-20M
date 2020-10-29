/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import csvReader.reader.Parser;
import csvReader.structs.Movie;
import csvReader.structs.Rating;
import csvReader.structs.Tag;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;

import java.util.LinkedList;

/**
 *
 * @author guibgoulart, jpricarte
 */

// 32749 = menor numero primo maior que (num_filmes + 20%)

public class Main {
    public static void main(String args[]) {
        LinkedList<Movie> movies = Parser.movieParser("movie.csv");

        // Criar uma função pra fazer isso, mas a partir daqui populamos a tabela Hash
        HashTable<MovieNode> movieHashTable = new HashTable<MovieNode>(32749);
        for (var movie : movies) {
            movieHashTable.insert(new MovieNode(movie.getMovieId(), movie.getTitle(), movie.getGenres()));
        }
        movies = null;

        // Criar estrutura de User e passar essa estrutura aqui
        Parser.ratingParser("rating.csv", movieHashTable);

        // Aqui, salvariamos as Estruturas em disco e fariamos a nossa Trie

        // Pega nota media de Toy Story (1995)
        movieHashTable.find(1).updateRatingAverage();
        System.out.println(movieHashTable.find(1).getRatingAverage());
    }
}
