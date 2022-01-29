package com.virtual.soft.axew.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.client.ClientSaveDto;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.utils.SecurityContextTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SaveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return access denied when role is not ADMIN")
    void returnAccessDeniedWhenRoleIsWrong () throws Exception {
        Set<RoleEnum> roles = Set.of(RoleEnum.USER);

        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Severino Ian Martins");
        params.put("cpf", "632.416.844-15");
        params.put("email", "bill.ian@gmail.com");
        params.put("password", "0000000000");
        params.put("phone", "+55 61 33680-0646");
        params.put("birthDate", LocalDate.of(2021, 1, 1));
        params.put("street", "Quadra SQS 110 Bloco J");
        params.put("number", "683");
        params.put("district", "Asa Sul");
        params.put("zipCode", "70373-100");
        params.put("roles", new String[]{"ROLE_USER"});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isForbidden());

    }

    @Test
    @DisplayName("Should return bad request when role is invalid")
    void returnBadRequestWhenRoleIsInvalid () throws Exception {
        Set<RoleEnum> roles = Set.of(RoleEnum.ADMIN);

        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Severino Ian Martins");
        params.put("cpf", "632.416.844-15");
        params.put("email", "bill.ian@gmail.com");
        params.put("password", "0000000000");
        params.put("phone", "61 33680-0646");
        params.put("birthDate", "2000-05-23");
        params.put("street", "Quadra SQS 110 Bloco J");
        params.put("number", "683");
        params.put("district", "Asa Sul");
        params.put("zipCode", "70373-100");
        params.put("roles", new String[]{"ROLE_WRONG"});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());
    }

    @Test
    @DisplayName("Should return bad request when payload is empty")
    void returnBadRequestWhenPayloadIsEmpty () throws Exception {
        Set<RoleEnum> roles = Set.of(RoleEnum.ADMIN);

        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "");
        params.put("cpf", "");
        params.put("email", "");
        params.put("password", "");
        params.put("phone", "");
        params.put("birthDate", "");
        params.put("street", "");
        params.put("number", "");
        params.put("district", "");
        params.put("zipCode", "");
        params.put("roles", new String[]{""});

        ClientSaveDto dto = objectMapper.convertValue(params, ClientSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());
    }

    public ResultActions callEndpoint (ClientSaveDto payload) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/client")
                .content(objectMapper.writeValueAsString(payload))
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON));
    }
}
