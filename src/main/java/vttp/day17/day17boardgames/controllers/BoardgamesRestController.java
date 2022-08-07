package vttp.day17.day17boardgames.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.day17.day17boardgames.services.BoardgameService;

@RestController
@RequestMapping(path="/boardgame", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardgamesRestController {
      
    @Autowired
    private BoardgameService bgSvc;

    @GetMapping
    ResponseEntity<String> getBoardGames(
        @RequestParam(name="offset", defaultValue="0") Integer offset,
        @RequestParam(name="limit", defaultValue="5") Integer limit) {

        List<String> allKeys = bgSvc.keys().subList(offset, offset + limit);
        List<String> keyRange = allKeys.stream()
            .map(k -> "/boardgame/%s".formatted(k))
            .toList();

        JsonArray arr = Json.createArrayBuilder(keyRange).build();

        return ResponseEntity.ok(arr.toString());
    }

        @GetMapping(path="count")
        ResponseEntity<String> getBoardgamesCount() {
            Integer count = bgSvc.count();

            JsonObject payload = Json.createObjectBuilder()
                .add("count", count)
                .build();

            return ResponseEntity.ok(payload.toString());
        }
}

// http://localhost:8080/boardgame?offset=3&limit=9
// returns: ["/boardgame/4","/boardgame/5","/boardgame/6","/boardgame/7","/boardgame/8","/boardgame/9","/boardgame/10","/boardgame/11","/boardgame/12"]
