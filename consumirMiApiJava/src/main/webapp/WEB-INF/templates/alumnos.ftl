<#ftl strip_whitespace = true>

<#assign charset="UTF-8">
<#assign title="Example">
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
    <body>
        <#if error == 500>
        <h1>Este alumno tiene notas asociadas y es necesario borrarlas, ¿Desea borrarlas?</h1>
        <form action="alumnos">
            <input type="hidden" name="id" value="${idAlumnoBorrar}">
            
            <button value="deleteTotal" name="op">CONFIRMAR</button>
        </form>
        </#if>
        
    </body>
</html>
