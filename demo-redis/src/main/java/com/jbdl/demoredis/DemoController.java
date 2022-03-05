package com.jbdl.demoredis;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String KEY_PREFIX = "person::";
    private static final String PERSON_LIST_KEY = "person_list";
    private static final String HASH_PREFIX_KEY = "person_hash::";

    // ------------------------- Value Operations---------------------------------------------------

    @PostMapping("/setValue")
    public void setValue(@Valid @RequestBody Person person) {
        String key = KEY_PREFIX + person.getId();
        redisTemplate.opsForValue().set(key, person);

    }

    @GetMapping("/getValue")
    public Person getValue(@RequestParam("id") int personId) {
        String key = KEY_PREFIX + personId;
        return (Person) redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/getAllValues")
    public List<Person> getAllPerson() {
        String pattern = KEY_PREFIX + "*";
        return redisTemplate.keys(pattern).stream()
                .sorted() // will sort the stream in ascending order of the id .
                .map(key -> redisTemplate.opsForValue().get(key))
                .map(object -> (Person) object)
                .collect(Collectors.toList());

    }

    @PutMapping("/expire")
    public Person expire(@RequestParam("id") int id , @RequestParam("timeInSec") int timeInSec){

        String pattern = KEY_PREFIX + id ;
        return (Person) redisTemplate.opsForValue().getAndExpire(pattern, Duration.ofSeconds(timeInSec));
    }

    @PutMapping("/setex")
    public void  setex(@RequestBody Person person , @RequestParam("timeInSec") long timeInSec)
    {
        String key = KEY_PREFIX + person.getId();
         redisTemplate.opsForValue().set(key, person ,Duration.ofSeconds(timeInSec));
    }
//
//    @PutMapping("/expireAt")
//    public void expireAt(@RequestParam("id") int id, @RequestParam("unixTimeStamp") int unixTimeStamp)
//    {
//        String key = KEY_PREFIX + id;
//
//        redisTemplate.opsForValue().expire
//    }
//
//    @GetMapping("/ttl")
//    public int ttl(@RequestParam("id") int id){
//        String pattern = KEY_PREFIX + id ;
//        return redisTemplate.opsForValue().   }




    //----------------------------------List Operations------------------------------------------------

    @PostMapping("/lpush")
    public void lpush(@Valid @RequestBody Person person) {
        String key = PERSON_LIST_KEY;
        redisTemplate.opsForList().leftPush(key, person);
    }

    @PostMapping("/rpush")
    public void rpush(@Valid @RequestBody Person person) {
        String key = PERSON_LIST_KEY;
        redisTemplate.opsForList().rightPush(key, person);

    }

    @DeleteMapping("/lpop")
    public List<Person> lpop(@Valid @RequestParam(value = "count", required = false, defaultValue = "1") int count) {
        String key = PERSON_LIST_KEY;
        return redisTemplate.opsForList().leftPop(key, count).stream()
                .map(obj -> (Person) obj)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/rpop")
    public List<Person> rpop(@Valid @RequestParam(value = "count", required = false, defaultValue = "1") int count) {
        String key = PERSON_LIST_KEY;
        return redisTemplate.opsForList().leftPop(key, count).stream()
                .map(obj -> (Person) obj)
                .collect(Collectors.toList());
    }

    @GetMapping("/lrange")
    public List<Person> lrange(@Valid @RequestParam(value = "start", required = false, defaultValue = "0") int start,
                               @RequestParam(value = "end", required = false, defaultValue = "-1") int end) {
        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end).stream()
                .map(obj -> (Person) obj)
                .collect(Collectors.toList());
    }

    //----------------------------------------Hash Operations ------------------------------------

    @PostMapping("/hmset")
    public void hmset(@Valid @RequestBody Person person) {
        String key = HASH_PREFIX_KEY + person.getId();

        Map map = objectMapper.convertValue(person, Map.class);
        redisTemplate.opsForHash().putAll(key, map);

    }

    @GetMapping("/hgetall")
    public Person hgetall(@RequestParam("id") int id) {
        String key = HASH_PREFIX_KEY + id;
        Map map =   redisTemplate.opsForHash().entries(key);
        return  objectMapper.convertValue(map, Person.class) ;
    }

    @DeleteMapping("/hdel")
    public ResponseEntity hdel(@RequestParam("id") int id , @RequestParam("field") String field)
    {
        String key = HASH_PREFIX_KEY + id ;
        redisTemplate.opsForHash().delete(key,field);
         return new ResponseEntity(HttpStatus.OK ) ;
    }
}
