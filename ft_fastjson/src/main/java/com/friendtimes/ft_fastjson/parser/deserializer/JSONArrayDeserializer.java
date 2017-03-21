package com.friendtimes.ft_fastjson.parser.deserializer;


import com.friendtimes.ft_fastjson.JSONArray;
import com.friendtimes.ft_fastjson.parser.DefaultJSONParser;
import com.friendtimes.ft_fastjson.parser.JSONToken;

import java.lang.reflect.Type;


public class JSONArrayDeserializer implements ObjectDeserializer {
    public final static JSONArrayDeserializer instance = new JSONArrayDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        JSONArray array = new JSONArray();
        parser.parseArray(array);
        return (T) array;
    }

    public int getFastMatchToken() {
        return JSONToken.LBRACKET;
    }
}
