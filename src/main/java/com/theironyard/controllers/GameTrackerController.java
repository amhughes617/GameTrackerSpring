package com.theironyard.controllers;

import com.theironyard.entities.Game;
import com.theironyard.services.GameRepository;
import com.theironyard.entities.User;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by alexanderhughes on 3/8/16.
 */
@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;
    @Autowired
    UserRepository users;
    @PostConstruct
    public void init() {
        System.out.println("Started Up");
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String genre, Integer releaseYear, String platform, HttpSession session) {
        User user = users.findByUserName((String) session.getAttribute("userName"));
        if (user != null) {
            model.addAttribute("user", user);
        }
        if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }
        else if (genre != null && releaseYear  != null) {
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYear(user, genre, releaseYear));
        }
        else if (genre != null) {
            model.addAttribute("games", games.findByGenreAndUser(genre, user));
        }
        else {
            model.addAttribute("games", games.findAll());

        }
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, int gameYear, String gameGenre) {
        User user = users.findByUserName((String) session.getAttribute("userName"));
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear, user);//should make this private and use setter for this, but trying to keep up with zac
        games.save(game);
        return "redirect:/";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findByUserName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("userName", userName);

        return "redirect:/";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
