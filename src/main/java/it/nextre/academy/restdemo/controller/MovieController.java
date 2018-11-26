package it.nextre.academy.restdemo.controller;

import it.nextre.academy.restdemo.entity.Movie;
import it.nextre.academy.restdemo.exception.MovieException;
import it.nextre.academy.restdemo.service.MovieService;
import it.nextre.academy.restdemo.structure.RispostaJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    MovieService movieService;

    //@RequestMapping(name="/", method = RequestMethod.GET)
    @GetMapping({"/"})
    ResponseEntity<RispostaJson> getTutti() {
        log.debug("Sono in getTutti...");
        List<Movie> films = movieService.getAll();
        RispostaJson risp = new RispostaJson();
        risp.setStato(100);
        risp.setMessaggio("success");
        risp.setResponse(films);
        return new ResponseEntity(risp,HttpStatus.OK);
    }



    @GetMapping("/{id}")
    Movie getByID(@PathVariable("id") int mid) {
        return movieService.getByID(mid);
    }


    @GetMapping("/cerca")
    List<Movie> getByID(
            @RequestParam(value = "what", required = true) String what, @RequestParam(value = "where", required = true) String where) {
        if (what != null && what.length() > 0 &&
                where != null && where.length() > 0) {

            switch (where) {
                case "id":
                    try {
                        return Arrays.asList(movieService.getByID(Integer.parseInt(what)));
                    } catch (NumberFormatException e) {
                        return new ArrayList<>();
                    }
                case "titolo":
                    return movieService.getTitle(what);
                case "anno":
                    try {
                        return movieService.getYear(Integer.parseInt(what));
                    } catch (NumberFormatException e) {
                        return new ArrayList<>();
                    }
                case "regista":
                    return movieService.getDirector(what);
                default:
                    return new ArrayList<>();
            }

        }
        return new ArrayList<>();
    }




    /*
    * Inserire nel body della chiamata POST in formato JSON il seguente:
    *
      {
	    "mID":null,
	    "title":"Il signore degli anelli",
	    "year":null,
	    "director":null
      }
    *
    *
    * */
    @PostMapping("/")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        System.out.println("Sono in movie Post + " + movie);
        try {
            return new ResponseEntity(movieService.addMovie(movie), HttpStatus.OK);
        }catch(MovieException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable("id") Integer mid, @RequestBody Movie movie){
        //todo finire con controllo prenventivo x fare la bad request
        //System.out.println("ID: "+mid); System.out.println("Movie: " + movie);
        if (movie != null && movie.getMID()==0 && mid!=0 ){
            movie.setMID(mid);
        }

        if (mid!=movie.getMID()){
            return new ResponseEntity(movieService.updateMovie(mid, movie),HttpStatus.OK);
        }else{
            return new ResponseEntity(movieService.updateMovie(movie),HttpStatus.OK);
        }

    }




    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable("id") Integer mid){
        if(mid!=null){
            return new ResponseEntity(movieService.deleteById(mid),HttpStatus.OK);
        }
        return new ResponseEntity(new MovieException("Movie non valido"), HttpStatus.BAD_REQUEST);
    }










}//end class
