COLDIGO.produto = new Object();

$(document).ready(function() {
	COLDIGO.produto.carregaMarcas = function() {
		alert("Tentando buscar marcas")
		$.ajax({
			type: "GET",
			url: "/ProjetoTrilhaWeb/rest/marca/buscar",
			success: function (marcas) {
				if(marcas) {
					$("#selMarca").html("")
					var option = document.createElement("option")
					option.setAttribute("value", "")
					option.innerHTML = ("Escolha")
					$("#selMarca").append(option)
					
					for(var i = 0; i < marcas.lenght; i++) {
						var option = document.createElement("option")
						option.setAttribute("value", marcas[i].id)
						option.innerHTML = (marcas[i].nome)
						console.log(option)
						$("#selMarca").append(option)
					}
				} else {
					$("#selMarca").html("")
					var option = document.createElement("option")
					option.setAttibute("value", "")
					option.innerHTML = ("Cadastre uma marca primeiro!")
					$("#selMarca").append(option)
					$("#selMarca").addClass("aviso")
				}
			},
			error: function (info) {
				COLDIGO.exibirAviso("Erro ao buscar as marcas: "+ info.status + " - " + info.statusText)
				$("#selMarca").html("")
				var option = document.createElement("option")
				option.setAttribute("value", "")
				option.innerHTML = ("Erro ao carregar marcas!")
				$("#selMarca").append(option)
				$("#selMarca").addClass("aviso")
			}
		})
	}
	
	COLDIGO.produto.carregaMarcas()
})