package io.thorntail.example.quotes;

import io.thorntail.example.quotes.ads.Ad;
import io.thorntail.example.quotes.ads.AdRetriever;
import io.thorntail.example.quotes.quotes.Quote;
import io.thorntail.example.quotes.quotes.QuotesProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/5/18
 */
@Path("/quotes")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
public class QuoteResource {

    @Inject
    private QuotesProvider quotesProvider;

    @Inject
    private AdRetriever adRetriever;

    @Inject
    private Processor processor;

    @GET
    public List<Quote> getQuotes() {
        List<Quote> resultList = new ArrayList<>();

        Ad ad = adRetriever.getAd();
        List<Quote> quotes = quotesProvider.getQuotes();
        Quote addAsQuote = adToQuote(ad);

        processor.process();

        resultList.add(addAsQuote);
        resultList.addAll(quotes);


        return resultList;
    }

    private Quote adToQuote(Ad ad) {
        Quote result = new Quote();
        result.setContent(ad.getContent());
        result.setUrl(ad.getUrl());
        result.setAuthor("Advertisement");
        return result;
    }

}
