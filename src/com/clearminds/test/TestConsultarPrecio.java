package com.clearminds.test;


import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestConsultarPrecio {

	public static void main(String[] args) {

		MaquinaDulces maquina=new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C");
		maquina.agregarCelda("D");
		
		Producto producto=new Producto("KE34","Papitas",0.85);
		maquina.cargarProducto(producto, "B", 4);
		
		maquina.cargarProducto(new Producto("BDCR", "Gatorade", 2.54),"D",5);
		
		double precio=maquina.consultarPrecio("D");
		
		System.out.println("Precio:"+precio);
	}

}
