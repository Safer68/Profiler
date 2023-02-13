package by.itacademy.profiler.api.controllers;

import by.itacademy.profiler.usecasses.dto.AboutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AboutRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class AboutRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn200WhenSaveAboutSection() throws Exception {
        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturn200WhenSaveValidInputAboutSection() throws Exception {
        AboutDto about = new AboutDto("test description", "http://test.com/12334");

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(about))
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenSaveInvalidDescriptionAboutSection() throws Exception {
        AboutDto about = new AboutDto("test descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest descriptiontest description", "https://test.com/12334");

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(about))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn500WhenSaveInvalidSelfPresentationAboutSection() throws Exception {
        AboutDto about = new AboutDto("test description", "");

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(about))
        ).andExpect(status().isBadRequest());
    }


}