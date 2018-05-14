package com.maxnerva.maxbase.rest;

import com.maxnerva.maxbase.mapper.UserRepository;
import com.maxnerva.maxbase.msg.ObjectRestResponse;
import com.maxnerva.maxbase.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ObjectRestResponse<User> validate(@RequestParam(value = "username", required = true) String username,
                                             @RequestParam(value = "password", required = true) String password)
    {
        User user= this.userRepository.findByUsername(username);
        if (!password.isEmpty() && password.equals(user.getPassword()))
        {
            System.out.println(user.getUsername());
            return new ObjectRestResponse().date(user);
        }
        else
        {
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            System.out.println("username or password is not correct.");
            objectRestResponse.setData("");
            objectRestResponse.setMessage("username or password is not correct.");
            return new ObjectRestResponse();
        }

    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id)
    {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }
}
