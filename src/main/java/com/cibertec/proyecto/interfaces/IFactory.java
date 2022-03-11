package com.cibertec.proyecto.interfaces;
import com.cibertec.proyecto.interfaces.IClienteDao;

public interface IFactory {
	
	IClienteDao getClienteDao();
	
}
