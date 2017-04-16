package DockerCreater;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jon on 2017/4/16.
 */

@CrossOrigin
@RestController
public class ImageController {

    private String return_str;

    @RequestMapping(value = "/images/list",method = RequestMethod.GET)
    public String hello(){
        return_str= "[{\"name\":\"ubuntu\",\"version\":\"1.1.0\"},{\"name\":\"ubuntu\",\"version\":\"1.1.0\"}]";
        return return_str;
    }

}
