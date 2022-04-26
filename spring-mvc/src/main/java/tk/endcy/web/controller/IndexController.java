package tk.endcy.web.controller;

import tk.endcy.framework.annotation.MyController;
import tk.endcy.framework.annotation.MyRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:37
 */
@MyController
public class IndexController {
    @MyRequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
