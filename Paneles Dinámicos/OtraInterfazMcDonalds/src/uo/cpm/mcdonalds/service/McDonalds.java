package uo.cpm.mcdonalds.service;

import uo.cpm.mcdonalds.model.*;

public class McDonalds {
	Carta carta = new Carta();
	Pedido pedido = new Pedido(); 
	
	public McDonalds() {

	}

	public Articulo[] getArticulosCarta() {
			return carta.getArticulos();
	}
	
   public Articulo getArticulo(int i) {
	   return carta.getListaArticulos().get(i);
   }
	
	public void inicializarPedido() {
		pedido.inicializar();
	}
	
	public void añadirAPedido(Articulo articulo, int unidades) {
		pedido.add(articulo, unidades);
	
	}
	public void remove(Articulo articulo, int unidades) {
		pedido.remove(articulo, unidades);
	}

	public float getTotalPedido() {
		return pedido.getTotal();
	}
 
	public void grabarPedido() {
		pedido.grabarPedido();
	}

	public int buscarUnidades(Articulo a) {
		return pedido.buscarUnidades(a);
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public String getTextoPedido() {
		return pedido.toString();
	}
	
	public int numeroArticulosCarta() {
		return carta.getNumArticulos();
	}

	public String getCodigoPedido() {
		return pedido.getCodigo();
	}
}
