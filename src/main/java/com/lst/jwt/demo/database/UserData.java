package com.lst.jwt.demo.database;

import com.lst.jwt.demo.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    public static Map<String, User> userMap = new HashMap<>(8);
    static {
        userMap.put("stli",new User("stli"));
        userMap.put("tiger",new User("tiger"));
        userMap.put("lst",new User("lst"));
    }
}
