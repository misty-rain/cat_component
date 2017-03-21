package com.friendtimes.ft_fastjson.parser.deserializer;


import com.friendtimes.ft_fastjson.parser.DefaultJSONParser;

import java.lang.reflect.Type;


public interface ObjectDeserializer {
    <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName);
    
    int getFastMatchToken();
}
