package mx.com.rodrigoledesmagarcia.www.app;

import mx.com.rodrigoledesmagarcia.www.app.modelo.Estudiante;
import mx.com.rodrigoledesmagarcia.www.app.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;




@SpringBootApplication
public class SistemaApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SistemaApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		SpringApplication.run(SistemaApplication.class, args);
		logger.info("Aplicacion finalizada...");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"Ejecutando metodo run de spring...");
		var salir = false;
		var input = new Scanner(System.in);
		while(!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(input);
			logger.info(nl);
		}// fin ciclo while
	}
	private void mostrarMenu() {
		logger.info(nl);
		logger.info("""
                    **** Sistema de Estudiantes ****
                    Precione 1 para listar estudiante
                    Precione 2 para buscar estudiante
                    Precione 3 para agregar estudiante
                    Precione 4 para modificar estudiante
                    Precione 5 para eliminar estudiante
                    Precione 6 para salir del sistema
					""");
	}

	private boolean ejecutarOpciones(Scanner input) {
		var opcion = Integer.parseInt(input.nextLine());
		var salir = false;

		switch (opcion) {
			case 1 -> { // listado de estudiantes
				logger.info(nl+ "Listado de estudiantes. "+nl);
				List<Estudiante> estudiantes = estudianteServicio.listar();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
			}
			case 2 ->{
				logger.info("Introduce el ID del estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(input.nextLine());

				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if(estudiante != null){
					logger.info("Estudiante encontado: " + estudiante + nl);
				} else {
					logger.info("El id "+idEstudiante+" no se encontro en la base de datos.");
				}
			}

			case 3 -> {
				logger.info("Agrege al nuevo/a estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = input.nextLine();
				logger.info("Apellido: ");
				var apellido = input.nextLine();
				logger.info("Telefono: ");
				var telefono = input.nextLine();
				logger.info("email: ");
				var email = input.nextLine();

				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: " +estudiante + nl);
			}
			case 4 -> {
				logger.info("Modificar estudiante: " + nl);
				logger.info("Id del estudiante a modificar: ");
				var idEstudiante = Integer.parseInt(input.nextLine());

				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if( estudiante != null){
					logger.info("Nombre: ");
					var nombre = input.nextLine();
					logger.info("Apellido: ");
					var apellido = input.nextLine();
					logger.info("Telefono: ");
					var telefono = input.nextLine();
					logger.info("email: ");
					var email = input.nextLine();

					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);

					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado con exito...");

				} else {
					logger.info("El id "+idEstudiante+" no existe en la base de datos"+nl);

				}

			}

			case 5 -> {
				logger.info("Eliminar estudiante: ");
				logger.info("Proporcione el Id del estudiante a eliminar: ");
				var idEstudiante = Integer.parseInt(input.nextLine());
				var estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if( estudiante != null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado con exito...");

				} else {
					logger.info("El id "+ idEstudiante+ " no existe en la base de datos");
				}

			}
			case 6 -> {
				logger.info("Adios hasta la proxima " + nl + nl + nl);
				salir = true;
			}
			default -> {
				logger.info("Opcion no reconosida...");
			}

		}// fin del switch

		return salir;
	}
}
