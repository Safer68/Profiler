package by.itacademy.profiler.usecasses.mapper;

import by.itacademy.profiler.persistence.model.About;
import by.itacademy.profiler.usecasses.dto.AboutDto;

public interface AboutMapper {
    About toEntity(AboutDto aboutDto);

    AboutDto toDto(About about);
}
