package vttp.day17.day17boardgames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.day17.day17boardgames.models.Boardgame;
import vttp.day17.day17boardgames.repositories.BoardgameRepository;

@Service
public class BoardgameService {
      
      @Autowired
      private BoardgameRepository boardgameRepo;

      public Integer count() {
            return boardgameRepo.count();
      }

      public List<String> keys() {
            return boardgameRepo.keys();
      }

      public Optional<Boardgame> getBoardgameById(Integer id) {
            return getBoardgameById(id.toString());
      }

      public Optional<Boardgame> getBoardgameById(String id) {
            String result = boardgameRepo.get(id);
            if (result==null) {
                  return Optional.empty();
            } return Optional.of(Boardgame.create(result));
      }

}
