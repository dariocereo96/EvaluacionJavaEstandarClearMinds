package com.clearminds.test;

import java.util.HashMap;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestVenderConCambioControlado {

	public static void main(String[] args) {
		
		MaquinaDulces maquina = new MaquinaDulces();

		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C");
		maquina.agregarCelda("D");
		
		Producto producto1=new Producto("P1","Papitas",0.85);
		maquina.cargarProducto(producto1, "A", 4);
		
		Producto producto2=new Producto("P2","Doritos",0.60);
		maquina.cargarProducto(producto2, "B", 6);
		
		Producto producto3=new Producto("P3","Cola",0.36);
		maquina.cargarProducto(producto3, "C", 4);
		
		Producto producto4=new Producto("P4","Galletas",1.70);
		maquina.cargarProducto(producto4, "D", 6);
				
		maquina.cargaInicial(0.25, 10);
		maquina.cargaInicial(0.10, 3);
		maquina.cargaInicial(0.01, 10);
		maquina.cargaInicial(0.50, 4);
		
		System.out.println("Dinero actual en la maquina");
		maquina.mostrarDineroActual();
		System.out.println("");
		
		System.out.println("Dinero en la maquina luego de ingresar dinero");
		maquina.recibirDinero(1);
		maquina.mostrarDineroActual();
		System.out.println("");
		
		System.out.println("Venta de la maquina");
		
		HashMap<Double,Integer> cambio = maquina.venderConCambioControlado("C");
		
		for (Double denominacion : cambio.keySet()) {
			System.out.println(cambio.get(denominacion)+" de "+denominacion);
		}
		 System.out.println("");
		 
		 System.out.println("Dinero de la maquina despues de vender");
		 maquina.mostrarDineroActual();
		 
	}

}
