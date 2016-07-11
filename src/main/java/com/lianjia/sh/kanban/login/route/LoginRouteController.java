package com.lianjia.sh.kanban.login.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ouyang
 * @since 2016-07-10 18:08
 */
@Controller
public class LoginRouteController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }
}
