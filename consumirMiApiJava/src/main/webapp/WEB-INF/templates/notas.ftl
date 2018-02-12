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
        <#if nota??>
            <div>La nota del alumnos solicidao es: ${nota}</div>
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
