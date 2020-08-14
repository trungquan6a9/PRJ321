/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.listener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author johny
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> siteMap = new HashMap<>();

//        siteMap.put("", "login.html");
//        siteMap.put("try", "login.html");//IE chuoi rong se refresh lại trang -> dat cho no 1 cai ten
//
//        siteMap.put("login", "LoginServlet");
//        siteMap.put("search", "SearchServlet");
//        siteMap.put("signup", "createNewAccount.html");
        ServletContext context = sce.getServletContext();

        //lấy đường dẫn
        String realPath = context.getRealPath("/WEB-INF/siteMap.txt");
//        System.out.println("**********Real Path = " + realPath + "**********");

        Scanner sc = null;
        try {
            File file = new File(realPath);
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String tmp = sc.nextLine();
                String[] arrayOfSiteMapFile = tmp.split("=");
                for (int i = 0; i < arrayOfSiteMapFile.length; i++) {
                    siteMap.put(arrayOfSiteMapFile[i], arrayOfSiteMapFile[++i]);
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.setAttribute("SITE_MAP", siteMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
