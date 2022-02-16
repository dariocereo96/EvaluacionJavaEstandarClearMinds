package com.clearminds.test;

import java.util.HashMap;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestVenderConEntregaCambio {

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
		
		Producto producto3=new Producto("P3","Cola",0.26);
		maquina.cargarProducto(producto3, "C", 4);
		
		Producto producto4=new Producto("P4","Galletas",1.70);
		maquina.cargarProducto(producto4, "D", 6);
		
		System.out.println("Productos en la maquina:");
		maquina.mostrarProductos();
		
		maquina.recibirDinero(0.50);
		maquina.recibirDinero(1);
		
		HashMap<Double,Integer> cambio = maquina.venderConEntregaCambio("C");
		
		System.out.println("Cambio a entregar:");
		System.out.println(cambio);
	}

}
