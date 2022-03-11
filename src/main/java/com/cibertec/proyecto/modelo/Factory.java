package com.cibertec.proyecto.modelo;

import java.sql.Connection;


import com.cibertec.proyecto.cnx.Conexion;
import com.cibertec.proyecto.interfaces.IClienteDao;
import com.cibertec.proyecto.interfaces.IFactory;

public class Factory implements IFactory {

	private static final Factory instancia = new Factory();
	private Connection cn;
	
	private Factory() {
		cn = new Conexion().getConn();
	}
	public static Factory getInstancia() {
		return instancia;
	}
	
	private IClienteDao cliente=null;
	@Override
	public IClienteDao getClienteDao() {
		if(cliente == null) {
			cliente = new ClienteDao(cn);
		}
		return null;
	}

}
