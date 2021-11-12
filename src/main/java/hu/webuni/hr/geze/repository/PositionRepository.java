package hu.webuni.hr.geze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.geze.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{

	public List<Position> findByName(String name);
}
