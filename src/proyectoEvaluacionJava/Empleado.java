package proyectoEvaluacionJava;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
	private List<String> listaEmpleados;
	
	
	/*
	 * Método que hace la precarga de la lista de empleados
	 */
	public List<String> CargaEmpleados() {
		this.listaEmpleados = new ArrayList<>();
		
		listaEmpleados.add("ep00162|Juan Vicente|42|51000.00");
		listaEmpleados.add("ep00170|Alberto|32|20500.00");
		listaEmpleados.add("ep00134|Fernanda|28|15000.00");
		
		return listaEmpleados;
	}
	/*
	 * Método que agrega el registro a la lista de Empelados
	 * Recíbe el regsitro nuevo, y la lista de empleados
	 */
	public List<String> AgregarEmpleado(String registroNuevo, List<String> listaEmpleados){
		this.listaEmpleados = listaEmpleados;
		//Se añade el registro nuevo con el método "add"
		listaEmpleados.add(registroNuevo);
		
		return listaEmpleados;
	}
	
	/*
	 * Método que busca el nombre dentro de los registros de la lista de empleados
	 * Recíbe la lista de empleados, y el nombre a buscar
	 */
	public String Buscar(List<String> listaEmpleados, String nombreBuscar) {
		int numRegistros = listaEmpleados.size();
		String encontrado = " ";
		
		for (int i = 0; i < numRegistros; i++) {
			String[] separadoRegis = new String[4];
			separadoRegis = listaEmpleados.get(i).split("\\|");
			String nombre = separadoRegis[1];
			
			//Se pregunta que el nombre que es parte del regsitro sea el que se recíbio para buscar
			if(nombre.equals(nombreBuscar)) {
				encontrado = listaEmpleados.get(i);
				//System.out.println("Registro encontrado ---> "+listaEmpleados.get(i));
				break;
			}
		}
		return encontrado;
	}
	 /*
	  * Método que hace el despliege de la lista mostrando a los empleados en ella
	  */
	public void VerLista(List<String> listaEmpleados) {
		int numRegistros = listaEmpleados.size();
		System.out.println("*************VER LISTA*************");
		for (int i=0; i<numRegistros; i++ ) {
			System.out.println("ELEMENTO ----> "+i+"-----> "+listaEmpleados.get(i));
		}
		System.out.println("*************VER LISTA*************");
		System.out.println(" ");
	}
}
