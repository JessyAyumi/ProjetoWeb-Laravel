<%-- 
    Document   : listaTudo
    Created on : 30/11/2017, 20:23:36
    Author     : Jéssica Ayumi Uehara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="content-type"
              content="text/html; charset=utf-8" />
    </head>
    <body>
        <input type="submit" name="busca" value="Listar" />
        <ul id="container"></ul>
        <script type="text/javascript" charset="utf-8">
            var container = document.querySelector("#container");
            document.querySelector("input").addEventListener("click", function () {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "listaTudo", true);
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                        container.innerHTML = xmlhttp.responseText;
                };
                xmlhttp.send();
            })
        </script>
    </body>
</html>
