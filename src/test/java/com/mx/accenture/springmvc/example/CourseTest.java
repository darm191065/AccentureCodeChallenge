package com.mx.accenture.springmvc.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.mx.accenture.springmvc.example.controller.CourseController;
import com.mx.accenture.springmvc.example.dto.CourseDTO;
import com.mx.accenture.springmvc.example.service.ICourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
public class CourseTest {

    public static final int ID_COURSE = 10;
    public static final String COURSE_NAME = "courseName";
    public static final String TYPE_COURSE = "typeCourse";
    public static final String TEACHER_NAME = "teacherName";
    public static final int NUMBER_STUDENTS = 30;
    public static final int NUMBER_LESSONS = 20;
    //MockMvc component is provided by Spring to make calls to the Spring MVC API and assert different properties like status code and received response.
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ICourseService iCursoService; // This will mock a Spring Bean and Inject it where is needed


    @WithMockUser("admin")
    @Test
    void getAllCoursesTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/course/list"))
                .andExpect(status().isOk()).andReturn(); //check is response status is 200
        assertEquals(200, result.getResponse().getStatus());
    }
    @WithMockUser("admin")
    @Test
    void getCourseById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/course/1"))
                .andExpect(status().isOk()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    @WithMockUser("admin")
    @Test
    void addCourse() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        post("/course/add")
                        .content(asJsonString(courseDTOTestInstance()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                //.andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    @WithMockUser("admin")
    void updateCourse() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put("/course/update/4")
                        .content(asJsonString(courseDTOTestInstance()))
        ).andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser("admin")
    @Test
    void deleteCourseByIdTest() throws Exception{
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/course/delete/1"))
                .andExpect(status().isOk()) //check is response status is 200
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    private static CourseDTO courseDTOTestInstance() {
        return new CourseDTO(ID_COURSE, COURSE_NAME, TYPE_COURSE, TEACHER_NAME, NUMBER_STUDENTS, NUMBER_LESSONS);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}