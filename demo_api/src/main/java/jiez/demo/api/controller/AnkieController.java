package jiez.demo.api.controller;

import jiez.demo.api.service.IAnkieToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/ankie")
public class AnkieController {

    @Autowired
    private IAnkieToolService ankieToolService;

    @RequestMapping(value = "/generateWeeklyPublication", method = RequestMethod.POST)
    public String generateWeeklyPublication (@RequestParam("file") MultipartFile file) throws Exception {
        return ankieToolService.generateWeeklyPublication(file);
    }

    @RequestMapping(value = "/generateWeeklyPublication", method = RequestMethod.GET)
    public String an () throws Exception {
        return "fuck";
    }
}
