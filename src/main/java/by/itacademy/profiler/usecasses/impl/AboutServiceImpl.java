package by.itacademy.profiler.usecasses.impl;

import by.itacademy.profiler.persistence.model.About;
import by.itacademy.profiler.persistence.repository.AboutRepository;
import by.itacademy.profiler.usecasses.AboutService;
import by.itacademy.profiler.usecasses.dto.AboutDto;
import by.itacademy.profiler.usecasses.mapper.AboutMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutMapper aboutMapper;
    private final AboutRepository aboutRepository;

    @Override
    public AboutDto add(String cvUUID, AboutDto aboutDto) {
        About about = aboutMapper.toEntity(aboutDto);
        About savedAbout = aboutRepository.save(about);
        return aboutMapper.toDto(savedAbout);
    }
}
