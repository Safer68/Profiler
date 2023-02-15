package by.itacademy.profiler.usecasses;

import by.itacademy.profiler.usecasses.dto.AboutDto;

public interface AboutService {
    AboutDto add(String cvUUID, AboutDto aboutDto);
}
