package co.com.zonatelematica.estudiantes;

import co.com.zonatelematica.estudiantes.modelo.Estudiante;
import co.com.zonatelematica.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger =
			LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Inciando la aplicacion...");
		//Levanta la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada!");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Se ejecuta el metodo run de Spring..." + nl);
		boolean salir = false;
		var input = new Scanner(System.in);
		while (!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(input);
			logger.info(nl);
		}// fin while
	}

	private void mostrarMenu(){
		// logger.info(nl);
		logger.info("""
				*** Sistema de Estudiantes ***
				1. Listar Estudiantes
				2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Selecciona una opcion:
				""");

	}
	private  boolean ejecutarOpciones(Scanner input){
		int opcion = Integer.parseInt(input.nextLine());
		boolean salir = false;
		switch(opcion){
			case 1: // Listar estudiantes
				logger.info(nl + "Listado de Estudiantes: " + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + nl)));
				break;

			case 2: // Buscar estudiante por Id
				logger.info("Ingrese por favor el Id del estudiante: ");
				int idEstudiante = Integer.parseInt(input.nextLine());
				Estudiante estudiante =
						estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null)
					logger.info("Se encontro el estudiante exitosamente: " + estudiante + nl);
				else
					logger.info("No se pudo encontrar el estudiante con id: " + estudiante + nl);
				break;
			case 3: // Agregar estudiante
				logger.info("Agregar Estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = input.nextLine();
				logger.info("Apellido: ");
				var apellido = input.nextLine();
				logger.info("Telefono: ");
				var telefono = input.nextLine();
				logger.info("Email: ");
				var email = input.nextLine();
				// Crear el objeto estudiante sin el Id
				estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Se ha agregado estudiante exitosamente: " + estudiante + nl);
				break;

			case 4: //Modificar estudiante
				logger.info("Modificar estudiante: " + nl);
				logger.info("Id Estudiante: ");
				idEstudiante = Integer.parseInt(input.nextLine());
				//Se busca estudiante a modificar
				 estudiante =
						estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null) {
					nombre = input.nextLine();
					logger.info("Apellido: ");
					apellido = input.nextLine();
					logger.info("Telefono: ");
					telefono = input.nextLine();
					logger.info("Email: ");
					email = input.nextLine();
					estudiante = new Estudiante();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Se modifico estudiante exitosamente: " + estudiante + nl);
				}
				else
					logger.info("No se pudo encontrar el estudiante con id: " + estudiante + nl);
				break;

			case 5: //Eliminar estudiante
				logger.info("Eliminar Estudiante: " + nl);
				logger.info("Id Estudiante: ");
				idEstudiante = Integer.parseInt(input.nextLine());
				// Se busca el id a eliminar
				estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Se elimino estudiante exitosamente: " + estudiante + nl);
				}
				else
				logger.info("No se pudo encontrar el estudiante con id: " + estudiante + nl);
				break;

			case 6: //Salir menu
				logger.info("Adios ...." + nl + nl);
				salir = true;
				break;

			default:
				logger.info("Opcion incorrecta: " + opcion + nl);

		}// fin switch
		return salir;
	}

}