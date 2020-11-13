/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import csvReader.reader.Parser;
import csvReader.structs.Movie;
import structs.MovieTrie.MovieTrie;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;
import structs.hashTable.UserNode;

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
        MovieTrie moviesTrie = new MovieTrie();

        for (var movie : movies) {
            movieHashTable.insert(new MovieNode(movie.getMovieId(), movie.getTitle(), movie.getGenres()));
            moviesTrie.insert(movie.getTitle(), movie.getMovieId());
        }
        movies = null;

        HashTable<UserNode> userHashTable = new HashTable<UserNode>(150001);
        // Criar estrutura de User e passar essa estrutura aqui
        Parser.ratingParser("minirating.csv", movieHashTable, userHashTable);

        System.out.println("Fim do parse");

        System.out.println(moviesTrie.getMovie("Lives of a Bengal Lancer, The (1935)"));

        // todos filmes começando com Star Wa
        LinkedList<Integer> l = moviesTrie.getAllMovies("Lord of");

        for(int i: l) {
            System.out.println(movieHashTable.find(i).getMovieName() + "\t" + movieHashTable.find(i).getRatingAverage());
        }

    }
}
