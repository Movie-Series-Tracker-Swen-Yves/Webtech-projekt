package htw.webtech.todo.app;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    public Controller() {
    }

    @GetMapping({"/SwenIsKing"})
    public String home() {
        return "<html><body>\n  <h1>Test </h1>\n  <p>Baka! Sharingan Rasengang ITADAKIMASU. Tasgete Onichan Baka yaro</p>\n</body></html>\n";
    }
}
