package com.example.demo;

import com.example.gradesapi.DemoApplication;
import com.example.gradesapi.model.Course;
import com.example.gradesapi.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(classes = DemoApplication.class)



@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllCourses_ShouldReturnCourses() throws Exception {
        mockMvc.perform(get("/v1/courses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getCourseById_WhenCourseExists_ShouldReturnCourse() throws Exception {
        Long testCourseId = 1L;
        mockMvc.perform(get("/v1/courses/{id}", testCourseId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testCourseId));
    }

    @Test
    public void getCourseById_WhenCourseDoesNotExist_ShouldReturnNotFound() throws Exception {
        Long testCourseId = 99L;
        mockMvc.perform(get("/v1/courses/{id}", testCourseId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCourse_ShouldReturnCreatedCourse() throws Exception {
        Course course = new Course();
        course.setName("New Course");
        mockMvc.perform(post("/v1/courses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Course"));
    }

    @Test
    public void updateCourse_WhenCourseExists_ShouldReturnUpdatedCourse() throws Exception {
        Long testCourseId = 1L;
        Course course = new Course();
        course.setName("Updated Course");

        mockMvc.perform(put("/v1/courses/{id}", testCourseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Course"));
    }

    @Test
    public void deleteCourse_WhenCourseExists_ShouldReturnOk() throws Exception {
        Long testCourseId = 1L;
        mockMvc.perform(delete("/v1/courses/{id}", testCourseId))
                .andExpect(status().isOk());
    }
}