function saludaExterna(nombre){
    alert("Hola externa " + nombre);
}

/*  
    <script>        - El navegador tiene que esperar hasta que el script 
                      sea descargado, ejecutarlo y solo después procesa 
                      el resto de la página.
    <script async>  - Lo descarga y, cuando se cargue, se ejecuta.
    <script defer>  - Se ejecuta hasta el final 
    global  - nameVar
    local   - var nameVar 
    bloque  - let nameVar
*/