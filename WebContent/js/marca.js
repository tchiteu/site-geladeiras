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
	
	COLDIGO.marca.exibir = function(listaDeMarcas) {
		var tabela = "<table>" +
		"<tr>" +
		"<th>Nome</th>" +
		"<th class='acoes'>Ações</th>" +
		"</tr>";
		
		if (listaDeMarcas != undefined && listaDeMarcas.length > 0) {
			for(var i=0; i < listaDeMarcas.length; i++) {
				tabela += "<tr>" +
				"<td>"+listaDeMarcas[i].nome+"</td>" + +
				"<td>" + 
					"<a onclick=\"COLDIGO.marca.exibirEdicao('"+listaDeMarcas[i].id+"')\"><img src='../../imgs/edit.png' alt='Editar registro'></a>" + 
					"<a onclick=\"COLDIGO.marca.excluir('"+listaDeMarcas[i].id+"')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
				"</td>" +
				"</tr>"
			}
			
		} else if (listaDeMarcas == "") {
			tabela += "<tr><td colspan='6'>Nenhum registro encontrado</td></tr>"
		}
		tabela += "</table>";
		
		return tabela;
	};
	
	COLDIGO.marca.buscarPorNome = function() {
		const valorBusca = $("#campoBuscaMarca").val();
		console.log(valorBusca);
		$.ajax({
			type: "GET",
			url: `${COLDIGO.PATH}marca/buscar`,
			data: "	="+valorBusca,
			success: function(dados) {
				console.log(dados);
				dados = JSON.parse(dados);
				$("#listaMarcas").html(COLDIGO.marca.exibir(dados));
			},
			error: function(info) {
				COLDIGO.exibirAviso(`Erro ao buscar marca: ${info.status} - ${info.statusText}`)
			}
		})
	}
	
	
//	COLDIGO.marca.buscar();
})