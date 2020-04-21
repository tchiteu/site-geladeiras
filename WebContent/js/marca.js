COLDIGO.marca = new Object();

$(document).ready(function() {
		
	COLDIGO.marca.cadastrar = function() {	
		var marca = new Object()
		marca.nome = document.frmAddMarca.nome.value;
		
		if(!marca.nome || marca.nome.length < 1) {
			COLDIGO.exibirAviso("Preencha todos os campos!")
		} else {
			$.ajax({
				type: "POST",
				url: `${COLDIGO.PATH}marca/inserir`,
				data:JSON.stringify(marca),
				success: function(msg) {
//					COLDIGO.marca.buscar();
					COLDIGO.exibirAviso(msg);
					$("#addMarca").trigger("reset");
				},
				error: function(info) {
					COLDIGO.exibirAviso(`Erro ao cadastrar uma nova marca: ${info.status} - ${info.statusText}`)
				}
			})
		}
	};
	
	
//	COLDIGO.marca.buscar();
})