package com.clearminds.test;

import java.util.ArrayList;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestBuscarMenores {

	public static void main(String[] args) {
		MaquinaDulces maquina=new MaquinaDulces();
		maquina.agregarCelda("A");
		maquina.agregarCelda("B");
		maquina.agregarCelda("C");
		maquina.agregarCelda("D");
		maquina.agregarCelda("E");
		maquina.agregarCelda("F");
		
		Producto producto1=new Producto("P1","Papitas",0.85);
		Producto producto2=new Producto("P2","Doritos",0.60);
		Producto producto3=new Producto("P3","Cola",0.95);
		Producto producto4=new Producto("P4","Galletas",1.70);
		Producto producto5=new Producto("P5","Pan",0.35);
		Producto producto6=new Producto("P6","Rosca",1.00);
		
		maquina.cargarProducto(producto1, "A", 7);
		maquina.cargarProducto(producto2, "B", 8);
		maquina.cargarProducto(producto3, "C", 10);
		maquina.cargarProducto(producto4, "D", 4);
		maquina.cargarProducto(producto5, "E", 23);
		maquina.cargarProducto(producto6, "F", 13);
		

		ArrayList<Producto> productos = maquina.buscarMenores(0.50);
		
		if(productos.size()>0){
			for (int i = 0; i < productos.size(); i++) {
				Producto prod = productos.get(i);
				System.out.println("Codigo: "+prod.getCodigo()+" Nombre: "+prod.getNombre()+" Precio: "+prod.getPrecio());
			}
		}
		else{
			System.out.println("Lista vacia");
		}
		
		
	}

}
