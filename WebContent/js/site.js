function validaFaleConosco() {
    if(document.frmfaleconosco.txtnome.value == ""){
        alert("Preencha o campo nome.");
        document.frmfaleconosco.txtnome.focus();
        return false;
    }
    
    if(document.frmfaleconosco.txtfone.value == ""){
        alert("Preencha o campo telefone.");
        document.frmfaleconosco.txtfone.focus();
        return false;
    }

    if(document.frmfaleconosco.txtemail.value == ""){
        alert("Preencha o campo e-mail.");
        document.frmfaleconosco.txtemail.focus();
        return false;
    }

    if(document.frmfaleconosco.selmotivo.value == ""){
        alert("Preencha o campo motivo.");
        document.frmfaleconosco.selmotivo.focus();
        return false;
    }
    
    if(document.frmfaleconosco.selmotivo.value == "PR"){
        if(document.frmfaleconosco.selproduto.value == ""){
            alert("Preencha o campo produto.");
            document.frmfaleconosco.selproduto.focus();
            return false;
        }
    }

    if(document.frmfaleconosco.txtcomentario.value == ""){
        alert("Preencha o campo comentário.");
        document.frmfaleconosco.txtcomentario.focus();
        return false;
    }


    return true;
}

function verificaMotivo(motivo) {
    var elemento = document.getElementById("opcaoProduto");
    
    if(motivo == "PR"){
        var select = document.createElement("select");
        select.setAttribute("name", "selproduto");
        
        var option = document.createElement("option");
        option.setAttribute("value", "");
        
        var texto = document.createTextNode("Escolha");
        option.appendChild(texto);

        var option2 = document.createElement("option");
        option2.setAttribute("value", "1");
        
        var texto = document.createTextNode("Freezer");
        option2.appendChild(texto);
        
        select.appendChild(option);
        select.appendChild(option2);
        elemento.appendChild(select);
    } else {
        if(elemento.firstChild) {
            elemento.removeChild(elemento.firstChild);
        }
    }


}