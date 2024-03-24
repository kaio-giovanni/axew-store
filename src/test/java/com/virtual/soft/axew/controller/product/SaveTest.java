package com.virtual.soft.axew.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.product.ProductSaveDto;
import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.repository.CategoryRepository;
import com.virtual.soft.axew.repository.ProductRepository;
import com.virtual.soft.axew.utils.FakeUtils;
import com.virtual.soft.axew.utils.SecurityContextTestUtils;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SaveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    void setup() {
        categoryRepository.saveAll(FakeUtils.makeCategories());
    }

    @Test
    @DisplayName("Should return HTTP status created when use ROLE ADMIN")
    void returnCorrectResponseWhenProductExists() throws Exception {
        final Set<RoleEnum> roles = Set.of(RoleEnum.ADMIN);
        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Calca jeans");
        params.put("description", "Calca jeans blablabla");
        params.put("price", 200);
        params.put("imgUrl", "http://fake/path/image");
        params.put("categoryIds", new Integer[]{1, 2});

        ProductSaveDto dto = objectMapper.convertValue(params, ProductSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isCreated());
    }

    @Test
    @DisplayName("Should return forbidden status when use ROLE USER")
    void returnIncorrectResponseWhenUseIncorrectRole() throws Exception {
        final Set<RoleEnum> roles = Set.of(RoleEnum.USER);
        SecurityContextTestUtils.fakeAuthentication(100L, roles);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Calca jeans");
        params.put("description", "Calca jeans blablabla");
        params.put("price", 200);
        params.put("imgUrl", "http://fake/path/image");
        params.put("categoryIds", new Integer[]{1, 2});

        ProductSaveDto dto = objectMapper.convertValue(params, ProductSaveDto.class);

        callEndpoint(dto)
                .andExpect(MockMvcResultMatchers
                        .status().isForbidden());
    }

    public ResultActions callEndpoint(ProductSaveDto payload) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/product/new")
                .content(objectMapper.writeValueAsString(payload))
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON));
    }

}
