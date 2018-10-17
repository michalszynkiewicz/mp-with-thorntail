package io.thorntail.example.ads;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/4/18
 */
@Path("/config")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ConfigResource {

    private Config config = new Config();

    @GET
    public Config get() {
        return config;
    }

    @POST
    public void set(Config config) {
        this.config = config;
    }

    public static class Config {
        private int time = 0 /* seconds */;
        private double successRate = 1;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public double getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(double successRate) {
            this.successRate = successRate;
        }
    }
}
