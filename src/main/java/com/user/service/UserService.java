package com.user.service;

import com.basic.service.BaseService;
import com.user.vo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    Logger log = Logger.getLogger(this.getClass());
}
