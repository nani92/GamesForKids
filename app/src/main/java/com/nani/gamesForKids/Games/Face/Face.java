package com.nani.gamesForKids.Games.Face;

import android.graphics.Rect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class Face {
    private HashMap<String, List<Rect>> facePartListHashMap;

    public Face(JSONObject faceJsonObject) throws JSONException {
        this.facePartListHashMap = new HashMap<>();
        Iterator jsonKeys = faceJsonObject.keys();

        while (jsonKeys.hasNext()) {
            String jsonKey = jsonKeys.next().toString();

            if (jsonKey.equals("id")) {
                continue;
            }

            JSONArray jsonArray = faceJsonObject.getJSONArray(jsonKey);
            facePartListHashMap.put(jsonKey, getRects(jsonArray));
        }

    }

    public HashMap<String, List<Rect>> getFacePartListHashMap() {
        return facePartListHashMap;
    }

    private List<Rect> getRects(JSONArray jsonArray) throws JSONException {
        List<Rect> rects = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            rects.add(getRectForJsonObject(jsonArray.getJSONObject(i)));
        }

        return rects;
    }

    private Rect getRectForJsonObject(JSONObject rectJsonObject) throws JSONException {
        return new Rect(
                rectJsonObject.getInt("left"),
                rectJsonObject.getInt("top"),
                rectJsonObject.getInt("right"),
                rectJsonObject.getInt("bottom"));
    }
}