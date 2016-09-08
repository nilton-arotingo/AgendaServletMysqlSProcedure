package com.lonewolf.service;

import java.util.List;

import com.lonewolf.dto.Contactos;

public interface ServiceContactos {
	
	public List<Contactos> contactosQry();
	
	public void contactosIns(Contactos contactos);
	
	public Contactos contactosFnd(Integer id);
	
	public void contactosUpd(Contactos contactos);
	
	public void contactosDel(Integer id);
	
	public String getMensaje();

}
