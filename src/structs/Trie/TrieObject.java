package structs.Trie;

import csvReader.structs.Movie;

import java.util.List;

public interface TrieObject {
    public ArvoreTrie popularTrie(List<Movie> movieList);
}
