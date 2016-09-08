package com.lonewolf.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.lonewolf.dto.Contactos;
import com.lonewolf.jdbc.ConectaDb;
import com.lonewolf.service.ServiceContactos;

public class ServiceContactosImpl implements ServiceContactos {
	
	private final ConectaDb db;
	private String mensaje;

	public ServiceContactosImpl() {
		this.db = new ConectaDb();
	}

	@Override
	public List<Contactos> contactosQry() {
		List<Contactos> lista = null;
		
		String sql = "{CALL SP_LISTAR()}";
		
		Connection cn = db.getConnection();
		
		if (cn != null) {
			try {
				CallableStatement cs = cn.prepareCall(sql);
				
				ResultSet rs = cs.executeQuery();
				lista = new LinkedList<Contactos>();
				while (rs.next()) {
					Contactos contactos = new Contactos();
					contactos.setId(rs.getInt(1));
					contactos.setNombres(rs.getString(2));
					contactos.setCelular(rs.getInt(3));
					contactos.setCorreo(rs.getString(4));
					
					lista.add(contactos);
				}
				cs.close();
				
			} catch (SQLException e) {
				setMensaje("Problemas para listar: " + e.getMessage());
			} finally {
				try {
					cn.close();
				} catch (SQLException ex) {
					setMensaje(ex.getMessage());
				}
			}
		} else {
			setMensaje("Error en conexion: " + db.getMensajeDb());
		}
		
		return lista;
	}

	@Override
	public void contactosIns(Contactos contactos) {
		
		String sql = "{CALL SP_INSERTAR(?,?,?)}";
		
		Connection cn = db.getConnection();
		if (cn != null) {
			try {
				CallableStatement cs = cn.prepareCall(sql);
				
				cs.setString(1, contactos.getNombres());
				cs.setInt(2, contactos.getCelular());
				cs.setString(3, contactos.getCorreo());
				
				int exec = cs.executeUpdate();
				
				if (exec == 0) {
					throw new SQLException();
				}
				cs.close();
				
			} catch (SQLException e) {
				setMensaje("Problemas para insertar: " + e.getMessage());
			} finally {
				try {
					cn .close();
				} catch (SQLException ex) {
					setMensaje(ex.getMessage());
				}
			}
		} else {
			setMensaje("Error en conexion: " + db.getMensajeDb());
		}
		
	}

	@Override
	public Contactos contactosFnd(Integer id) {
		Contactos contactos = null;
		
		String sql = "{CALL SP_CONSULTAR(?)}";
		
		Connection cn = db.getConnection();
		
		if (cn != null) {
			try {
				CallableStatement cs = cn.prepareCall(sql);
				
				cs.setInt(1, id);
				
				ResultSet rs = cs.executeQuery();
				while (rs.next()) {
					contactos = new Contactos();
					
					contactos.setId(rs.getInt(1));
					contactos.setNombres(rs.getString(2));
					contactos.setCelular(rs.getInt(3));
					contactos.setCorreo(rs.getString(4));
				}
				cs.close();
				
			} catch (SQLException e) {
				setMensaje("Problemas para consultar: " + e.getMessage());
			} finally {
				try {
					cn.close();
				} catch (SQLException ex) {
					setMensaje(ex.getMessage());
				}
			}
		} else {
			setMensaje("Error en conexion: " + db.getMensajeDb());
		}
		return contactos;
	}

	@Override
	public void contactosUpd(Contactos contactos) {
		String sql = "{CALL SP_ACTUALIZAR(?,?,?,?)}";
		
		Connection cn = db.getConnection();
		if (cn != null) {
			try {
				CallableStatement cs = cn.prepareCall(sql);
				
				cs.setString(1, contactos.getNombres());
				cs.setInt(2, contactos.getCelular());
				cs.setString(3, contactos.getCorreo());
				cs.setInt(4, contactos.getId());
				
				int exec = cs.executeUpdate();
				
				if (exec == 0) {
					throw new SQLException();
					
				}
				cs.close();
				
			} catch (SQLException e) {
				setMensaje("Problemas para actualizar: " + e.getMessage());
			} finally {
				try {
					cn .close();
				} catch (SQLException ex) {
					setMensaje(ex.getMessage());
				}
			}
		} else {
			setMensaje("Error en conexion: " + db.getMensajeDb());
		}
		
	}

	@Override
	public void contactosDel(Integer id) {
		String sql = "{CALL SP_ELIMINAR(?)}";
		
		Connection cn = db.getConnection();
		if (cn != null) {
			try {
				CallableStatement cs = cn.prepareCall(sql);

				cs.setInt(1, id);
				
				int exec = cs.executeUpdate();
				
				if (exec == 0) {
					throw new SQLException();
				}
				cs.close();
				
			} catch (SQLException e) {
				setMensaje("Problemas para eliminar: " + e.getMessage());
			} finally {
				try {
					cn .close();
				} catch (SQLException ex) {
					setMensaje(ex.getMessage());
				}
			}
		} else {
			setMensaje("Error en conexion: " + db.getMensajeDb());
		}
		
	}

	@Override
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
