package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestVenderConCambio {

	public static void main(String[] args) {
		MaquinaDulces maquina=new MaquinaDulces();
		
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C");
		maquina.agregarCelda("D");
		
		Producto producto1=new Producto("P1","Papitas",0.85);
		maquina.cargarProducto(producto1, "A", 4);
		
		Producto producto2=new Producto("P2","Doritos",0.60);
		maquina.cargarProducto(producto2, "B", 6);
		
		Producto producto3=new Producto("P3","Cola",0.95);
		maquina.cargarProducto(producto3, "C", 4);
		
		Producto producto4=new Producto("P4","Galletas",1.70);
		maquina.cargarProducto(producto4, "D", 6);
		
		maquina.mostrarProductos();
		
		double cambio = maquina.venderConCambio("C",5);
	
		System.out.println("Maquina despues de la venta");
		
		maquina.mostrarProductos();
		
		System.out.println("Su cambio es: "+cambio);
		
		

	}

}
