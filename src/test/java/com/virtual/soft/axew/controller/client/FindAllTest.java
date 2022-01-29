package com.virtual.soft.axew.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.client.ClientPageDto;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.repository.ClientRepository;
import com.virtual.soft.axew.utils.FakeUtils;
import com.virtual.soft.axew.utils.SecurityContextTestUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FindAllTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    void setup () {
        List<Client> clients = FakeUtils.makeClients();
        repository.saveAll(clients);
    }

    @AfterEach
    @Transactional
    void cleanUp () {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Should return a paginated users when role is ADMIN")
    void returnCorrectResponseWhenRoleIsCorrect () throws Exception {
        final int page = 0;
        final int size = 5;
        Set<RoleEnum> roles = Set.of(RoleEnum.ADMIN);

        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client")
                .queryParam("page", String.valueOf(page))
                .queryParam("size", String.valueOf(size))
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andReturn();

        ClientPageDto dto = objectMapper.readValue(result
                .getResponse()
                .getContentAsString(), ClientPageDto.class);

        Assertions.assertEquals(2, dto.getTotalPages());
        Assertions.assertEquals(10, dto.getNumberOfElements());
        Assertions.assertEquals(size, dto.getClients().size());
    }

    @Test
    @DisplayName("Should return access denied when role is not ADMIN")
    void returnAccessDeniedWhenRoleIsNotAdmin () throws Exception {
        Set<RoleEnum> roles = Set.of(RoleEnum.USER);

        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/client")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @DisplayName("Should return access denied when client roles is null")
    void returnAccessDeniedWhenRolesIsNull () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/client")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
