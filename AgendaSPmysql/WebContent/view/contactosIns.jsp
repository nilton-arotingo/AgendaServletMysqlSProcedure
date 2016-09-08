<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Ingresar Contactos</title>

        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="../icons/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="../css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body> 
		<br/>
		<br/>
		<br/>
        <div id="m_main">   

            <div id="m_body" >
                <div class="container">

                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="page-header"  style="text-align: center; margin-top: 5px">
                                    <h3>Insert Contactos</h3>
                                </div>
                                <form action="Contactos" method="POST"  class="form-horizontal">
                                    <input type="hidden" name="accion" value="INS"/>  

                                    
                                                   
                                    <div class="form-group form-group-sm">
                                        <label for="nombres" class="control-label col-md-4">Nombres</label>
                                        <div class="col-md-8">									
                                            <input type="text" class="form-control" name="nombres" value="${contactos.nombres}"
                                                   placeholder="Nombres" required="" maxlength="30"/>							
                                        </div>
                                    </div>

                                    <div class="form-group form-group-sm">
                                        <label for="celular" class="control-label col-md-4">Celular</label>
                                        <div class="col-md-8">									
                                            <input type="text" class="form-control" name="celular" value="${contactos.celular}"
                                                   placeholder="Celular" required="" maxlength="9"/>							
                                        </div>
                                    </div>          
                                                 
                                   <div class="form-group form-group-sm">
                                        <label for="correo" class="control-label col-md-4">Correo</label>
                                        <div class="col-md-8">									
                                            <input type="text" class="form-control" name="correo" value="${contactos.correo}"
                                                   placeholder="Correo" required="" maxlength="30"/>							
                                        </div>
                                    </div> 


                                    <hr/>
                                    <div class="form-group">
                                        <div class="col-md-4 col-sm-offset-2">
                                            <button type="submit" class="btn btn-info">Aceptar</button>
                                        </div>
                                        <div class="col-md-4 col-sm-offset-2">
                                            <button type="button" id="btnCancelar" class="btn btn-warning" >Cancelar</button>
                                        </div>	
                                    </div>
                                    
                                </form>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <%-- para mensajes  --%>
                    <div>${mensaje}</div>
                    
                </div>
            </div>
        </div>

        <script src="../js/jquery.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
		<script src="../js/contactos.js" type="text/javascript"></script>
        
    </body>
</html>