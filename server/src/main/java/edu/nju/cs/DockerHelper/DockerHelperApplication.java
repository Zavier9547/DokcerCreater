package edu.nju.cs.DockerHelper;

import edu.nju.cs.DockerHelper.entity.ConDependency;
import edu.nju.cs.DockerHelper.entity.Dependency;
import edu.nju.cs.DockerHelper.entity.Image;
import edu.nju.cs.DockerHelper.repository.DependencyRepo;
import edu.nju.cs.DockerHelper.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DockerHelperApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DockerHelperApplication.class, args);
	}

	@Autowired
	private DependencyRepo dR;

	@Autowired
	private ImageRepo iR;

	@Override
	public void run(String... strings) throws Exception {
		dR.deleteAll();
		iR.deleteAll();

		Dependency one1 = new Dependency("one");
		Dependency one2 = new Dependency("one","2.0");
		Dependency two1 = new Dependency("two", Arrays.asList(new ConDependency("one",">= 1.0")));
		Dependency two2 = new Dependency("two","2.0","i386", Arrays.asList(new ConDependency("one",">= 1.0")));
		Dependency three = new Dependency("three",
				Arrays.asList(new ConDependency("one","= 1.0")),
				Arrays.asList(new ConDependency("four","<< 2.0")));
		Dependency four = new Dependency("four");
		Dependency five = new Dependency("five", Arrays.asList(new ConDependency("two",">= 2.0")));
		Dependency six = new Dependency("six");

		dR.save(one1);
		dR.save(one2);
		dR.save(two1);
		dR.save(two2);
		dR.save(three);
		dR.save(four);
		dR.save(five);
		dR.save(six);

		iR.save(new Image("image-one",Arrays.asList(one1)));
		iR.save(new Image("image-two",Arrays.asList(one1,three)));
		iR.save(new Image("image-three",Arrays.asList(one1,two1,three)));
		iR.save(new Image("image-four",Arrays.asList(one1,two2,three)));
	}
}
