package by.itacademy.profiler.usecasses;

import by.itacademy.profiler.persistence.model.About;
import by.itacademy.profiler.persistence.repository.AboutRepository;
import by.itacademy.profiler.usecasses.dto.AboutDto;
import by.itacademy.profiler.usecasses.impl.AboutServiceImpl;
import by.itacademy.profiler.usecasses.mapper.AboutMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AboutServiceTest {
    @Mock
    private AboutMapper aboutMapper;
    @Mock
    private AboutRepository aboutRepository;
    @InjectMocks
    private AboutServiceImpl aboutService;

    @Test
    void testPositiveFlow() {
        String cvUUID = "20c3cb38-abb4-11ed-afa1-0242ac120002";
        AboutDto aboutDto = new AboutDto("test description", "https://test.com/12334");

        About about = new About();

        when(aboutMapper.toEntity(aboutDto)).thenReturn(about);
        when(aboutRepository.save(any(About.class))).thenReturn(about);
        when(aboutMapper.toDto(about)).thenReturn(aboutDto);

        AboutDto addedAboutDto = aboutService.add(cvUUID, aboutDto);

        assertEquals(aboutDto.getDescription(), addedAboutDto.getDescription());
        assertEquals(aboutDto.getSelfPresentation(), addedAboutDto.getSelfPresentation());
    }


}