package vttp.day17.day17boardgames.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.day17.day17boardgames.models.Boardgame;
import vttp.day17.day17boardgames.services.BoardgameService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/boardgame", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardgameRestController {

      @Autowired
      private BoardgameService bgSvc;
      
      @GetMapping(value="{gid}")
      public ResponseEntity<String> getBoardgame(@PathVariable String gid) {
            Optional<Boardgame> opt = bgSvc.getBoardgameById(gid);

            // check if opt is empty or has values
            if (opt.isEmpty()) {
                  JsonObject err = Json.createObjectBuilder()
                        .add("error", "ID %s not found".formatted(gid))
                        .build();
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(err.toString());
            } 

            // if not null, return boardgame as string
            Boardgame bg = opt.get();
            return ResponseEntity.ok(bg.toJson().toString());
      }
}

// http://localhost:8080/boardgame/3
// produces {"id":3,"name":"Samurai","year":1998,"ranking":188,"users_rated":13455,"url":"https://www.boardgamegeek.com/boardgame/3/samurai","image":"https://cf.geekdo-images.com/micro/img/4XUy5QxQQfMHjx7pm2JpkJrTdDQ=/fit-in/64x64/pic3211873.jpg"}
