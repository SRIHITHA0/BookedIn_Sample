package com.cts.mfrp.bkin.config;

import com.cts.mfrp.bkin.entity.Book;
import com.cts.mfrp.bkin.entity.Genre;
import com.cts.mfrp.bkin.repository.BookRepository;
import com.cts.mfrp.bkin.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class DataSeeder {

    private static final Map<String, String> COVER_URLS = Map.of(
        "The Name of the Wind",                   "https://tse2.mm.bing.net/th/id/OIP.l7OmNx1kwCVmcD3DtCjZcQHaMP?rs=1&pid=ImgDetMain&o=7&rm=3",
        "Project Hail Mary",                       "https://images.squarespace-cdn.com/content/v1/60332279321c6f6cc87cf8fe/c459467d-c710-4c8b-a34e-d9d98f275738/Project+Hail+Mary.jpg",
        "Gone Girl",                               "https://www.commonsensemedia.org/sites/default/files/styles/ratio_2_3_xlarge/public/product-images/csm-movie/gone-girl.jpg",
        "The Great Gatsby",                        "https://tse3.mm.bing.net/th/id/OIP._OuBE7YbstiymN2Hwb0K1AHaLS?w=1000&h=1524&rs=1&pid=ImgDetMain&o=7&rm=3",
        "Educated",                                "https://cdn2.penguin.com.au/covers/original/9780099511021.jpg",
        "The Hitchhiker's Guide to the Galaxy",    "https://tse1.mm.bing.net/th/id/OIP.4fBHiA9pN46_bpQ4SKwikQHaLH?rs=1&pid=ImgDetMain&o=7&rm=3",
        "In the Woods",                            "https://m.media-amazon.com/images/I/91jMHH2+qHL._SL1500_.jpg",
        "Atomic Habits",                           "https://tse4.mm.bing.net/th/id/OIP.brD-4-_kgf7hupfcL9bmhAAAAA?w=300&h=450&rs=1&pid=ImgDetMain&o=7&rm=3",
        "The Midnight Library",                    "https://m.media-amazon.com/images/I/71ls-I6A5KL.jpg",
        "Sapiens",                                 "https://d30a6s96kk7rhm.cloudfront.net/original/readings/978/009/959/9780099590088.jpg"
    );

    @Bean
    CommandLineRunner seedDatabase(GenreRepository genreRepository, BookRepository bookRepository) {
        return args -> {
            if (genreRepository.count() == 0) {
                List<Genre> savedGenres = genreRepository.saveAll(List.of(
                    new Genre("Fiction",         "Stories created from imagination"),
                    new Genre("Mystery",         "Suspenseful crime and detective stories"),
                    new Genre("Science Fiction", "Speculative futures and technology"),
                    new Genre("Fantasy",         "Magical worlds and mythical beings"),
                    new Genre("Thriller",        "High-stakes tension and action"),
                    new Genre("Romance",         "Love stories and emotional journeys"),
                    new Genre("Non-Fiction",     "Real events and factual content"),
                    new Genre("Biography",       "Life stories of real people"),
                    new Genre("History",         "Exploration of past events"),
                    new Genre("Self-Help",       "Personal growth and development")
                ));

                Map<String, Genre> g = savedGenres.stream()
                    .collect(Collectors.toMap(Genre::getName, Function.identity()));

                bookRepository.saveAll(List.of(
                    new Book("The Name of the Wind", "Patrick Rothfuss", "9780756404741",
                        "A legendary figure tells his own story in this epic fantasy.",
                        COVER_URLS.get("The Name of the Wind"),
                        LocalDate.of(2007, 3, 27), g.get("Fantasy")),

                    new Book("Project Hail Mary", "Andy Weir", "9780593135204",
                        "A lone astronaut must save Earth from an extinction-level threat.",
                        COVER_URLS.get("Project Hail Mary"),
                        LocalDate.of(2021, 5, 4), g.get("Science Fiction")),

                    new Book("Gone Girl", "Gillian Flynn", "9780307588371",
                        "A marriage with secrets unravels through twisting perspectives.",
                        COVER_URLS.get("Gone Girl"),
                        LocalDate.of(2012, 6, 5), g.get("Thriller")),

                    new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565",
                        "A tale of wealth, love, and the American Dream in the Jazz Age.",
                        COVER_URLS.get("The Great Gatsby"),
                        LocalDate.of(1925, 4, 10), g.get("Fiction")),

                    new Book("Educated", "Tara Westover", "9780399590504",
                        "A memoir of a woman who leaves her survivalist family to discover education.",
                        COVER_URLS.get("Educated"),
                        LocalDate.of(2018, 2, 20), g.get("Biography")),

                    new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "9780345391803",
                        "A comedic sci-fi odyssey through space with a bewildered human.",
                        COVER_URLS.get("The Hitchhiker's Guide to the Galaxy"),
                        LocalDate.of(1979, 10, 12), g.get("Science Fiction")),

                    new Book("In the Woods", "Tana French", "9780143113492",
                        "A detective investigates a murder near the site of a childhood trauma.",
                        COVER_URLS.get("In the Woods"),
                        LocalDate.of(2007, 5, 22), g.get("Mystery")),

                    new Book("Atomic Habits", "James Clear", "9780735211292",
                        "A framework for building good habits and breaking bad ones.",
                        COVER_URLS.get("Atomic Habits"),
                        LocalDate.of(2018, 10, 16), g.get("Self-Help")),

                    new Book("The Midnight Library", "Matt Haig", "9780525559474",
                        "A woman discovers a library between life and death with infinite possibilities.",
                        COVER_URLS.get("The Midnight Library"),
                        LocalDate.of(2020, 9, 29), g.get("Fiction")),

                    new Book("Sapiens", "Yuval Noah Harari", "9780062316097",
                        "A brief history of humankind from ancient ancestors to modern civilization.",
                        COVER_URLS.get("Sapiens"),
                        LocalDate.of(2011, 1, 1), g.get("History"))
                ));

                System.out.println("[BookedIn] Database seeded: 10 genres, 10 books.");
            }

            // Always run: update cover URLs to ISBN-based format for all existing books
            bookRepository.findAll().forEach(book -> {
                String correctUrl = COVER_URLS.get(book.getTitle());
                if (correctUrl != null && !correctUrl.equals(book.getCoverImageUrl())) {
                    book.setCoverImageUrl(correctUrl);
                    bookRepository.save(book);
                    System.out.println("[BookedIn] Updated cover for: " + book.getTitle());
                }
            });
        };
    }
}
