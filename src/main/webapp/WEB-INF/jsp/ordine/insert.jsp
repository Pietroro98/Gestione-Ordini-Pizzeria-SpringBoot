<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
	<head>
		<jsp:include page="../header.jsp" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<title>Nuovo Ordine</title>
	</head>
		<body>
			<jsp:include page="../navbar.jsp" />
			<main class="container-fluid px-4 px-xl-5 py-5">
				<div class="row justify-content-center">
					<div class="col-12 col-lg-10 col-xl-9 col-xxl-8">
						<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
							<div class="card-header bg-danger text-white">
								<h4 class="mb-0">Inserisci nuovo ordine</h4>
							</div>
							<div class="card-body p-4">
								<spring:hasBindErrors name="insert_ordine_attr">
									<div class="alert alert-danger">Sono presenti errori di validazione</div>
								</spring:hasBindErrors>
								<form:form method="post" modelAttribute="insert_ordine_attr" action="save" class="row g-3" id="ordineForm">
									<div class="col-md-4">
										<label class="form-label" for="codice">Codice</label>
										<form:input path="codice" cssClass="form-control" id="codice" />
										<form:errors path="codice" cssClass="text-danger" />
									</div>
									<div class="col-md-4">
										<label class="form-label" for="dataOrdine">Data ordine</label>
										<form:input path="dataOrdine" cssClass="form-control" id="dataOrdine" type="datetime-local" />
										<form:errors path="dataOrdine" cssClass="text-danger" />
									</div>
									<%--<div class="col-md-4">
										<label class="form-label" for="closed">Closed</label>
										<form:select path="closed" cssClass="form-select" id="closed">
											<form:option value="false">false</form:option>
											<form:option value="true">true</form:option>
										</form:select>
									</div>--%>
									<div class="col-md-4">
										<label class="form-label" for="clienteSearchInput">Cliente</label>
										<input class="form-control" id="clienteSearchInput" value="${not empty insert_ordine_attr.cliente.nome or not empty insert_ordine_attr.cliente.cognome ? insert_ordine_attr.cliente.nome.concat(' ').concat(insert_ordine_attr.cliente.cognome) : ''}">
										<input type="hidden" name="cliente.id" id="clienteId" value="${insert_ordine_attr.cliente.id}">
										<input type="hidden" name="livelloPromo" id="clientePromo">
										<form:errors path="cliente" cssClass="text-danger" />
									</div>

									<div id="promoBanner" class="promo-banner d-none mt-3"></div>

									<div class="col-12">
										<button class="btn btn-outline-danger w-100 text-start d-flex justify-content-between align-items-center mt-2" type="button" data-bs-toggle="collapse" data-bs-target="#pizzeDropdown" aria-expanded="false" aria-controls="pizzeDropdown">
											<span>Seleziona pizze</span>
											<i class="bi bi-chevron-down"></i>
										</button>
										<div class="collapse mt-2" id="pizzeDropdown">
											<div class="card border-0 border-top rounded-0">
												<div class="card-body p-3">
													<input class="form-control mb-3" id="pizzaSearchInput" type="text" placeholder="Cerca pizze per nome o ingredienti...">
													<div class="list-group list-group-flush" id="pizzeDisponibiliContainer" style="max-height: 400px; overflow-y: auto;">
														<c:forEach items="${pizza_list_attribute}" var="pizza">
															<div class="list-group-item pizza-item" data-pizza-id="${pizza.id}" data-pizza-name="${pizza.descrizione}" data-pizza-ingredients="${pizza.ingredienti}">
																<div class="form-check">
																	<input class="form-check-input pizza-checkbox" type="checkbox" name="pizzaIds" value="${pizza.id}" id="pizza_${pizza.id}"
																		${insert_ordine_attr.pizzaIds.contains(pizza.id) ? 'checked' : ''}>
																	<label class="form-check-label w-100" for="pizza_${pizza.id}">
																		<strong>${pizza.descrizione}</strong>
																		<span class="d-block text-muted small">${pizza.ingredienti}</span>
																		<span class="d-block">Prezzo: <span class="pizza-price">${pizza.prezzoBase + (pizza.prezzoBase * 20 / 100)}</span>€</span>
																	</label>
																</div>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<label class="form-label mt-3">Pizze selezionate</label>
										<ul class="list-group mb-3" id="pizzeSelezionateList">
											<li class="list-group-item text-muted" id="nessunaPizzaMsg">Nessuna pizza selezionata</li>
										</ul>
										<form:errors path="pizzaIds" cssClass="text-danger" />
									</div>
									<div class="col-12 text-end">
										<button class="btn btn-outline-danger" type="button" id="procediBtn">Procedi</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>

				<%-- MODALE CONFERMA ORDINE CON RIEPILOGO --%>
				<jsp:include page="riepilogo-modal.jsp" />

				<script src="${pageContext.request.contextPath}/assets/js/ordine-riepilogo.js"></script>

				<script>
					// Inizializzo il comportamento del riepilogo passando gli id del form corrente.
					inizializzaRiepilogoOrdine({
						formId: "ordineForm",
						procediBtnId: "procediBtn",
						confermaBtnId: "confermaOrdineBtn",
						codiceInputId: "codice",
						dataOrdineInputId: "dataOrdine",
						clienteInputId: "clienteSearchInput"
					});

					// Autocomplete cliente: mentre l'utente scrive chiedo al controller
					// i clienti compatibili e salvo l'id scelto nel campo hidden cliente.id.
					$("#clienteSearchInput").autocomplete({
						source: function(request, response) {
							$.ajax({
								url: "${pageContext.request.contextPath}/pizzeria/clienti/searchAjax",
								dataType: "json",
								data: {
									term: request.term,
								},
								success: function(data) {
									response($.map(data, function(item) {
										return {
											label: item.label,
											value: item.value,
											livelloPromo: item.livelloPromo,
										}
									}))
								}
							})
						},

						focus: function(event, ui) {
							$("#clienteSearchInput").val(ui.item.label)
							return false
						},
						minLength: 2,

						select: function( event, ui ) {
							$('#clienteId').val(ui.item.value);
							$('#clientePromo').val(ui.item.livelloPromo);
							aggiornaPromoBanner(ui.item.livelloPromo);
							return false;
						},

						change: function( event, ui ) {
							if(!$("#clienteSearchInput").val() || !ui.item ){
								$('#clienteId').val('');
								$('#clientePromo').val('');
								aggiornaPromoBanner('');
								return false;
							}

							$('#clienteId').val(ui.item.value);
							$('#clientePromo').val(ui.item.livelloPromo);
							aggiornaPromoBanner(ui.item.livelloPromo);
						}
					});

					const promoBanner = $("#promoBanner");

					function aggiornaPromoBanner(livelloPromo) {
						const livelloNormalizzato = (livelloPromo || "").toLowerCase();

						if (livelloNormalizzato === "silver") {
							promoBanner
									.removeClass("d-none alert-warning alert-dark")
									.addClass("alert alert-secondary")
									.text("Complimenti hai raggiunto il livello SILVER." +
											" \n Adesso hai accesso al 10% di sconto!");
							return;
						}

						if (livelloNormalizzato === "gold") {
							promoBanner
									.removeClass("d-none alert-secondary alert-warning")
									.addClass("alert alert-warning")
									.text("Complimenti hai raggiunto il livello GOLD." +
											" \n Adesso hai accesso al 20% di sconto!");
							return;
						}

						promoBanner
								.removeClass("alert alert-secondary alert-warning alert-dark")
								.addClass("d-none")
								.text("");
					}

					// Ricerca e filtro pizze
					const pizzaSearchInput = document.getElementById("pizzaSearchInput");
					const pizzeDisponibiliContainer = document.getElementById("pizzeDisponibiliContainer");
					const pizzeItems = Array.from(document.querySelectorAll(".pizza-item"));
					const pizzaCheckboxes = document.querySelectorAll(".pizza-checkbox");
					const pizzeSelezionateList = document.getElementById("pizzeSelezionateList");
					const nessunaPizzaMsg = document.getElementById("nessunaPizzaMsg");

					function filtraPizze() {
						const searchTerm = pizzaSearchInput.value.toLowerCase().trim();

						pizzeItems.forEach(function(item) {
							const name = item.dataset.pizzaName.toLowerCase();
							const ingredients = item.dataset.pizzaIngredients.toLowerCase();

							if (searchTerm === "" || name.includes(searchTerm) || ingredients.includes(searchTerm)) {
								item.style.display = "";
							} else {
								item.style.display = "none";
							}
						});
					}

					function aggiornaListaPizzeSelezionate() {
						const pizzeSelezionate = Array.from(pizzaCheckboxes)
							.filter(checkbox => checkbox.checked)
							.map(checkbox => {
								const label = document.querySelector("label[for='" + checkbox.id + "']");
								const descrizione = label.querySelector("strong").textContent.trim();
								const prezzoText = Array.from(label.querySelectorAll("span.pizza-price"))
									.map(span => span.textContent.trim());
								const prezzo = prezzoText[0] || "0";

								return {
									id: checkbox.value,
									descrizione: descrizione,
									prezzo: prezzo
								};
							});

						pizzeSelezionateList.innerHTML = "";

						if (pizzeSelezionate.length === 0) {
							nessunaPizzaMsg.style.display = "";
							pizzeSelezionateList.appendChild(nessunaPizzaMsg);
						} else {
							nessunaPizzaMsg.style.display = "none";
							pizzeSelezionate.forEach(function(pizza) {
								const li = document.createElement("li");
								li.className = "list-group-item d-flex justify-content-between align-items-center";
								li.innerHTML = "<span>" + pizza.descrizione + "</span><span>" + pizza.prezzo + "€</span>";
								pizzeSelezionateList.appendChild(li);
							});
						}
					}

					// Event listener per ricerca
					pizzaSearchInput.addEventListener("input", filtraPizze);

					// Event listener per checkbox
					pizzaCheckboxes.forEach(function(checkbox) {
						checkbox.addEventListener("change", aggiornaListaPizzeSelezionate);
					});

					// Inizializza la lista al caricamento
					aggiornaListaPizzeSelezionate();
				</script>
			</main>
		</body>
</html>
