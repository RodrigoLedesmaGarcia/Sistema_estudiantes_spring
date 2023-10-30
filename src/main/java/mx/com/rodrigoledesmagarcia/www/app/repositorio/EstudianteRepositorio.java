package mx.com.rodrigoledesmagarcia.www.app.repositorio;

import mx.com.rodrigoledesmagarcia.www.app.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
}
