package com.clearminds.maquina;

import java.util.ArrayList;
import java.util.HashMap;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {
	private ArrayList<Celda> celdas = new ArrayList<Celda>();
	private double saldo;
	private double dineroIngresado;
	private double[] montosValidos;
	private HashMap<Double, Integer> dineroActual;

	public MaquinaDulces() {
		this.montosValidos = new double[] { 0.01, 0.05, 0.10f, 0.25, 0.50f, 1.00f, 5.00f };
		this.dineroActual = new HashMap<Double, Integer>();
	}

	public void agregarCelda(String codigo) {
		this.celdas.add(new Celda(codigo));
	}

	public void mostrarConfiguracion() {
		System.out.println("***********************");
		for (int i = 0; i < this.celdas.size(); i++) {
			Celda celda = celdas.get(i);
			System.out.println("CELDA: " + celda.getCodigo());
		}
		System.out.println("SALDO: " + this.saldo);
	}

	public Celda buscarCelda(String codigo) {
		for (int i = 0; i < this.celdas.size(); i++) {
			Celda celda = celdas.get(i);
			if (celda.getCodigo().equals(codigo)) {
				return celda;
			}
		}
		return null;
	}

	public void cargarProducto(Producto producto, String codigo, int stock) {
		Celda celdaBusqueda = this.buscarCelda(codigo);
		if (celdaBusqueda != null) {
			celdaBusqueda.ingresarProducto(producto, stock);
		} else {
			System.out.println("No se encontro la celda");
		}
	}

	public void mostrarProductos() {
		for (int i = 0; i < celdas.size(); i++) {
			Celda celda = this.celdas.get(i);
			System.out.print("Celda: " + celda.getCodigo());
			System.out.print(" Stock: " + celda.getStock());
			if (celda.getProducto() != null) {
				System.out.print(" Producto: " + celda.getProducto().getNombre());
				System.out.print(" Precio: " + celda.getProducto().getPrecio());
			} else {
				System.out.print(" Sin producto asignado");
			}
			System.out.print("\n");
		}
		System.out.println(" Saldo: " + this.saldo);
	}

	public Producto buscarProductoEnCelda(String codigo) {
		Celda celdaBusqueda = this.buscarCelda(codigo);
		if (celdaBusqueda != null) {
			return celdaBusqueda.getProducto();
		} else {
			return null;
		}
	}

	public double consultarPrecio(String codigo) {
		Producto productoEnCelda = this.buscarProductoEnCelda(codigo);
		if (productoEnCelda != null) {
			return productoEnCelda.getPrecio();
		} else {
			return 0;
		}
	}

	public Celda buscarCeldaProducto(String codigo) {
		for (int i = 0; i < celdas.size(); i++) {
			Celda celda = this.celdas.get(i);
			if (celda.getProducto() != null) {
				if (celda.getProducto().getCodigo().equals(codigo)) {
					return celda;
				}
			}
		}
		return null;
	}

	public void incrementarProductos(String codigo, int stock) {
		Celda celdaEncontrada = buscarCeldaProducto(codigo);
		if (celdaEncontrada != null) {
			celdaEncontrada.setStock(celdaEncontrada.getStock() + stock);
		} else {
			System.out.println("No se encontro celda para el producto");
		}
	}

	public void vender(String codigo) {
		Celda celdaEncontrada = this.buscarCelda(codigo);
		if (celdaEncontrada != null) {
			celdaEncontrada.setStock(celdaEncontrada.getStock() - 1);
			double precioProducto = celdaEncontrada.getProducto().getPrecio();
			this.saldo += precioProducto;
		} else {
			System.out.println("No se encontro celda");
		}
	}

	public double venderConCambio(String codigo, double valor) {
		Celda celdaEncontrada = this.buscarCelda(codigo);
		if (celdaEncontrada != null) {
			celdaEncontrada.setStock(celdaEncontrada.getStock() - 1);
			double precioProducto = celdaEncontrada.getProducto().getPrecio();
			this.saldo += precioProducto;
			double cambio = valor - precioProducto;
			return cambio;
		} else {
			return 0;
		}
	}

	public ArrayList<Producto> buscarMenores(double limite) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		for (int i = 0; i < celdas.size(); i++) {
			Celda celda = celdas.get(i);
			if (celda.getProducto() != null) {
				Producto prod = celda.getProducto();
				if (prod.getPrecio() <= limite) {
					productos.add(prod);
				}
			}
		}
		return productos;
	}

	// Evaluacion JavaStandard

	// Ingreso de dinero
	public void recibirDinero(double monto) {

		boolean isValido = false;
		//Comparamos el monto ingresado con los montos validos
		for (double montoValido : montosValidos) {
			if (montoValido == monto) {
				this.dineroIngresado += monto;
				if(dineroActual.containsKey(montoValido)){
					this.dineroActual.put(montoValido,dineroActual.get(montoValido)+1);//Agregamos el monto a dineroActual
				}
				else{
					this.dineroActual.put(montoValido,1);
				}
				
				isValido = true;
				break;
			}
		}

		if (!isValido) {
			System.out.println("El monto ingresado no es valido [" + monto + "]");
		}
	}

	// Retornar dinero ingresado
	public double consultarDineroIngresado() {
		return this.dineroIngresado;
	}

	// Colacar dinero ingresado en 0
	public void resetearDinero() {
		this.dineroIngresado = 0;
	}

	// Venden con entrega de cambio recibe el codigo de la celda
	public HashMap<Double, Integer> venderConEntregaCambio(String codigo) {

		Producto producto = this.buscarProductoEnCelda(codigo);

		HashMap<Double, Integer> valorEntregar = new HashMap<Double, Integer>();

		double cambio = this.dineroIngresado - producto.getPrecio();

		System.out.println(cambio);

		// Transforma el cambio a centavos
		double centavos = cambio / 0.01;

		System.out.println(centavos);

		// Valores de 5.00
		if (centavos >= 500) {
			int moneda = (int) centavos / 500;
			valorEntregar.put(5.00, moneda);
			centavos -= moneda * 500;
		}

		// Valores de 1.00
		if (centavos >= 100) {
			int moneda = (int) centavos / 100;
			valorEntregar.put(1.00, moneda);
			centavos -= moneda * 100;
		}

		// Valores de 0.50
		if (centavos >= 50) {
			int moneda = (int) centavos / 50;
			valorEntregar.put(0.50, moneda);
			centavos -= moneda * 50;
		}

		// Valores de 0.25
		if (centavos >= 25) {
			int moneda = (int) centavos / 25;
			valorEntregar.put(0.25, moneda);
			centavos -= moneda * 25;
		}

		// Valores de 0.10
		if (centavos >= 10) {
			int moneda = (int) centavos / 10;
			valorEntregar.put(0.10, moneda);
			centavos -= moneda * 10;
		}

		// Valores de 0.05
		if (centavos >= 5) {
			int moneda = (int) centavos / 5;
			valorEntregar.put(0.05, moneda);
			centavos -= moneda * 5;
		}

		// Valores de 0.01
		if (centavos >= 1) {
			int moneda = (int) centavos / 1;
			valorEntregar.put(0.01, moneda);
			centavos -= moneda * 1;
		}

		return valorEntregar;

	}

	public void cargaInicial(double denominacion, int cantidad) {

		dineroActual.put(denominacion, cantidad);
	}
	
	public void mostrarDineroActual(){
		for (Double denominacion : dineroActual.keySet()) {
			System.out.println(dineroActual.get(denominacion)+" de "+denominacion);
		}
	}

	// Vender con cambio controlado
	public HashMap<Double, Integer> venderConCambioControlado(String codigo) {
		Producto producto = this.buscarProductoEnCelda(codigo);

		HashMap<Double, Integer> valorEntregar = new HashMap<Double, Integer>();

		double cambio = this.dineroIngresado - producto.getPrecio();

		// Transforma el cambio a centavos
		double centavos = cambio / 0.01;
		System.out.println("Cambio a entregar: " + cambio);

		// Verificamos si hay valores de 5 en la maquina
		if (this.dineroActual.containsKey(5.00) && centavos >= 500) {
			for (int i = 1; i <= dineroActual.get(5.00); i++) {
				if (centavos >= 500) {
					centavos -= 500;
					valorEntregar.put(5.00, i);
					this.dineroActual.put(5.00,dineroActual.get(5.00)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 1 en la maquina
		if (this.dineroActual.containsKey(1.00) && centavos >= 100) {
			for (int i = 1; i <= dineroActual.get(1.00); i++) {
				if (centavos >= 100) {
					centavos -= 100;
					valorEntregar.put(1.00, i);
					this.dineroActual.put(1.00,dineroActual.get(1.00)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.50 en la maquina
		if (this.dineroActual.containsKey(0.50) && centavos >= 50) {
			for (int i = 1; i <= dineroActual.get(0.50); i++) {
				if (centavos >= 50) {
					centavos -= 50;
					valorEntregar.put(0.50, i);
					this.dineroActual.put(0.50,dineroActual.get(0.50)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.25 en la maquina
		if (this.dineroActual.containsKey(0.25) && centavos >= 25) {
			for (int i = 1; i <= dineroActual.get(0.25); i++) {
				if (centavos >= 25) {
					centavos -= 25;
					valorEntregar.put(0.25, i);
					this.dineroActual.put(0.25,dineroActual.get(0.25)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.10 en la maquina
		if (this.dineroActual.containsKey(0.10) && centavos >= 10) {
			for (int i = 1; i <= dineroActual.get(0.10); i++) {
				if (centavos >= 10) {
					centavos -= 10;
					valorEntregar.put(0.10, i);
					this.dineroActual.put(0.10,dineroActual.get(0.10)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.05 en la maquina
		if (this.dineroActual.containsKey(0.05) && centavos >= 5) {
			for (int i = 1; i <= dineroActual.get(0.05); i++) {
				if (centavos >= 5) {
					centavos -= 5;
					valorEntregar.put(0.05, i);
					this.dineroActual.put(0.05,dineroActual.get(0.05)-1);
				}
				else{
					break;
				}
			}
		}

		// Verificamos si hay valores de 0.01 en la maquina
		if (this.dineroActual.containsKey(0.01) && centavos >= 1) {
			for (int i = 1; i <= dineroActual.get(0.01); i++) {
				if (centavos >= 1) {
					centavos -= 1;
					valorEntregar.put(0.01, i);
					this.dineroActual.put(0.01,dineroActual.get(0.01)-1);
				}
				else{
					break;
				}
			}
		}

		return valorEntregar;

	}

}
