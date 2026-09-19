package com.proyectado.proyecto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
class ProyectoControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ProyectoRepository proyectoRepository;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		proyectoRepository.deleteAll();
	}

	@Test
	void findByIdDevuelveElProyectoCuandoExiste() throws Exception {
		Proyecto guardado = proyectoRepository.save(Proyecto.builder()
				.nombre("Proyecta")
				.descripcion("Entrega find-by-id")
				.build());

		mockMvc.perform(get("/api/proyectos/{id}", guardado.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(guardado.getId()))
				.andExpect(jsonPath("$.nombre").value("Proyecta"));
	}

	@Test
	void findByIdDevuelve404CuandoNoExiste() throws Exception {
		mockMvc.perform(get("/api/proyectos/{id}", 999L))
				.andExpect(status().isNotFound());
	}
}
