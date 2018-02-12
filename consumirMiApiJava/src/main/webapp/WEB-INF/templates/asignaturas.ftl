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
        <#if error??>
            <#if error == 500>
                <h1>Esta asginatura tiene notas asociadas y es necesario borrarlas, ¿Desea borrarlas?</h1>
                <form action="asignaturas">
                    <input type="hidden" name="id" value="${idAsignaturaDelete}">

                    <button value="deleteTotal" name="op">CONFIRMAR</button>
                </form>
            </#if>
        </#if>
        
        <#if codigo??>
                <#if codigo==200>
                    <div>${mensaje}</div>
                <#elseif codigo==500>
                    <div>${mensaje}</div>
                </#if>
        </#if>
        <a href="eleccion">Vovler</a>
    </body>
</html>
