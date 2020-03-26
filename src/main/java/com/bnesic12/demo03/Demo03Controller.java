package com.bnesic12.demo03;

import com.bnesic12.demo03.dto.ScrabbleEntity;
import com.bnesic12.demo03.services.IScrabbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Demo03Controller {

    @Autowired
    private IScrabbleService scrabbleService;

    @RequestMapping(value="/bnesic12", method= RequestMethod.GET)
    public String bnesic12Home(Model model) {
        return "bnesic12";
    }

    @RequestMapping(value="/techDescription", method= RequestMethod.GET)
    public String techDescription(Model model) {
        return "techDescription";
    }

    @RequestMapping(value="/playScrabble", method=RequestMethod.GET)
    public String playScrabble(Model model) {
        ScrabbleEntity scrabbleEntityObj = scrabbleService.getScrabble();
        model.addAttribute("scrabbleEntityObj", scrabbleEntityObj);
        return "playScrabble";
    }

    @PostMapping(value = "/playScrabble")
    public String doScrabble(
            @ModelAttribute("scrabbleEntityObj") ScrabbleEntity scrabbleEntityObj,
            Model model) {
        return "playScrabble";
    }

    @RequestMapping("/")
    public String index() {
        return "root";
    }
}
