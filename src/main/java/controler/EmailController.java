package controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class EmailController {
    private static Pattern pattern;
    private Matcher matcher;

    private static  final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";

    public  EmailController(){
        pattern = pattern.compile(EMAIL_REGEX);
    }

    @GetMapping()
    public ModelAndView ShowEmail(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PostMapping("/validate")
    public ModelAndView ValidateEmail(@RequestParam("email") String email){
        boolean isvalid = this.validate(email);
        String message;
        if (isvalid){
            message = email;
        }else {
            message = "Email is valid";
        }
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    private boolean validate(String regex) {
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
