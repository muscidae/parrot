package com.muscidae.parrot.common.util.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuxueli
 * @date 2015-12-12 18:01:06
 * @description Cookie.Utils
 */
public final class CookieUtils {

    private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    private static final String COOKIE_PATH = "/";

    private CookieUtils(){ }

    /**
     * 保存
     * @param response
     * @param key
     * @param value
     * @param remember
     */
    public void set(HttpServletResponse response, String key, String value, boolean remember) {
        int age = remember? COOKIE_MAX_AGE : -1;
        set(response, key, value, null, COOKIE_PATH, age, true);
    }

    /**
     * 查询value
     * @param request
     * @param key
     */
    public String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        return null == cookie? null : cookie.getValue();
    }

    /**
     * Cookie查询
     * @param request
     * @param key
     */
    private Cookie get(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || 0 == cookies.length)
            return null;
        for (Cookie cookie : cookies)
            if (key.equals(cookie.getName()))
                return cookie;
        return null;
    }

    /**
     * 删除Cookie
     * @param request
     * @param response
     * @param key
     */
    public void remove(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = get(request, key);
        if (null != cookie)
            set(response, key, "", null, COOKIE_PATH, 0, true);
    }

    /**
     * 保存
     * @param response
     * @param key
     * @param value
     * @param maxAge
     */
    private void set(HttpServletResponse response, String key, String value,
                     String domain, String path, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(key, value);
        if (null != domain)
            cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public enum Singleton{
        INSTANCE;
        private CookieUtils cookieUtils;
        Singleton(){ cookieUtils = new CookieUtils(); }
        public CookieUtils newInstance(){ return cookieUtils; }
    }

}
