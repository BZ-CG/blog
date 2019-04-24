package cn.edu.pzhu.blog.web.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:CG
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView toAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/AdminLTE/starter");
        return model;
    }

    @RequestMapping(value = "/toMessageBoard", method = RequestMethod.GET)
    public ModelAndView toMessageBoard() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/blog/messageBoard");
        return model;
    }

    @RequestMapping(value = "/toDonationPage", method = RequestMethod.GET)
    public ModelAndView toDonationPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/blog/donationPage");
        return model;
    }

    @RequestMapping(value = "toaboutMe", method = RequestMethod.GET)
    public ModelAndView toAboutMe() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/blog/aboutMe");
        return model;
    }

    @RequestMapping(value = "/toBlogPage", method = RequestMethod.GET)
    public ModelAndView toBlogPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/blog/blogPage");

        return model;
    }
}
