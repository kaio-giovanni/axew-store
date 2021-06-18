package com.virtual.soft.axew.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.client.ClientDto;
import com.virtual.soft.axew.entity.Client;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.repository.ClientRepository;
import com.virtual.soft.axew.utils.FakeUtils;
import com.virtual.soft.axew.utils.SecurityContextTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FindByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return client data when role is ADMIN")
    void returnCorrectResponseWhenRoleIsAdmin () throws Exception {
        final Long id = 21L;
        Set<RoleEnum> roles = Set.of(RoleEnum.ADMIN);

        Optional<Client> client = Optional.of(FakeUtils.makeClient(id));
        Mockito.when(repository.findById(id))
                .thenReturn(client);

        SecurityContextTestUtils.fakeAuthentication(id, roles);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client/{id}", String.valueOf(id))
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andReturn();

        ClientDto dto = objectMapper.readValue(result.getResponse()
                .getContentAsString(), ClientDto.class);

        Assertions.assertEquals(dto.getId(), id);
    }

    @Test
    @DisplayName("Should return correct data when the role is USER and the id corresponds to the user who made the request")
    void returnCorrectResponseWhenRoleIsUser () throws Exception {
        final Long id = 20L;
        Set<RoleEnum> roles = Set.of(RoleEnum.USER);

        SecurityContextTestUtils.fakeAuthentication(id, roles);

        Optional<Client> client = Optional.of(FakeUtils.makeClient(id));
        Mockito.when(repository.findById(id))
                .thenReturn(client);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/client/{id}", String.valueOf(id))
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andReturn();

        ClientDto dto = objectMapper.readValue(result.getResponse()
                .getContentAsString(), ClientDto.class);

        Assertions.assertEquals(dto.getId(), id);
    }

    @Test
    @DisplayName("Should return access denied when role is USER and the id not corresponds to the user who made the request")
    void returnAccessDeniedWhenRoleIsUser () throws Exception {
        final long id = 11L;
        Set<RoleEnum> roles = Set.of(RoleEnum.USER);

        SecurityContextTestUtils.fakeAuthentication(15L, roles);

        Optional<Client> client = Optional.of(FakeUtils.makeClient(id));
        Mockito.when(repository.findById(id))
                .thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/client/{id}", String.valueOf(id))
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isForbidden());
    }
}
