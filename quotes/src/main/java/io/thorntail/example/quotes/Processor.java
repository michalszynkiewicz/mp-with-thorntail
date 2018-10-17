package io.thorntail.example.quotes;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 10/9/18
 */
@ApplicationScoped
public class Processor {

    private Long totalProcessingTime = 0L;

    public Long getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void process() {
        Random random = new Random();
        try {
            int processingTime = random.nextInt(10) * 100;
            Thread.sleep(processingTime);
            totalProcessingTime += processingTime;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
