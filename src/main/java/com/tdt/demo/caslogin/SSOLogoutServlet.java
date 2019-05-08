package com.tdt.demo.caslogin;

import com.tdt.demo.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "wholeLogout", urlPatterns = "/wholeLogout")
public class SSOLogoutServlet extends HttpServlet {
    private static String casLogoutUrl = null;
    private static Properties p = null;
    private static final String CONFIG_FILE = "cas_url.properties";

    @Override
    public void init() throws ServletException {
        p = FileUtil.getLocalProperAddr(CONFIG_FILE);
        casLogoutUrl = p.getProperty("cas.server.logout.url");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("wholeLogout");
        String targetUserName = req.getParameter("u");
        logout(targetUserName, req, resp);

    }

    /**
     * 登出
     *  1. 系統自己登出
     *  2. 服務中心登出
     */
    private void logout(String targetUserName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //獲取用戶的session，失效session
        System.out.println("================ user [ "+ targetUserName +" ] is going whole logout ======================");
        HttpSession session = getSessionByUserName(targetUserName);
        if (session != null) {
            session.invalidate();
        }

        //跳轉服務中心的登出接口
        resp.sendRedirect(casLogoutUrl);

    }

    /**
     * 依據用戶名獲取session
     *
     * @return
     */
    private HttpSession getSessionByUserName(String username) {


        return null;
    }

    /**
     * 依據sessionId獲取session
     *
     * @return
     */
    private HttpSession getSessionBySessionId(String sessionId) {


        return null;
    }

}
