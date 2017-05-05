package DockerCreater;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/4.
 */


public class main {




    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        List<String> test2 = new ArrayList<>();


        test2.add("456");
        test2.addAll(test);
        System.out.println(test2);


    }
}
