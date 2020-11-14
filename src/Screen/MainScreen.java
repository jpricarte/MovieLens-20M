package Screen;

import csvReader.reader.Parser;
import csvReader.structs.Movie;
import csvReader.structs.Tag;
import structs.MovieTrie.MovieTrie;
import structs.TagTrie.TagNode;
import structs.TagTrie.TagTrie;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;
import structs.hashTable.UserNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainScreen {
    private JTextField movieTextField;
    private JPanel panel1;
    private JButton searchPrefixButton;
    private JButton searchMovieButton;
    private JTextArea moviesByNameResultArea;
    private JTabbedPane tabs;
    private JButton searchUserRatesButton;
    private JTextField userIdTextField;
    private JTextArea moviesByUserResultArea;
    private JPanel searchUserIdTab;
    private JPanel searchMovieTab;
    private JPanel seachTagTab;
    private JTextField tagsTextField;
    private JButton searchTagsButton;
    private JTextArea moviesByTagsResultArea;

    static HashTable<MovieNode> movieHashTable;
    static MovieTrie moviesTrie;
    static TagTrie tagsTrie;
    static HashTable<UserNode> userHashTable;

    // QUERIES
    private void searchMovieQuery() {
        moviesByNameResultArea.setText("");

        // Procura o id de um filme que tenha exatamente o mesmo nome que o inserido e imprime informações
        int id = moviesTrie.getMovie(movieTextField.getText());
        if (id>0) {
            MovieNode m = movieHashTable.find(id);
            moviesByNameResultArea.append("Filme: " + m.getMovieName() +
                    "\n\t Nota: " + m.getRatingAverage() +
                    "\n\t Nº de Avaliações: " + m.getNumOfReviews() +
                    "\n\t Generos: ");

            for (var genre : m.getGenres()) {
                moviesByNameResultArea.append(genre + "; ");
            }
            moviesByNameResultArea.append("\n");
        }
        else
            moviesByNameResultArea.setText("Filme não encontrado");

        movieTextField.setText("");
    }

    private void searchByPrefixQuery() {

        // Pega Ids dos filmes
        LinkedList<Integer> ids = moviesTrie.getAllMovies(movieTextField.getText());

        moviesByNameResultArea.setText("");

        // Se não houver filme, erro
        if (ids == null)
            moviesByNameResultArea.setText("Nenhum Filme Encontrado");

        // Senão, imprime cada filme com seus dados
        // TODO: Ordenar por nota ou popularidade (nº de reviews)
        else for(var id : ids) {
                MovieNode m = movieHashTable.find(id);
                moviesByNameResultArea.append("Filme: " + m.getMovieName() +
                        "\n\t Nota: " + m.getRatingAverage() +
                        "\n\t Nº de Avaliações: " + m.getNumOfReviews() +
                        "\n\t Generos: ");

                // Imprime generos separados por ;
                for (var genre : m.getGenres()) {
                    moviesByNameResultArea.append(genre + "; ");
                }
                moviesByNameResultArea.append("\n");
            }

        movieTextField.setText("");
    }

    private void searchUserByIdQuery() {
        // Limpa área de texto
        moviesByUserResultArea.setText("");

        // Busca usuário
        try {
            UserNode user = userHashTable.find(Integer.parseInt(userIdTextField.getText()));

            // Para cada filme, imprime na tela -> Provavelmente muda essa parte
            for (int i=0; i < user.getMovieIdList().size(); i++) {
                int movieId = user.getMovieIdList().get(i);
                float movieRate = user.getRatingList().get(i);

                // Pega valores do filme e imprime
                MovieNode m = movieHashTable.find(movieId);

                moviesByUserResultArea.append(m.getMovieName()+
                        "\n\t Nota: " + movieRate +
                        "\n\t Média: " + m.getRatingAverage() +
                        "\n\t Outras " + m.getNumOfReviews() + " pessoas avaliaram esse filme." +
                        "\n\n");
            }
        } catch (NumberFormatException e) {
            moviesByUserResultArea.setText(e.getMessage());
        }

        userIdTextField.setText("");
    }

    private void searchByTagsQuery() {
        moviesByTagsResultArea.setText("");

        String[] tags = tagsTextField.getText().split(",");
        LinkedList<Integer> moviesId = new LinkedList<Integer>();
        for (var tag : tags) {
             LinkedList<Integer> auxList = tagsTrie.getMovies(tag);

             for (int movieId : auxList) {
                 if(!moviesId.contains(movieId)) {
                     MovieNode m = movieHashTable.find(movieId);
                     moviesId.addLast(movieId);
                     moviesByTagsResultArea.append(m.getMovieName() + "\n\n");
                     }
             }
        }
    }

    private void searchByTagsIntersectionQuery() {
        moviesByTagsResultArea.setText("");

        String[] tags = tagsTextField.getText().split(",");
        LinkedList<Integer> moviesId = new LinkedList<Integer>();

        LinkedList<Integer> auxList = tagsTrie.getMovies(tags[0]);

        for (int movieId : auxList) {
                moviesId.addLast(movieId);
        }

        for (var tag : tags) {
            auxList = tagsTrie.getMovies(tag);

            if (moviesId.size() > 0)
                for (int movieId : moviesId) {
                    if(!auxList.contains(movieId)) {
                        System.out.println(auxList.contains(movieId));
                        moviesId.removeLastOccurrence(movieId);
                    }
                }
        }

        for (int movieId : auxList) {
            moviesByTagsResultArea.append(movieHashTable.find(movieId).getMovieName() + "\n\n");
        }
    }

    // ACTIONS
    public MainScreen() {

        searchMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchMovieQuery();
            }
        });
        searchPrefixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchByPrefixQuery();
            }
        });
        searchUserRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchUserByIdQuery();
            }
        });
        searchTagsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchByTagsQuery();
            }
        });
    }

    // MAIN

    public static void main(String args[]) {
        LinkedList<Movie> movies = Parser.movieParser("movie.csv");

        // Criar uma função pra fazer isso, mas a partir daqui populamos a tabela Hash
        movieHashTable = new HashTable<MovieNode>(32749);
        moviesTrie = new MovieTrie();
        tagsTrie = new TagTrie();

        for (var movie : movies) {
            movieHashTable.insert(new MovieNode(movie.getMovieId(), movie.getTitle(), movie.getGenres()));
            moviesTrie.insert(movie.getTitle(), movie.getMovieId());
        }
        movies = null;

        LinkedList<Tag> tagList = Parser.tagParser("tag.csv");
        for (var tag : tagList) {
            tagsTrie.insert(tag.getTag(),tag.getMovieId());
        }
        tagList = null;

        userHashTable = new HashTable<UserNode>(150001);
        Parser.ratingParser("rating.csv", movieHashTable, userHashTable);

        System.out.println("Fim da leitura, abrindo janela...");

        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
