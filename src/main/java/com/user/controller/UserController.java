package com.user.controller;

import com.basic.cache.service.CacheManager;
import com.basic.vo.Result;
import com.code.vo.Code;
import com.user.service.UserService;
import com.user.vo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {
    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    CacheManager cacheManager;

    @RequestMapping("list")
    public ModelAndView list(User user) {
        ModelAndView mav = new ModelAndView();

        constructModelAndView(user, mav);

        List<User> users = userService.list(user);
        Map<String, String> teamMap = cacheManager.getCodeMap("team");
        Map<String, String> levelMap = cacheManager.getCodeMap("level");
        for(User u: users){
            u.setTeam(teamMap.get(u.getTeam()));
            u.setLevel(levelMap.get(u.getLevel()));
        }

        mav.addObject("users", users);
        mav.setViewName("user/list");
        return mav;
    }

    private void constructModelAndView(User user, ModelAndView mav) {
        mav.addObject("teams", cacheManager.getCodes("team"));
        mav.addObject("levels", cacheManager.getCodes("level"));
        if (user.getUserId() != null) {
            user = userService.get(user);
            mav.addObject("user", user);
        }
    }

    @RequestMapping(value = "user", method = {RequestMethod.GET})
    public ModelAndView user(User user) {
        ModelAndView mav = new ModelAndView();

        constructModelAndView(user, mav);

        mav.setViewName("user/user");
        return mav;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public Result add(User user) {
        try {
            userService.add(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("保存失败");
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result delete(User user) {
        try {
            userService.delete(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("删除失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(User user) {
        try {
            userService.update(user);
            return new Result();
        } catch (Exception e) {
            log.error(e);
            return new Result("更新失败");
        }
    }
}
