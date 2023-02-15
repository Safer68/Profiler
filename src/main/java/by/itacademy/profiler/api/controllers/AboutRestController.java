package by.itacademy.profiler.api.controllers;


import by.itacademy.profiler.usecasses.AboutService;
import by.itacademy.profiler.usecasses.dto.AboutDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AboutRestController {

    private final AboutService aboutService;

    @PostMapping("/api/v1/cvs/{uuid}/about")
    public ResponseEntity<AboutDto> addAbout(@PathVariable String uuid, @RequestBody @Valid AboutDto aboutDto) {
        AboutDto addedAboutDto = aboutService.add(uuid, aboutDto);
        return new ResponseEntity<>(addedAboutDto, HttpStatus.OK);
    }
}
