package com.cibertec.proyecto.servicio;

import com.cibertec.proyecto.interfaces.IClienteDao;
import com.cibertec.proyecto.modelo.Factory;


public class ClienteService {

	Factory factory = Factory.getInstancia();
	IClienteDao dao = factory.getClienteDao();

}
