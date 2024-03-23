package com.virtual.soft.axew.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.product.ProductPageDto;
import com.virtual.soft.axew.entity.Product;
import com.virtual.soft.axew.repository.ProductRepository;
import com.virtual.soft.axew.utils.FakeUtils;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FindAllTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    void setup() {
        List<Product> products = FakeUtils.makeProducts();
        repository.saveAll(products);
    }

    @AfterEach
    @Transactional
    void cleanUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Should return a paginated list of products")
    void returnCorrectResponseWhenRoleIsCorrect() throws Exception {
        final int page = 0;
        final int size = 5;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/")
                        .queryParam("page", String.valueOf(page))
                        .queryParam("size", String.valueOf(size))
                        .header(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andReturn();

        ProductPageDto dto = objectMapper.readValue(result
                .getResponse()
                .getContentAsString(), ProductPageDto.class);

        Assertions.assertEquals(1, dto.getTotalPages());
        Assertions.assertEquals(size, dto.getNumberOfElements());
        Assertions.assertEquals(size, dto.getProducts().size());
    }

    @Test
    @DisplayName("Should return bad request when params are not integers")
    void returnBadRequestWhenParamsArentIntegers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/")
                        .queryParam("page", "something else")
                        .queryParam("size", "something else")
                        .header(HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());

    }
}
