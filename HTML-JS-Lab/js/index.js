function updateName(){    
    radioGen = document.getElementsByName('gen');
    gen = ""
    for(i = 0; i < radioGen.length; i++) {
        if(radioGen[i].checked)
            gen = radioGen[i].value;
    }    
    nom = document.getElementById("nom").value;
    ap = document.getElementById("ap").value;
    label = document.getElementById("name");    
    if (gen == "Masculino"){
        label.innerHTML = "Gracias Sr. " + nom + " " + ap + " por el interés en nuestros seguros"; 
    }else{
        label.innerHTML = "Gracias Sra. " + nom + " " + ap + " por el interés en nuestros seguros";
    }

}