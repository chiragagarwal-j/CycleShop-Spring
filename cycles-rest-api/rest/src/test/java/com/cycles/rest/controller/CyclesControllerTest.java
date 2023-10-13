package com.cycles.rest.controller;

import com.cycles.rest.dto.AddToCartRequest;
import com.cycles.rest.dto.CartUpdateRequest;
import com.cycles.rest.repository.CartRepository;
import com.cycles.rest.repository.CyclesRepository;
import com.cycles.rest.repository.OrderRepository;
import com.cycles.rest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CyclesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CyclesRepository cyclesRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser
    void testGetAllCycles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cycles/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].brand").value("BSA"))
                .andExpect(jsonPath("$[0].quantity").value(305))
                .andExpect(jsonPath("$[0].price").value(100))
                .andExpect(jsonPath("$[0].numBorrowed").value(69))
                .andExpect(jsonPath("$[0].numAvailable").value(236))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].color").value("Red"))
                .andExpect(jsonPath("$[1].brand").value("Atlas"))
                .andExpect(jsonPath("$[1].quantity").value(200))
                .andExpect(jsonPath("$[1].price").value(200))
                .andExpect(jsonPath("$[1].numBorrowed").value(93))
                .andExpect(jsonPath("$[1].numAvailable").value(107));
    }

    @Test
    @WithMockUser
    void testAddToCart() throws Exception {
        AddToCartRequest request = new AddToCartRequest();
        request.setId(1L);
        request.setQuantity(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Item added to the cart."));
    }

    @Test
    @WithMockUser
    void testGetCartItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cycles/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].cycleId").value(1))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].brand").value("BSA"))
                .andExpect(jsonPath("$[0].quantity").value(1))
                .andExpect(jsonPath("$[0].price").value(100));
    }

    @Test
    @WithMockUser
    void testUpdateCartItemQuantity() throws Exception {
        CartUpdateRequest request = new CartUpdateRequest();
        request.setCycleId(1L);
        request.setNewQuantity(3);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/updateCartItemQuantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Cart item quantity updated"));
    }

    @Test
    @WithMockUser
    void testRemoveFromCart() throws Exception {
        Long cycleId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cycles/removeFromCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cycleId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Item removed from the cart"));
    }

    @Test
    @WithMockUser
    void testConfirmedOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/confirmedOrder")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.responseMessage").value("Order confirmed successfully"));
    }

}
