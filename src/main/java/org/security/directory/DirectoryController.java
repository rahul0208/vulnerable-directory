package org.security.directory;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class DirectoryController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    SearchDAO searchDAO;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("noSearch", true);
        return "index";
    }

    @PostMapping("/")
    public String search(@RequestParam Map<String, String> body, Model model) {
        model.addAttribute("zipCode", body.get("zipCode"));
        List<Object[]> results = searchDAO.findByZipCode(body.get("zipCode"));
        model.addAttribute("results", results);
        return "index";
    }
}
