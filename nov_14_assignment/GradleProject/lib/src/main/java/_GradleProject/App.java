package _GradleProject;

import org.apache.log4j.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.debug("Debug message");
        logger.error("Error occurred");

        System.out.println("Check logs/app.log for file logs.");
    }
}