package DockerCreater;

import DockerCreater.entity.AloneDependency;
import DockerCreater.entity.Dependency;
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

        dependencyRepository.save(new Dependency("junit", "4.12", "default", Arrays.asList(
                new AloneDependency("hamcrest-core", "1.3", "default")
        )));
        dependencyRepository.save(new Dependency("scala-library", "2.13.0-M1", "default"));
        dependencyRepository.save(new Dependency("junit", "4.11", "default", Arrays.asList(
                new AloneDependency("hamcrest-core", "1.3", "default")
        )));
        dependencyRepository.save(new Dependency("hamcrest-core", "1.3", "default"));

        imageRepository.deleteAll();

        imageRepository.save(new Image("debian", "1.0", "system"));

        //一下为实验用虚构依赖和镜像文件

        dependencyRepository.save(new Dependency("alone-one"));
        dependencyRepository.save(new Dependency("alone-two"));
        dependencyRepository.save(new Dependency("alone-three"));
        dependencyRepository.save(new Dependency("alone-four"));
        dependencyRepository.save(new Dependency("twist-one",Arrays.asList(
                new AloneDependency("twist-two"),
                new AloneDependency("twist-three")
        )));
        dependencyRepository.save(new Dependency("twist-two"));
        dependencyRepository.save(new Dependency("twist-three",Arrays.asList(
                new AloneDependency("twist-four")
        )));
        dependencyRepository.save(new Dependency("twist-four"));

        imageRepository.save(new Image("image-one",Arrays.asList(
                new AloneDependency("alone-one")
        )));

        imageRepository.save(new Image("image-two",Arrays.asList(
                new AloneDependency("alone-one"),
                new AloneDependency("twist-one")
        )));

        imageRepository.save(new Image("image-three",Arrays.asList(
                new AloneDependency("alone-one"),
                new AloneDependency("alone-four"),
                new AloneDependency("twist-one")
        )));

        imageRepository.save(new Image("image-four",Arrays.asList(
                new AloneDependency("alone-one"),
                new AloneDependency("alone-two"),
                new AloneDependency("twist-one")
        )));



    }


}
