package com.tdt.demo.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionUtil {
    private static HashMap<String, HttpSession> sessionCache = new HashMap<String, HttpSession>();

    public static void addSession(String username, HttpSession httpSession) {
        sessionCache.put(username, httpSession);
    }

    public static boolean removeSession(String username) {
        HttpSession session = sessionCache.remove(username);
        return session != null;
    }
}
