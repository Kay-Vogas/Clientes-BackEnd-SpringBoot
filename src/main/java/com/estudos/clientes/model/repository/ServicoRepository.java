package com.estudos.clientes.model.repository;

import com.estudos.clientes.model.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
