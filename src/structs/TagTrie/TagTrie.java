package structs.TagTrie;

import structs.GenreTrie.GenreNode;

import java.util.LinkedList;

public class TagTrie {
        private TagNode root;

        public TagTrie() {
            this.root = new TagNode();
        }

        public void insert(String tag, int movieId) {
            TagNode aux = root;
            tag = tag.trim().toUpperCase();

            // Faz apenas o caminhamento
            for (int i=0; i < tag.length(); i++) {
                char c = tag.charAt(i);

                // Procura filho ou cria, se esse não existir
                aux = aux.findOrCreateChild(c);
            }

            aux.addMovieIdIfNotExists(movieId);
            aux.setLeaf(true);
        }

        public LinkedList<Integer> getMovies(String tagName) {
            TagNode aux = root;
            tagName = tagName.trim().toUpperCase();

            // Faz o caminhamento ou retorna -1 se não existir caminho
            for (int i=0; i < tagName.length(); i++) {
                char c = tagName.charAt(i);

                aux = aux.findChild(c);
                if (aux == null) return new LinkedList<>();
            }

            return aux.getMovieIdList();
        }
}
