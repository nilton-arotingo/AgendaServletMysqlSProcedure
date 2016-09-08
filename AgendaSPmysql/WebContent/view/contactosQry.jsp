<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Contactos</title>

        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../datatable/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="../datatable/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css"/>
		<link href="../css/estilos.css" rel="stylesheet" type="text/css"/>
		
    </head>
    <body> 

        <div id="m_main">           

            <div id="m_body">
                <div id="container" style="margin:auto; width: 80%">
					<h1 class="titulo">Lista de Contactos</h1>
                    <div id="demo_jui">
                        <table id="example" class="display table-responsive" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th style="text-align: center"><u>ID</u></th>
                                    <th style="text-align: center"><u>Nombres</u></th>
                                    <th style="text-align: center"><u>Celular</u></th>
                                    <th style="text-align: center"><u>Correo</u></th>
                                    <th style="text-align: center"><u><a class="btn btn-warning" 
                                              href="contactosIns.jsp" role="button">
                                                Insertar</a></u></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="n" items="${lista}">
                                    <tr>                                    
                                        <td style="text-align: center">${n.id}</td>
                                        <td style="text-align: center">${n.nombres}</td>
                                        <td style="text-align: center">${n.celular}</td>
                                       <td style="text-align: center">${n.correo}</td>
                                        <td style="text-align: center"><a class="btn btn-warning" 
                                               href="Contactos?accion=DEL&id=${n.id}" role="button">
                                                <span class="glyphicon glyphicon-trash"></span></a></td>
                                        <td style="text-align: center"><a class="btn btn-warning" 
                                        href="Contactos?accion=FND&id=${n.id}" role="button">
                                                <span class="glyphicon glyphicon-repeat"></span></a></td>                              
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br/>
                <%-- para mensajes  --%>
                <div>${mensaje}</div>

            </div>
            <!--/div-->

        </div>

        <script src="../js/jquery.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../datatable/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../datatable/dataTables.jqueryui.min.js" type="text/javascript"></script>
        
        <!--script src="../../datatableBstp/js/dataTables.bootstrap4.min.js" type="text/javascript"></script-->

        <script type="text/javascript">
            $(document).ready(function () {
                $('#example').dataTable({
                    "lengthMenu": [7, 10, 25, 50, 75, 100],
                    "language": {
                        "lengthMenu": 'Mostrar <select>' +
                                '<option value="7">7</option>' +
                                '<option value="10">10</option>' +
                                '<option value="25">25</option>' +
                                '<option value="50">50</option>' +
                                '<option value="75">75</option>' +
                                '<option value="100">100</option>' +
                                '<option value="-1">Todo</option>' +
                                '</select> registros ***** MANTENIMIENTO CONTACTOS *****',
                        "search": "Buscar:",
                        "info": "Mostrando pagina _PAGE_ de _PAGES_",
                        "paginate": {
                            "previous": "Atras",
                            "next": "Adelante"
                        }
                    }
                });
            });
        </script>
    </body>
</html>
