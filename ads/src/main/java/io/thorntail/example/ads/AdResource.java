package io.thorntail.example.ads;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/4/18
 */
@Path("/ads")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AdResource {
    @Inject
    // it's not a very good practice to inject resources, don't do it in prod
    private ConfigResource configResource;

    private List<Ad> ads = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    public void init() {
        addAd("Try out Red Hat's MicroProfile implementation, Thorntail!", "http://thorntail.io");
        addAd("Check out Payara Micro, a microservices-ready version of Payara Server!", "https://www.payara.fish/payara_micro");
        addAd("Use MicroProfile with OpenLiberty", "https://openliberty.io/");
    }

    private void addAd(String content, String url) {
        Ad ad = new Ad();
        ad.setContent(content);
        ad.setUrl(url);
        ads.add(ad);
    }

    @GET
    public Response getAd() throws InterruptedException {
        mimicComputations();
        int adIndex = new Random().nextInt(ads.size());
        return Response.ok(ads.get(adIndex)).build();
    }

    private void mimicComputations() throws InterruptedException {
        long processingTime = configResource.get().getTime();
        System.out.println("processingTime: " + processingTime);
        if (processingTime > 0) {
            System.out.println("will sleep");
            Thread.sleep(processingTime * 1000L);
        }
        if (new Random().nextDouble() >= configResource.get().getSuccessRate()) {
            throw new RuntimeException("Forced server error");
        }
    }
}
