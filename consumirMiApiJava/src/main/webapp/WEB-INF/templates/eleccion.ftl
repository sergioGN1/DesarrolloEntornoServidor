<#ftl strip_whitespace = true>

<#assign charset="UTF-8">
<#assign title="Example">
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="assets/css/modales.css">
        <script src="assets/js/modalUpdate.js"></script>
        <script src="assets/js/modalAdd.js"></script>
        <script src="assets/js/modalDelete.js"></script>
        <script src="assets/js/modalLeer.js"></script>
        <script>
            $(document).ready(function(){
                var fecha = "";
                var nombre="";
                var mayor="";
                var nombreAsig="";
                var curso = "";
                var ciclo="";
                $("#selectAlumno").change(function(){
                    $(".opciones").each(function(){
                        fecha = $(this).attr("data-fecha");
                        nombre = $(this).text();
                        mayor = $(this).attr("data-mayor");
                    });
                    $("#fecha").val(fecha);
                    $("#nombre").val(nombre);
                    if (mayor){
                        $("#mayor").prop( "checked", true );
                    } else {
                        $("#mayor").prop( "checked", false );
                    }
                            
                });
                   
            });
            </script>
        </head>
    <body>
        <div class="btn-group">
            <button type="button" class="btn btn-primary"  data-toggle="dropdown">Alumnos</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item add">Insertar</button>
                <button class="dropdown-item deleteAlumno">Borrar</button>
                <button class="dropdown-item edit">Actualizar</button>
                <button class="dropdown-item leerAlumnos" href="#">Leer</button>
                </div>
            </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-toggle="dropdown">Asignaturas</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item addAsig" href="#">Insertar</button>
                <button class="dropdown-item deleteAsig" href="#">Borrar</button>
                <button class="dropdown-item editAsig" href="#">Actualizar</button>
                <button class="dropdown-item leerAsig" href="#">Leer</button>

                </div>
            </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-toggle="dropdown">Notas</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item addNota">Insertar</button>
                <button class="dropdown-item deleteNota">Borrar</button>
                <button class="dropdown-item editNota">Actualizar</button>
                <button class="dropdown-item leerNota">Leer</button>

                </div>
            </div>







    <!-- Añadir alumno-->

        <div id="addA" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="alumnos" method="get">
                    <input type="text" name="nombre" placeholder="Introduzca el nombre del alumno">
                    <input type="date" name="fecha_nacimiento">
                    <label for="mayor">Mayor Edad</label>
                    <input type="checkbox" name="mayor_edad" id="mayor">
                    <button name="op" value="insertar">Enviar</button>
                    </form>
                </div>

            </div>
    <!-- Actualizar alumno-->
        <div id="updateA" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="alumnos" method="get">
                    <select name="id" id="selectAsignatura">
                        <#list alumnos as alumno>
                        <option value="${alumno.id}" class="opciones" data-fecha="${alumno.fecha_nacimiento}" data-mayor="${alumno.mayor_edad?c}">${alumno.nombre}</option>
                        </#list>
                        </select>
                    <input type="text" name="nombre" id="nombre">
                    <input type="date" name="fecha_nacimiento" id="fecha">
                    <input type="checkbox" name="mayor_edad" id="mayor">
                    <button name="op" value="actualizar">Enviar</button>
                    </form>
                </div>

            </div>
    <!-- leer alumno-->
        <div id="leerAlumnos" class="modal leer">

            <span class="close">&times;</span>
            <div class="modal-content">
                <table class="table table-sttriped">
                    <tr class=" table table-dark">
                        <td>Nombre</td>
                        <td>Fecha Nacimiento</td>
                        <td>Mayor de Edad</td>
                    </tr>
                        <#list alumnos as alumno>
                    <tr id="${alumno.id}">
                        <td>${alumno.nombre}</td>
                        <td>${alumno.fecha_nacimiento}</td>
                        <td>${alumno.mayor_edad?c}</td>
                    </tr>
                        </#list>
                </table>
            </div>

        </div>
        <!-- Borrar Alumno-->
        <div id="modalDeleteAlumno" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="alumnos" method="get">
                    <select name="id" id="selectAlumno">
                        <#list alumnos as alumno>
                        <option value="${alumno.id}" class="opciones">${alumno.nombre}</option>
                        </#list>
                        </select>
                    <button name="op" value="delete">Enviar</button>
                    </form>
                </div>

            </div>



<!-- Modales referentes a las asignaturas-->


    <!-- Añadir asignatura-->

        <div id="addAsig" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="asignaturas" method="get">
                    <input type="text" name="nombre" placeholder="Introduzca el nombre de la asignatura">
                    <input type="text" name="curso" placeholder="Introduza el curso de la asignatura">
                    <input type="text" name="ciclo" placeholder="Introduza el ciclo de la asignatura">
                    <button name="op" value="insertar">Enviar</button>
                    </form>
                </div>

            </div>
    <!-- Actualizar asignatura-->
        <div id="updateAsig" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="asignaturas" method="get">
                    <select name="id">
                        <#list asignaturas as asignatura>
                            <option value="${asignatura.id}" data-curso="${asignatura.curso}" data-ciclo="${asignatura.ciclo}" class="opciones">${asignatura.nombre}</option>
                        </#list>
                    </select>
                    <input type="text" name="nombre" id="nombreAsig">
                    <input type="text" name="curso" id="curso">
                    <input type="text" name="ciclo" id="ciclo">
                    <button name="op" value="actualizar">Enviar</button>
                </form>
            </div>

        </div>
    <!-- Borrar asignatura-->
        <div id="modalDeleteAsignatura" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="asignaturas" method="get">
                    <select name="id">
                        <#list asignaturas as asignatura>
                            <option value="${asignatura.id}">${asignatura.nombre}</option>
                        </#list>
                    </select>
                    <button name="op" value="delete">Enviar</button>
                    </form>
                </div>

            </div>
    <!-- Leer asignatura-->
        <div id="leerAsignatura" class="modal leer">

            <span class="close">&times;</span>
            <div class="modal-content">
                <table class="table table-sttriped">
                    <tr class=" table table-dark">
                        <td>Nombre</td>
                        <td>Curso</td>
                        <td>Ciclo</td>
                    </tr>
                        <#list asignaturas as asignatura>
                    <tr id="${asignatura.id}">
                        <td>${asignatura.nombre}</td>
                        <td>${asignatura.curso}</td>
                        <td>${asignatura.ciclo}</td>
                    </tr>
                        </#list>
                </table>
            </div>

        </div>
        
    <!--Insertar nota-->
        <div id="insertarNota" class="modal">

            <span class="close">&times;</span>
            <div class="modal-content">
                <form action="notas" method="get">
                    <select name="id_alumno">
                        <#list alumnos as alumnoNota>
                            <option value="${alumnoNota.id}">${alumnoNota.nombre}</option>
                        </#list>
                    </select>
                    <select name="id_asignatura">
                        <#list asignaturas as asignaturaNota>
                            <option value="${asignaturaNota.id}">${asignaturaNota.nombre}</option>
                        </#list>
                    </select>
                    <input type="text" name="valorNota" placeholder="Introduzca el valor de la nota">
                    <button name="op" value="insertar">Enviar</button>
                </form>
            </div>

        </div>
    <!--Borra Notas-->
        <div id="modalDeleteNota" class="modal">

            <span class="close">&times;</span>
            <div class="modal-content">
                <form action="notas" method="get">
                    <select name="id_alumno">
                        <#list alumnos as alumnoNota>
                            <option value="${alumnoNota.id}">${alumnoNota.nombre}</option>
                        </#list>
                    </select>
                    <select name="id_asignatura">
                        <#list asignaturas as asignaturaNota>
                            <option value="${asignaturaNota.id}">${asignaturaNota.nombre}</option>
                        </#list>
                    </select>
                    <button name="op" value="delete">Enviar</button>
                </form>
            </div>

        </div>
    <!--Ver nota-->
        <div id="leerNota" class="modal leer">

            <span class="close">&times;</span>
            <div class="modal-content">
                <form action="notas" method="get">
                    <select name="id_alumno">
                        <#list alumnos as alumnoNota>
                            <option value="${alumnoNota.id}">${alumnoNota.nombre}</option>
                        </#list>
                    </select>
                    <select name="id_asignatura">
                        <#list asignaturas as asignaturaNota>
                            <option value="${asignaturaNota.id}">${asignaturaNota.nombre}</option>
                        </#list>
                    </select>
                    <#if nota??>
                    <input type="text" name="notaAlumno" id="notaDelAlumno" value="${nota}">
                    <#else>
                    <input type="text" name="notaAlumno" id="notaDelAlumno" value="">
                    </#if>
                    <button name="op" value="leer">Enviar</button>
                </form>
            </div>

        </div>
    <!--Actualizar Nota-->
        
        <div id="updateNota" class="modal">

            <span class="close">&times;</span>
            <div class="modal-content">
                <form action="notas" method="get">
                    <select name="id_alumno">
                        <#list alumnos as alumnoNota>
                            <option value="${alumnoNota.id}">${alumnoNota.nombre}</option>
                        </#list>
                    </select>
                    <select name="id_asignatura">
                        <#list asignaturas as asignaturaNota>
                            <option value="${asignaturaNota.id}">${asignaturaNota.nombre}</option>
                        </#list>
                    </select>
                    <input type="text" name="valorNota" placeholder="Introduzca el valor de la nota">
                    <button name="op" value="actualizar">Enviar</button>
                </form>
            </div>

        </div>
        </body>
    </html>
