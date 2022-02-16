package com.clearminds.test;

import com.clearminds.maquina.MaquinaDulces;

public class TestRecibirDinero {
	public static void main(String[] args) {
		
		MaquinaDulces maquina = new MaquinaDulces();
		maquina.recibirDinero(0.25);
		maquina.recibirDinero(0.40);
		maquina.recibirDinero(0.50);
		
		double dinero = maquina.consultarDineroIngresado();
		
		System.out.println("Dinero ingresado en la maquina: "+dinero);
		
		maquina.resetearDinero();
		
	}
}
