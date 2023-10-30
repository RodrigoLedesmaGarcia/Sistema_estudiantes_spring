package mx.com.rodrigoledesmagarcia.www.app.servicio;

import mx.com.rodrigoledesmagarcia.www.app.modelo.Estudiante;
import mx.com.rodrigoledesmagarcia.www.app.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio implements  IEstudianteServicio{

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return  estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
          estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
          estudianteRepositorio.delete(estudiante);
    }
}
