package by.itacademy.profiler.api.controllers;


import by.itacademy.profiler.usecasses.dto.AboutDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutRestController {

    @PostMapping("/api/v1/cvs/{uuid}/about")
    public void addAbout(@PathVariable String uuid, @RequestBody @Valid AboutDto aboutDto) {

    }
}
