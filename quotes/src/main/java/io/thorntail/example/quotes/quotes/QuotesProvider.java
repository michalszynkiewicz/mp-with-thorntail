package io.thorntail.example.quotes.quotes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/5/18
 */
@ApplicationScoped
public class QuotesProvider {

    private static final int QUOTES_AMOUNT = 3;

    private List<Quote> quotes = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() throws IOException {
        try (InputStream stream = getClass().getResourceAsStream("/quotes.txt");
             InputStreamReader streamReader = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] quote = line.split(":");
                addQuote(quote[0].trim(), quote[1].trim());
            }
        }
    }

    private void addQuote(String author, String content) {
        Quote quote = new Quote();
        quote.setAuthor(author);
        quote.setContent(content);
        quotes.add(quote);
    }

    public List<Quote> getQuotes() {
        Random random = new Random();
        List<Integer> chosenIndexes = new ArrayList<>();

        while (chosenIndexes.size() < QUOTES_AMOUNT) {
            int randomNumber = random.nextInt(quotes.size());
            if (!chosenIndexes.contains(randomNumber)) {
                chosenIndexes.add(randomNumber);
            }
        }

        return chosenIndexes.stream()
                .map(quotes::get)
                .collect(Collectors.toList());
    }
}
