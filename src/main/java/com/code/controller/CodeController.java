package com.code.controller;

import com.basic.cache.service.CacheManager;
import com.code.service.CodeService;
import com.code.vo.Code;
import com.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("code")
public class CodeController {
    @Autowired
    CodeService service;
    @Autowired
    CacheManager cacheManager;

    @RequestMapping(value = "code", method = {RequestMethod.GET})
    public ModelAndView code(Code code) {
        ModelAndView mav = new ModelAndView();
        List<String> types = service.types();
        mav.addObject("types", types);

        if (code.getCodeId() != null) {
            code = service.get(code);
            mav.addObject("code", code);
        }
        mav.setViewName("code/code");
        return mav;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public int add(Code code) {
        try {
            service.add(code);
            cacheManager.clearCodes();
            return code.getCodeId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public int delete(Code code) {
        try {
            return service.delete(code);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public int update(Code code) {
        try {
            code = service.update(code);
            cacheManager.clearCodes();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest request, Code code) {

        ModelAndView mav = new ModelAndView();
        List<String> types = service.types();
        mav.addObject("types", types);

        List<Code> list = service.list(code);
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (!sessionUser.getRole().equals("0")) {
            list = Collections.emptyList();
        }
        mav.addObject("list", list);

        mav.addObject("code", code);
        mav.setViewName("code/list");
        return mav;
    }

    @RequestMapping("listByType")
    @ResponseBody
    public List<Code> listByType(Code code) {
        try {
            List<Code> codes = cacheManager.getCodes(code.getType(), code.getCode());
            return codes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
