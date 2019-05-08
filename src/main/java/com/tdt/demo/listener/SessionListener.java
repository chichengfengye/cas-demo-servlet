package com.tdt.demo.listener;

import com.tdt.demo.util.SessionUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        SessionUtil.addSession((String) session.getAttribute("username"),session);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        SessionUtil.removeSession((String) session.getAttribute("username"));
    }
}
