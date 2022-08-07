package vttp.day17.day17boardgames.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Boardgame {

    // 1. generate getters and setters
    private Integer id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    // 2. Create method for reader and read json object
    public static Boardgame create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(strReader);
        return create(reader.readObject());
    }

    // 3. Create bg from string
    public static Boardgame create(JsonObject jo) {
        Boardgame bg = new Boardgame();
        bg.setId(jo.getInt("id"));
        bg.setName(jo.getString("name"));
        bg.setYear(jo.getInt("year"));
        bg.setRanking(jo.getInt("ranking"));
        bg.setUsersRated(jo.getInt("users_rated"));
        bg.setUrl(jo.getString("url"));
        bg.setImage(jo.getString("image"));

        return bg;
    }

    // 4. Get json as output
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("year", year)
            .add("ranking", ranking)
            .add("users_rated", usersRated)
            .add("url", url)
            .add("image", image)
            .build();
    }
    
}
