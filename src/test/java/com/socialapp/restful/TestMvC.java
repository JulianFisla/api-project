package com.socialapp.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialapp.restful.domain.User;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestMvC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateAndDeleteUser() throws Exception {
        // JSON representation of a new user
        String userJson = "{"
                + "\"userName\": \"testuser\","
                + "\"password\": \"password123\","
                + "\"email\": \"testuser@example.com\","
                + "\"firstName\": \"Test\","
                + "\"lastName\": \"User\""
                + "}";

        // Create the user
        MvcResult createResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andReturn();

        // Parse the response to get the created user
        String createResponse = createResult.getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(createResponse, User.class);

        // Verify the user was created
        mockMvc.perform(get("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testuser"));

        // Delete the user
        mockMvc.perform(delete("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isNoContent());

        // Verify the user was deleted
        mockMvc.perform(get("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isNotFound());
    }
    
    
    @Test
    void testCreateAndDeleteUserWithAllAttributes() throws Exception {
        // Prepare date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a User object with all attributes
        User user = new User();
        user.setUserId(12345); // This will likely be overwritten by the controller
        user.setUserName("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");
        user.setCreateDate(dateFormat.parse("2020-01-01")); // May be overwritten
        user.setUpdateDate(dateFormat.parse("2020-01-01")); // May be overwritten

        // Convert the User object to JSON
        String userJson = objectMapper.writeValueAsString(user);

        // Create the user
        MvcResult createResult = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userName").value("testuser"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.createDate").exists())
                .andExpect(jsonPath("$.updateDate").exists())
                .andReturn();

        // Parse the response to get the created user
        String createResponse = createResult.getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(createResponse, User.class);

        // Verify the user was created with all attributes
        mockMvc.perform(get("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(createdUser.getUserId()))
                .andExpect(jsonPath("$.userName").value("testuser"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.createDate").exists())
                .andExpect(jsonPath("$.updateDate").exists());

        // Delete the user
        mockMvc.perform(delete("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isNoContent());

        // Verify the user was deleted
        mockMvc.perform(get("/users/{userId}", createdUser.getUserId()))
                .andExpect(status().isNotFound());
    }
}
