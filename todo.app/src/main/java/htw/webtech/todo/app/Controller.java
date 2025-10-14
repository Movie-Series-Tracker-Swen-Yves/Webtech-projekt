package htw.webtech.todo.app;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @GetMapping("/progress")
    public List<Progress> getAllProgress() {
        return List.of(
                new Progress("Breaking Bad", "Serie", 2, 5),
                new Progress("Inception", "Film", 0, 0)
        );
    }
}
