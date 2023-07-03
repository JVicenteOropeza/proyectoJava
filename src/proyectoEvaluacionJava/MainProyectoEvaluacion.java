package proyectoEvaluacionJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MainProyectoEvaluacion {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Se inicializa las variables a utilizar en el Main
		Empleado listaEmpleados = new Empleado();
		List<String> verLista = new ArrayList<>();
		Scanner leer = new Scanner(System.in);
		Administracion agregaEmp = new Administracion();
		int opcion;
		String entrada;
		
		//INICIO DEL SISTEMA PARA EL USUARIO
		System.out.println(" ");
		System.out.println("----------------------------------------------");
		System.out.println("Quieres iniciar el SISTEMA? S/N ");
		entrada = leer.next();
		System.out.println("----------------------------------------------");
		System.out.println(" ");
		entrada = entrada.toUpperCase();
		if(entrada.equals("S")) {
			//Se hace la precarga de la lista que contiene ya 3 empleados
			verLista = listaEmpleados.CargaEmpleados();
			try {
				//Se da inicio al sistema de la empresa
				do {
					/*
					 * Se da inició al menú de adminitración de la empresa
					 */
					System.out.println("Bienvenido al SISTEMA de Administración de EMPLEADOS");
					System.out.println("<<--------------------MENÚ PRINCIPAL-------------------->>");
					System.out.println("Contratar --------------------------------------------- 1");
					System.out.println("Despedir ---------------------------------------------- 2");
					System.out.println("Aumentar Salario -------------------------------------- 3");
					System.out.println("Ver lista de Empleados(Listar) ------------------------ 4");
					System.out.println("SALIR DEL SISTEMA ------------------------------------- 5");
					System.out.println("<<----------------FIN MENÚ PRINCIPAL-------------------->>");
					System.out.println();
					System.out.print("INGRESA TU OPCIÓN: ");
					
					opcion = leer.nextInt();
					switch(opcion){
						case 1:{
							System.out.println("Para CONTRATAR por favor ingrese el Nombre, Edad, y Salario");
							/*Se llama al método DatosContratar de la clase Administracion
							 * Recíbe la lista Original de pregacaga y la procesa para despues manejar
							 * una lista temporal para agregar y borrar llamada verLista
							 */
							agregaEmp.DatosContratar(listaEmpleados, verLista);
							break;
							}
						case 2:{
							/*
							 * Se llama al método Despedir de la clase Administracion
							 * recíbe la lista verLista para trabajar el borrado
							 */
							verLista = agregaEmp.Despedir(verLista);
							break;
						}
						case 3:{
							/*
							 * Se llama al método DatosAumento de la clase Administracion
							 * recíbe la lista verLista para agregar el aumento
							 */
							verLista = agregaEmp.DatosAumento(verLista);
							break;
						}
						case 4:{
							/*
							 * Se llama al método VerLista de la clase Empleado
							 * recíbe la lista verLista para leer y mostrar en pantalla
							 */
							System.out.println("<<<<<<<<LISTA DE EMPLEADOS>>>>>>>>");
							listaEmpleados.VerLista(verLista);
							break;
						}
						case 5:{
							System.out.println("<<---------------SALISTE DEL SISTEMA--------------->>");
							break;
						}
						default:{
							System.out.println("*****ELIGE UNA OPCIÓN VALIDA*****");
							break;
						}
					}
				}while(opcion >= 1 && opcion <5);
			
			}catch(InputMismatchException ex) {
				System.out.println("OPCIÓN NO VALIDA -- EX: "+ex);
			}
			
			leer.close();
		}else {
			System.out.println("********** NO SE INICIO EL SISTEMA **********");
		}
	}

}
