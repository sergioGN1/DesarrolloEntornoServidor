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
        </head>
    <body>
        <div class="btn-group">
            <button type="button" class="btn btn-primary"  data-toggle="dropdown">Alumnos</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item add" href="#">Insertar</button>
                <button class="dropdown-item" href="#">Borrar</button>
                <button class="dropdown-item edit" href="#">Actualizar</button>
                <button class="dropdown-item" href="#">Leer</button>
                </div>
            </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-toggle="dropdown">Asignaturas</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item addAsig" href="#">Insertar</button>
                <button class="dropdown-item" href="#">Borrar</button>
                <button class="dropdown-item editAsig" href="#">Actualizar</button>
                <button class="dropdown-item" href="#">Leer</button>
                </div>
            </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-toggle="dropdown">Notas</button>
            <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown">
                <span class="caret"></span>
                </button>
            <div class="dropdown-menu">
                <button class="dropdown-item add" href="#">Insertar</button>
                <button class="dropdown-item" href="#">Borrar</button>
                <button class="dropdown-item edit" href="#">Actualizar</button>
                <button class="dropdown-item" href="#">Leer</button>
                </div>
            </div>

        
        
        
        
        
        
        
        
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
        <div id="updateA" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="alumnos" method="get">
                    <input type="text" name="nombre" placeholder="Introduzca el nombre del alumno">
                    <input type="date" name="fecha_nacimiento">
                    <label for="mayor">Mayor Edad</label>
                    <input type="checkbox" name="mayor_edad" id="mayor">
                    <button name="op" value="actualizar">Enviar</button>
                </form>
                </div>

            </div>

        <div id="modalDelete" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <p>Some text in the Modal..</p>
            </div>

        </div>
        
        
        
        
        
        
        
        
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
        <div id="updateAsig" class="modal">


            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="asignaturas" method="get">
                    <input type="text" name="nombre" placeholder="Introduzca el nombre de la asignatura">
                    <input type="text" name="curso" placeholder="Introduza el curso de la asignatura">
                    <input type="text" name="ciclo" placeholder="Introduza el ciclo de la asignatura">
                    <button name="op" value="actualizar">Enviar</button>
                </form>
                </div>

            </div>

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        </body>
    </html>
