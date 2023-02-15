package by.itacademy.profiler.api.controllers;

import by.itacademy.profiler.usecasses.AboutService;
import by.itacademy.profiler.usecasses.dto.AboutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @MockBean
    private AboutService aboutService;

    @Test
    void shouldReturn200WhenSaveValidInputAboutSection() throws Exception {
        AboutDto about = new AboutDto("test description", "https://test.com/12334");

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
        AboutDto about = new AboutDto("test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description test description", "https://test.com/12334");

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(about))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenSaveInvalidSelfPresentationAboutSection() throws Exception {
        AboutDto about = new AboutDto("test description", "");

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        "20c3cb38-abb4-11ed-afa1-0242ac120002"
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(about))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn200AndCallBusinessLogicWhenSaveValidAboutSection() throws Exception {
        String cvUUID = "20c3cb38-abb4-11ed-afa1-0242ac120002";
        AboutDto aboutDto = new AboutDto("test description", "https://test.com/12334");

        when(aboutService.add(eq(cvUUID), eq(aboutDto))).thenReturn(aboutDto);

        mockMvc.perform(
                post(
                        "/api/v1/cvs/{uuid}/about",
                        cvUUID
                ).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aboutDto))
        ).andExpect(status().isOk());

        verify(aboutService, times(1)).add(cvUUID, aboutDto);

    }

    @Test
    void shouldReturnAddedAboutSectionWhenSaveValidAboutSection() throws Exception {
        String cvUUID = "20c3cb38-abb4-11ed-afa1-0242ac120002";
        AboutDto aboutDto = new AboutDto("test description", "https://test.com/12334");

        when(aboutService.add(eq(cvUUID), eq(aboutDto))).thenReturn(aboutDto);

        MvcResult mvcResult = mockMvc.perform(
                        post(
                                "/api/v1/cvs/{uuid}/about",
                                cvUUID
                        ).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(aboutDto))
                ).andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(aboutDto));
    }


}