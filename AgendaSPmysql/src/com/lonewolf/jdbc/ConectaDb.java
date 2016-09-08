package com.lonewolf.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDb {
	
	private final String database;
    private String mensajeDb;

    public Connection getConnection() {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    "root", "mysql01");

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            setMensajeDb(ex.getMessage());
        }

        return cn;
    }

    public ConectaDb() {
        this.database = "agendos";
    }

    public ConectaDb(String database) {
        this.database = database;
    }

	public String getMensajeDb() {
		return mensajeDb;
	}

	public void setMensajeDb(String mensajeDb) {
		this.mensajeDb = mensajeDb;
	}

}
