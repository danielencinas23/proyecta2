package com.proyectado.proyecto;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {

	private final ProyectoRepository proyectoRepository;

	public ProyectoService(ProyectoRepository proyectoRepository) {
		this.proyectoRepository = proyectoRepository;
	}

	public Optional<Proyecto> findById(Long id) {
		return proyectoRepository.findById(id);
	}
}
