package io.thorntail.example.quotes.ads;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/8/18
 */
@ApplicationScoped
public class AdRetriever {

    public Ad getAd() {
        return mockAd();
    }

    private Ad mockAd() {
        Ad ad = new Ad();

        ad.setContent("Keep an eye on what's coming in Thorntail v4!");
        ad.setUrl("http://thorntail.io");
        return ad;
    }
}
