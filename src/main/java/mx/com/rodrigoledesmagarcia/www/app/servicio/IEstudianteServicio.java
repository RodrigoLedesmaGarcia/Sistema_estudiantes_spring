package mx.com.rodrigoledesmagarcia.www.app.servicio;

import mx.com.rodrigoledesmagarcia.www.app.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {

    public List<Estudiante> listar();

    public Estudiante buscarEstudiantePorId(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public  void eliminarEstudiante(Estudiante estudiante);
}
