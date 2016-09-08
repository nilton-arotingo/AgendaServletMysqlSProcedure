package com.lonewolf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lonewolf.dao.ServiceContactosImpl;
import com.lonewolf.dto.Contactos;
import com.lonewolf.service.ServiceContactos;
import com.lonewolf.tools.Convierte;


@WebServlet({ "/Contactos", "/view/Contactos" })
public class ServletContactos extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		String mensaje = null;
		String direccionar = null;
		
		ServiceContactos service = new ServiceContactosImpl();
		
		switch (accion) {
		
		case "QRY":
			List<Contactos> lista = service.contactosQry();
			
			if (lista != null) {
                request.setAttribute("lista", lista);
            } else {
                mensaje = service.getMensaje();
            }
            direccionar = "contactosQry.jsp";
			
			break;
			
		case "INS":
			Contactos contactos = new Contactos();
            mensaje = verificar(request, contactos);
            
            if (mensaje == null) {
            	service.contactosIns(contactos);
                mensaje = service.getMensaje();
                
                if (mensaje != null) {
                    request.setAttribute("contactos", contactos);
                    direccionar = "contactosIns.jsp";
                } else {
                	direccionar = "Contactos?accion=QRY";
                }
                
            } else {
                request.setAttribute("contactos", contactos);
                direccionar = "contactosIns.jsp";
            }
			break;
		
		case "FND":
			contactos = null;
			Integer id = Convierte.aInteger(request.getParameter("id"));

            if (id != null) {
            	contactos = service.contactosFnd(id);

                if (contactos != null) {
                    
                    request.setAttribute("contactos", contactos);
                    
                    direccionar = "contactosUpd.jsp";
                } else {
                    mensaje = service.getMensaje();
                    direccionar = "Contactos?accion=QRY";
                }

            } else {
                mensaje = "No se ha recibido el ID de Contactos.";
                direccionar = "Contactos?accion=QRY";
            }
			break;
			
		case "UPD":
			contactos = new Contactos();
            mensaje = verificar(request, contactos);

            if (mensaje == null) {
            	service.contactosUpd(contactos);
                mensaje = service.getMensaje();
                
                if (mensaje != null) {
                    request.setAttribute("contactos", contactos);
                    direccionar = "contactosUpd.jsp";
                } else {
                	direccionar = "Contactos?accion=QRY";
                }
                
            } else {
                request.setAttribute("contactos", contactos);
                direccionar = "contactosUpd.jsp";
            }
			break;
			
		case "DEL":
			id = Convierte.aInteger(request.getParameter("id"));

            if (id != null) {
            	service.contactosDel(id);
            	mensaje = service.getMensaje();                   

            } else {
                mensaje = "No se ha recibido el id del contacto.";
                direccionar = "Contactos?accion=QRY";
            }

            direccionar = "Contactos?accion=QRY";
			break;
			
		default:
			mensaje = "Accion no reconicida";
			
		}
		
		if (mensaje != null) {
			String msg = "<div class=\"col-md-5 col-md-offset-3\" style=\"text-align: center\">";
			msg += "<div class=\"alert alert-danger\">";
			msg += "<button class=\"close\" data-dismiss=\"alert\"><span>&times;</span></button>";
			msg += "<strong>Alerta!!</strong><br/>";
			msg += mensaje;
			msg += "</div></div>";
			request.setAttribute("mensaje", msg);
		}
		
		RequestDispatcher despachador = request.getRequestDispatcher(direccionar);
		despachador.forward(request, response);
				
	}
	
	private String verificar(HttpServletRequest request, Contactos contactos) {
	        
	        
	        String mensaje = "<ul>";
	//        System.out.println("tamano " + mensaje.length());
	        Integer id = Convierte.aInteger(request.getParameter("id"));
	        String nombres = request.getParameter("nombres");	        
	        Integer celular = Convierte.aInteger(request.getParameter("celular"));
	        String correo = request.getParameter("correo");
	        
	        if ((nombres == null) || (nombres.trim().length() == 0)) {
	            mensaje += "<li>Verifique que halla ingresado correctamente el nombre</li>";
	        }
	        if ((celular == null) || (celular == 0)) {
	            mensaje += "<li>Verifique que halla ingresado un numero correctamente para el celular</li>";
	        }
	        if ((correo == null) || (correo.trim().length() == 0)) {
	            mensaje += "<li>Verifique que halla ingresado correctamente el correo</li>";
	        }
	        
	        
	        contactos.setId(id);
	        contactos.setNombres(nombres);
	        contactos.setCelular(celular);
	        contactos.setCorreo(correo);
	        
	        if (mensaje.equals("<ul>")) {
	            mensaje = null;
	        } else {
	            mensaje += "</ul>";
	        }
	
	        return mensaje;
	    }

}