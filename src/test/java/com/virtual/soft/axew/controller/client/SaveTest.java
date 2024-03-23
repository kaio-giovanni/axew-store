package com.virtual.soft.axew.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.entity.Address;
import com.virtual.soft.axew.repository.AddressRepository;
import com.virtual.soft.axew.utils.FakeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Disabled
class SaveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressRepository repository;

    @BeforeEach
    @Transactional
    void setup() {
        Address address = FakeUtils.makeAddress();
        repository.save(address);
    }

    @Test
    @DisplayName("Should return HTTP status created when use ROLE USER")
    void returnAccessDeniedWhenRoleIsWrong() throws Exception {
        final Long addressId = repository.findAll().get(0).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Severino Ian Martins");
        params.put("cpf", "390.080.050-26");
        params.put("email", "bill.ian@gmail.com");
        params.put("password", "0000000000");
        params.put("phone", "+55 61 33680-0646");
        params.put("birthDate", LocalDate.of(2021, 1, 1));
        params.put("addressId", addressId);
        params.put("roles", new String[]{"ROLE_USER"});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isCreated());

    }

    @Test
    @DisplayName("Should return internal server error when role is invalid")
    void returnBadRequestWhenRoleIsInvalid() throws Exception {
        final Long addressId = repository.findAll().get(0).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Severino Ian Martins");
        params.put("cpf", "390.080.050-26");
        params.put("email", "bill.ian@gmail.com");
        params.put("password", "0000000000");
        params.put("phone", "+55 61 33680-0646");
        params.put("birthDate", LocalDate.of(2021, 1, 1));
        params.put("addressId", addressId);
        params.put("roles", new String[]{"WRONG_ROLE"});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().is5xxServerError());
    }

    @Test
    @DisplayName("Should return bad request when payload is empty")
    void returnBadRequestWhenPayloadIsEmpty() throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("name", "");
        params.put("cpf", "");
        params.put("email", "");
        params.put("password", "");
        params.put("phone", "");
        params.put("birthDate", "");
        params.put("addressId", 0);
        params.put("roles", new String[]{""});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());
    }

    public ResultActions callEndpoint(ClientSaveDto payload) throws Exception {
        var tokenAttrName = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
        var httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        var csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        return mockMvc.perform(MockMvcRequestBuilders
                .post("/client/new")
                .sessionAttr(tokenAttrName, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .content(objectMapper.writeValueAsString(payload))
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON));
    }
}
