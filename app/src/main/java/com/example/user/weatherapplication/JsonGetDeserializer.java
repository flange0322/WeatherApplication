package com.example.user.weatherapplication;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class JsonGetDeserializer implements JsonDeserializer<JsonGet> {

    public JsonGet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        JsonObject coordObj = jsonObject.get("coord").getAsJsonObject();
        Double lon = coordObj.get("lon").getAsDouble();
        Double lat = coordObj.get("lat").getAsDouble();

        JsonObject weatherObj = jsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject();

        String main = weatherObj.get("main").getAsString();

        JsonObject mainObj = jsonObject.get("main").getAsJsonObject();
        Double temp = mainObj.get("temp").getAsDouble();
        int pressure = mainObj.get("pressure").getAsInt();

        JsonObject windObj = jsonObject.get("wind").getAsJsonObject();
        Double speed = windObj.get("speed").getAsDouble();

        String name = jsonObject.get("name").getAsString();

        return new JsonGet(lon,lat,main,temp,pressure,speed,name);
    }
}
