package com.wilfred.security.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilfred.security.springsecurity.payload.UserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class RegistrationControllerTest {

    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/registration")
                        .content(this.objectMapper.writeValueAsString(new UserRequest("wilfred", "kip", "wilfredkim5@gmail.com", "testPassWd", "testPassWd")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").exists())
                .andDo(document("registration service",
                        requestFields(fieldWithPath("firstname").description("The firstname of the input"),
                                fieldWithPath("lastname").description("The lastname of the input"),
                                fieldWithPath("email").description("The email of the input"),
                                fieldWithPath("password").description("The password of the input"),
                                fieldWithPath("matchingPassword").description("The matchingPassword of the input")
                        )));
    }

    @Test
    void confirmRegistration() {
    }


}