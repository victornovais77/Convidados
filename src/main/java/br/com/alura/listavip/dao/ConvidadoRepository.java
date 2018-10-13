package br.com.alura.listavip.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.listavip.model.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, Integer> {

}
