package proyectoEvaluacionJava;


import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Administracion {
	private String id, nombre, edad, salario;
	private String agregarEmpleado;
	private double porcentaje;
	Scanner leer = new Scanner(System.in);

	/*
	 * Se agrega la perosona a contratar por medio del método Contratar
	 * Recíbe el nombre, edad y salario como parametros
	 */
	public String Contratar(String nombre, String edad, String salario) {
		System.out.println("*****************CONTRATACIÓN*****************");
		//Se hace paso de parametros a los de la clase Administracion
		this.nombre = nombre;
		this.edad = edad;
		this.salario = salario;
		
		//Se llama al método de esta misma clase para generar un ID aleatorio para el nuevo Empleado
		id = GenerarId();
		
		//Se concantena para formar el registro nuevo para ingresarlo a la lista
		this.agregarEmpleado = id+"|"+nombre+"|"+edad+"|"+salario;
		System.out.println(" ");
		System.out.println("----------------------------------------------");
		System.out.println("FELICIDADES !!! YA TIENES UN NUEVO RECURSO");
		System.out.println("*****************CONTRATACIÓN*****************");
		System.out.println(" ");
		return agregarEmpleado;
	}
	
	/*
	 * Se despide al empleado y se borra su registro de la lista de Empleado
	 * este método llama al Buscar de la clase Empleado para buscar el registro con el nombre
	 * También llama al método Eliminar de esta clase para borrar el registro
	 */
	public List<String> Despedir(List<String> listaEmpleados) {
		System.out.println("***********DESPEDIR***********");
		System.out.println("Para Despedir a un Empleado busca primero por su nombre");
		System.out.print("Ingresa el nombre del Empleado a Despedir: ");
		nombre = leer.next();
		
		Empleado buscar = new Empleado();
		String encontrado;
		
		//Se llama al método Buscar de la clase de Empleado
		encontrado = buscar.Buscar(listaEmpleados, nombre);
		if(encontrado != " ") {
			//Se crea la confirmación de realmente despedir y hacer el borrado del registro
			String respuesta;
			String[] encontradoArreglo = encontrado.split("\\|");
			System.out.println("--------------------------------------");
			System.out.print("El Empleado a Despedir es ---> ");
			
			//Se imprime el regsitro por separado del empleado a borrar
			for(int i = 0; i < encontradoArreglo.length; i++) {
				System.out.print(encontradoArreglo[i]+" ");			
			}
			System.out.println(" ");
			System.out.println("--------------------------------------");
			System.out.print("ESTAS SEGURO? --- S/N: ");
			respuesta = leer.next();
			System.out.println("--------------------------------------");
			respuesta = respuesta.toUpperCase();
		
			//Se pregunta al usuario si realmente quiere continuar con el despido
			if(respuesta.equals("S")) {
				//Se llama el método Eliminar y rescíbe el resgitro encontrado y la lista listaEmpleados
				listaEmpleados = Eliminar(encontrado, listaEmpleados);
				System.out.println(" ");
				System.out.println("--------------------------------------");
				System.out.println("LO ACABAS DE DESPEDIR !!! =( ");
				System.out.println("--------------------------------------");
				return listaEmpleados;
			}else {
				System.out.println(" ");
				System.out.println("--------------------------------------");
				System.out.println("NO DESPEDISTE A NADIE. QUE ALEGRIA!!!");
				System.out.println("--------------------------------------");
			}
		}else {
			System.out.println(" ");
			System.out.println("---------------------------------------------------");
			System.out.println("NO SE ENCONTRO UN REGISTRO CON LA INFORMACIÓN DADA");
			System.out.println("---------------------------------------------------");
		}
		System.out.println("***********DESPEDIR***********");
		System.out.println(" ");
		return listaEmpleados;
	}
	
	/*
	 * Método que hace el borrado del registro de la lista de empleados.
	 * Recíbe el registro y  la lista 
	 */
	public List<String> Eliminar(String eliminarEmpleado, List<String> listaEmpleados){
		List<String> listaTemp = listaEmpleados;
		int posicion;
		
		/*
		 * Se obtiene el indice para el borrado. 
		 * Si el valor de indexOf es igual a -1 implica que no se encontro el resgistro
		 * por eso se pide que sea distinto de -1
		 */
		posicion = listaTemp.indexOf(eliminarEmpleado);
		if(posicion != -1) {
			listaTemp.remove(posicion);
			System.out.println("--------------------------------------");
			System.out.println("Se elimino EMPLEADO");
			System.out.println("--------------------------------------");
			System.out.println(" ");
		}else {
			System.out.println("--------------------------------------");
			System.out.println("No existe el EMPLEADO");
			System.out.println("--------------------------------------");
			System.out.println(" ");
		}
		//Se sobreescribe la lista listaEmpleado con la listaTemp que ya tiene el borrado
		listaEmpleados = listaTemp;
		return listaEmpleados;
	}
	
	/*
	 * Este método sirve para empezar el aumento de salario del Empleado
	 * Recíbe el registro, el porcentaje del aumento, y la lista de empleados
	 */
	public List<String> AumentarSalario(String registroEmpleado, double porcentaje, List<String> listaEmpleados) {
		List<String> listaTemp = listaEmpleados;
		int posicion;
		posicion = listaTemp.indexOf(registroEmpleado);
		
		//validación del porcentaje para que si se recíbe un porcentaje como 16 a decimal 0.16
		if(porcentaje > 0) {
			porcentaje = porcentaje / 100;
		}
		//Se valida que se haya encontrado el registro
		if(posicion != -1) {
			double salarioTemp = 0;
			String registroTemp = listaTemp.get(posicion);
			listaTemp.remove(posicion);
			String[] arregloTemp = registroTemp.split("\\|");
			int tamanoTemp = arregloTemp.length;
			
			//Se obtiene el registro con el salario y se hace la conversión a double para el cálculo
			salarioTemp = Double.parseDouble(arregloTemp[3]);
			salarioTemp = salarioTemp + (salarioTemp * porcentaje);
			String registroNuevo="";
			
			//Se hace la concatenación para regresar el registro con el aumento incluido
			for(int i = 0; i < tamanoTemp - 1; i++) {
				 registroNuevo = registroNuevo+arregloTemp[i]+"|";
			}
			registroNuevo = registroNuevo+salarioTemp;
			listaTemp.add(registroNuevo);
		}
		listaEmpleados = listaTemp;
		return listaEmpleados;
	}
	/*
	 * Método que genera un ID de forma aleatoria y crear 
	 * identificador único para cada Empleado
	 */
	private String GenerarId() {
		//Todo id empieza con el prefijo "ep"
		String idGenerado = "ep";
		
		//Se generala la semilla para los números aleatorios
		Random aleatorio = new Random();
		int numId = aleatorio.nextInt(90000) + 10000;
		
		//Se concatena el prefijo con los números aleatorios
		idGenerado = idGenerado + numId;
		System.out.println("------------------------------------------------------");
		System.out.println("SE GENERO ID PARA EL NUEVO EMPLEADO ---> " +idGenerado);
		System.out.println("------------------------------------------------------");
		return idGenerado;
	}
	/*
	 * Métod que sirve para empezar el proceso de contratación
	 * Recíne el registro y la lista de empleados
	 */
	public void DatosContratar(Empleado listaEmpleados, List<String> verLista) {
		String nuevoEmp;
		double salarioTemp = 0;
		
		try {
			//Se validan tipos de datos
			System.out.print("Agregar nombre: ");
			nombre = leer.next();
			System.out.print("Agregar edad: ");
			edad = leer.next();
			System.out.print("Agregar salario: ");
			salario = leer.next();
				//salarioTemp = Double.valueOf(salario);
			salarioTemp = Double.valueOf(salario);
		}catch(NumberFormatException ex){
			System.out.println("HAY UN PARAMETRO NO VALIDO ---> INGRESA 'N' ó 'n'");
		}
		String respuesta;
		System.out.println("--------------------------------------");
		System.out.println("VAS A INGRESAR A: --->"+nombre+", "+edad+", "+"$"+salarioTemp);
		System.out.print("ESTAS SEGURO? --- S/N: ");
		respuesta = leer.next();
		System.out.println("--------------------------------------");
		respuesta = respuesta.toUpperCase();
		
		//Se valida la confirmación de la respuesta del usuario para la contratación
		if(respuesta.equals("S")) {
			nuevoEmp = Contratar(nombre, edad, salario);
			
			//Se llama al método AgregarEmpleado de la clase Empleado
			listaEmpleados.AgregarEmpleado(nuevoEmp, verLista);
		}else {
			System.out.println("---------------------------------------------------------");
			System.out.println("NO CONTRATASTE A NADIE NI AGREGASTE A LA LISTA EMPLEADOS");
			System.out.println("---------------------------------------------------------");
		}
	}
	/*
	 * Método para empezar el proceso de aumento del salario
	 * Recíbe la lista de empleados
	 */
	public List<String> DatosAumento(List<String> verLista) {
		String encontrado;
		Empleado buscar = new Empleado();
		System.out.println("Para Aumentar el Salario de un Empleado su nombre e ingresa el Porcentaje del Aumento");
		System.out.print("Ingresa el nombre a buscar AUMENTO: ");
		nombre = leer.next();
		leer.nextLine();
		System.out.print("Ingresa el PORCENTAJE a buscar AUMENTO: ");
		porcentaje = leer.nextDouble();
		
		//Se busca el nombre del empleado al que se le aumentará el sueldo dentro de la lista 
		encontrado = buscar.Buscar(verLista, nombre);
		if(encontrado != " ") {
			String respuesta;
			String[] encontradoArreglo = encontrado.split("\\|");
			System.out.print("El Empleado a quien le Aumentaras el Salario es ---> ");
			for(int i = 0; i < encontradoArreglo.length; i++) {
				System.out.print(encontradoArreglo[i]+" ");			
			}
			System.out.println(" ");
			System.out.println("--------------------------------------");
			System.out.print("ESTAS SEGURO? --- S/N: ");
			respuesta = leer.next();
			System.out.println("--------------------------------------");
			respuesta = respuesta.toUpperCase();
			
			//Se valida la respuesta del usuario para confirmar el aumento
			if(respuesta.equals("S")) {
				//Se llama al método AumentarSalario de esta clase. 
				//Recíbe el regsitro, el pocentaje de aumento, y la lista de empleados
				verLista = AumentarSalario(encontrado, porcentaje, verLista);
				System.out.println("--------------------------------------");
				System.out.println("EN HORA BUENA AUMENTO HECHO !!! =) ");
				System.out.println("--------------------------------------");
			}else {
				System.out.println("---------------------------------------------------");
				System.out.println("NO AUMENTAS EL SALARIO DE NADIE ! QUE CODO !!!! =p");
				System.out.println("---------------------------------------------------");
			}
		}else {
			System.out.println(" ");
			System.out.println("---------------------------------------------------");
			System.out.println("NO SE ENCONTRO UN REGISTRO CON LA INFORMACIÓN DADA");
			System.out.println("---------------------------------------------------");
		}
		System.out.println("");
		return verLista;
	}
}
