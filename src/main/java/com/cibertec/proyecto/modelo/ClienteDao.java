package com.cibertec.proyecto.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.proyecto.entidades.Cliente;
import com.cibertec.proyecto.interfaces.IClienteDao;

public class ClienteDao implements IClienteDao {
	
	public Connection cn;
	public PreparedStatement ps;
	public CallableStatement cs;
	public ResultSet rs;
	public ArrayList<Cliente> lista;
	public Cliente obj;
	
	
	final String insert = "{call pa_add_cliente(?, ?, ?, ?, ?, ?, ?)}";
	final String update = "{call pa_update(?, ?, ?, ?, ?, ?, ?, ?)}";
	final String delete = "{call pa_delete_cliente(?)}";
	final String list = "{call pa_list_cliente()}";
	final String getById = "{call pa_listById_cliente(?)}";
	
	
	
	
	public ClienteDao(Connection cn) {
		this.cn = cn;
	}
	

	@Override
	public int ingresar(Cliente e) throws Exception {
		int rpta = -1;
		int i=1;
		try {
			cs = cn.prepareCall(insert);
			cs.setString(i++, e.getId_cliente());
			cs.setString(i++, e.getNombre());
			cs.setString(i++, e.getApellido());
			cs.setString(i++, e.getDireccion());
			cs.setString(i++, e.getFecha_nacimiento());
			cs.setString(i++, e.getTelefono());
			cs.setString(i++, e.getEmail());
			rpta = cs.executeUpdate();
			
		}catch (SQLException e2) {
			System.out.println("Error: " + e2.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				
			} catch (SQLException e2) {
			    System.out.println("Error: " + e2.getMessage());
			}
		}
		
		
		return rpta;
	}

	@Override
	public int modificar(Cliente e) throws Exception {
		int rpta = -1;
		int i=1;
		try {
			cs = cn.prepareCall(update);
			cs.setString(i++, e.getId_cliente());
			cs.setString(i++, e.getNombre());
			cs.setString(i++, e.getApellido());
			cs.setString(i++, e.getDireccion());
			cs.setString(i++, e.getFecha_nacimiento());
			cs.setString(i++, e.getTelefono());
			cs.setString(i++, e.getEmail());
			
			
			rpta = cs.executeUpdate();
			
		}catch (SQLException e2) {
			System.out.println("Error: " + e2.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				
			} catch (SQLException e2) {
			    System.out.println("Error: " + e2.getMessage());
			}
		}
		
		
		return rpta;
	}

	@Override
	public int eliminar(String id) throws Exception {
		int rpta = -1;
		int i=1;
		try {
			cs = cn.prepareCall(delete);
			
			cs.setString(i, id);
			
			rpta = cs.executeUpdate();
			
		}catch (SQLException e2) {
			System.out.println("Error: " + e2.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				
			} catch (SQLException e2) {
			    System.out.println("Error: " + e2.getMessage());
			}
		}
		
		
		return rpta;
	}

	@Override
	public List<Cliente> listars() throws Exception {
		lista = new ArrayList<Cliente>();
		try {
			cs = cn.prepareCall(list);
			rs = cs.executeQuery();
			
			while(rs.next()) {
				obj = new Cliente(
						rs.getString("id_cliente"), 
						rs.getString("nombre"), 
						rs.getString("apellido"), 
						rs.getString("direccion"), 
						rs.getString("fecha_nacimiento"), 
						rs.getString("telefono"), 
						rs.getString("email"));
				lista.add(obj);
			}
			
		} catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				System.out.println("Error: "+ e.getMessage());
			}
		}
		
		return lista;
	}

	@Override
	public Cliente obtenerByID(String id) throws Exception {
		try {
			cs = cn.prepareCall(getById);
			cs.setString(1, id);
			rs = cs.executeQuery();
			
			if(rs.next()) {
				obj = new Cliente(
						rs.getString("id_cliente"), 
						rs.getString("nombre"), 
						rs.getString("apellido"), 
						rs.getString("direccion"), 
						rs.getString("fecha_nacimiento"), 
						rs.getString("telefono"), 
						rs.getString("email"));
				
				lista.add(obj);		
			}
			
		} catch (SQLException e) {
			System.out.println("Error: "+ e.getMessage());
		}finally {
			try {
				if(ps!=null) ps.close();
				//if(cn!=null) cn.close();
			} catch (SQLException e) {
				System.out.println("Error: "+ e.getMessage());
			}
		}
		
		return obj;
	}

}
