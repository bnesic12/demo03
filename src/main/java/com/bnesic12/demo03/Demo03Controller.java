package com.bnesic12.demo03;

import com.bnesic12.demo03.dto.DNetEntity;
import com.bnesic12.demo03.dto.ScrabbleEntity;
import com.bnesic12.demo03.services.IDijkstraService;
import com.bnesic12.demo03.services.IScrabbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Demo03Controller {

    @Autowired
    private IScrabbleService scrabbleService;

    @Autowired
    private IDijkstraService pathService;

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

    @RequestMapping(value="/findShortestPath", method=RequestMethod.GET)
    public String findShortestPath(Model model) {
        // Generates default network, finds shortest path A->F
        DNetEntity dne = pathService.getDNet();
        dne.calculatePath();
        model.addAttribute("dne", dne);

        Object[] oNodes = dne.getdNet().getNet().keySet().toArray();
        List<String> dNodes = new ArrayList<>();
        for(Object oNode : oNodes) {
            dNodes.add((String) oNode);
        }
        model.addAttribute("nodes", dNodes);

        return "findShortestPath";
    }

    @PostMapping(value="/findShortestPath")
    public String findPath(
            @ModelAttribute("dne") DNetEntity dne,
            Model model) {
        DNetEntity dneNew = pathService.getDNet();
        dneNew.setStartNode(dne.getStartNode());
        dneNew.setDestNode(dne.getDestNode());
        dneNew.resetNet();
        dneNew.calculatePath();
        model.addAttribute("dne", dneNew);

        Object[] oNodes = dneNew.getdNet().getNet().keySet().toArray();
        List<String> dNodes = new ArrayList<>();
        for(Object oNode : oNodes) {
            dNodes.add((String) oNode);
        }
        model.addAttribute("nodes", dNodes);

        return "findShortestPath";
    }

    @RequestMapping("/")
    public String index() {
        return "root";
    }
}
