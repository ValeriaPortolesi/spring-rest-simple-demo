package it.nextre.academy.restdemo.controller;

import it.nextre.academy.restdemo.entity.Movie;
import it.nextre.academy.restdemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/site/elenco-film")
    public String getElencoFilm(Model model){

        ModelAndView mav = new ModelAndView("elenco");

        List<Movie> movies = movieService.getAll();
        model.addAttribute("films",movies);
        model.addAttribute("title","Elenco film");
        return "elenco";
    }

    @GetMapping("/site/elenco-film2")
    public ModelAndView getElencoFilm2(){
        ModelAndView mav = new ModelAndView("elenco");
        List<Movie> movies = movieService.getAll();
        mav.addObject("films", movies);
        mav.addObject("titolo", "Elenco film");
        mav.setViewName("elenco"); //qua o nel costruttore
        return mav;
    }


    @GetMapping("/site/movie/add")
    public ModelAndView getAddMovie(){
        ModelAndView mav = new ModelAndView("add_movie");
        mav.addObject("titolo","Nuovo film");
        return mav;
    }
}
