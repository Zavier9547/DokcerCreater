package DockerCreater;

import DockerCreater.entity.Dependency;
import DockerCreater.entity.AloneDependency;
import DockerCreater.entity.Image;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private DependencyRepository dependencyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void run(String... strings) throws Exception {
        dependencyRepository.deleteAll();

        dependencyRepository.save(new Dependency("junit", "junit", "4.12", Arrays.asList(
                new AloneDependency("org.hamcrest", "hamcrest-core", "1.3")
        )));
        dependencyRepository.save(new Dependency("org.scala-lang", "scala-library", "2.13.0-M1"));
        dependencyRepository.save(new Dependency("junit", "junit", "4.11", Arrays.asList(
                new AloneDependency("org.hamcrest", "hamcrest-core", "1.3")
        )));

        imageRepository.deleteAll();

        imageRepository.save(new Image("debian", "linux", "1.0.0", Arrays.asList()));

        //一下为实验用虚构依赖和镜像文件

        dependencyRepository.save(new Dependency("alone", "alone_one"));
        dependencyRepository.save(new Dependency("alone", "alone_two"));
        dependencyRepository.save(new Dependency("twist", "twist_one",Arrays.asList(
                new AloneDependency("twist", "twist_two"),
                new AloneDependency("twist", "twist_three")
        )));
        dependencyRepository.save(new Dependency("twist", "twist_two"));
        dependencyRepository.save(new Dependency("twist", "twist_three",Arrays.asList(
                new AloneDependency("twist", "twist_four")
        )));
        dependencyRepository.save(new Dependency("twist", "twist_four"));

        imageRepository.save(new Image("image_one",Arrays.asList(
                new AloneDependency("alone","alone_one"),
                new AloneDependency("alone","alone_two"),
                new AloneDependency("twist","twist_one")
        )));
    }


}
