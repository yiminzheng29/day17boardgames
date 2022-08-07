package vttp.day17.day17boardgames.repositories;

import java.util.LinkedList;
import java.util.List;
// import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardgameRepository {
      
      // 1. autowire to redis template
      @Autowired
      @Qualifier("redislab")
      private RedisTemplate<String, String> redisTemplate;

      public Integer count() {
            Set<String> keys = redisTemplate.keys("[0-9]*");
            return keys.size();
      }

      // convert set to a sorted list
      public List<String> keys() {
            Set<String> keys = redisTemplate.keys("[0-9]*");
            List<String> result = new LinkedList<>(keys);
            return result.stream()
                  .map(v -> Integer.parseInt(v))
                  .sorted()
                  .map(v -> v.toString())
                  .toList();
      }

      public String get (String id) {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(id);
      }

      // When to use optional?
      // public Optional<String> get (String id) {
      //       ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
      //       if (valueOps.get(id)==null) {
      //             return Optional.empty(); // empty box
      //       } return Optional.of(valueOps.get(id));

      // }
      
}
