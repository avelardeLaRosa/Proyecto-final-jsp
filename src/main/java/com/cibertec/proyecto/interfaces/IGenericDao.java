package com.cibertec.proyecto.interfaces;

import java.util.List;

public interface IGenericDao <C,PK> {
	
	public int ingresar (C e) throws Exception;
	public int modificar (C e) throws Exception;
	public int eliminar (PK id) throws Exception;
	public List<C>listars() throws Exception;
	public C obtenerByID (PK id) throws Exception;

}
