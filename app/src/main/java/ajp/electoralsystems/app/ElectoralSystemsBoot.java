package ajp.electoralsystems.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import ajp.electoralsystems.app.controller.Controller;

/**
 * @author Andres Jimenez Penalver
 */
@SpringBootApplication
@ImportResource("applicationContext.xml")
public class ElectoralSystemsBoot implements CommandLineRunner {

	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(ElectoralSystemsBoot.class);
	}

	@Override
	public void run(String... args) throws Exception {
		Controller o = context.getBean("controller", Controller.class);
		o.handleEvent("configInit");
	}

}