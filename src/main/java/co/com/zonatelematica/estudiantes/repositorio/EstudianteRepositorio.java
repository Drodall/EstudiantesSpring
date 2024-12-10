package co.com.zonatelematica.estudiantes.repositorio;

import co.com.zonatelematica.estudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante , Integer> {
}
