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
			<main class="container py-5">
				<div class="row justify-content-center">
					<div class="col-12 col-md-10 col-lg-8 col-xl-6">
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
									<div class="col-md-6">
										<label class="form-label" for="clienteSearchInput">Cliente</label>
										<input class="form-control" id="clienteSearchInput" value="${insert_ordine_attr.cliente.nome} ${insert_ordine_attr.cliente.cognome}">
										<input type="hidden" name="cliente.id" id="clienteId" value="${insert_ordine_attr.cliente.id}">
										<form:errors path="cliente" cssClass="text-danger" />
									</div>
									<div class="col-12">
										<label class="form-label">Pizze disponibili</label>
										<div class="list-group list-group-flush border rounded">
											<c:forEach items="${pizza_list_attribute}" var="pizza">
												<div class="list-group-item">
													<div class="form-check">
														<input class="form-check-input" type="checkbox" name="pizzaIds" value="${pizza.id}" id="pizza_${pizza.id}"
															${insert_ordine_attr.pizzaIds.contains(pizza.id) ? 'checked' : ''}>
														<label class="form-check-label w-100" for="pizza_${pizza.id}">
															<strong>${pizza.descrizione}</strong>
															<span class="d-block text-muted small">${pizza.ingredienti}</span>
															<span class="d-block">Prezzo base: ${pizza.prezzoBase}€</span>
														</label>
													</div>
												</div>
											</c:forEach>
										</div>
										<form:errors path="pizzaIds" cssClass="text-danger" />
									</div>
									<div class="col-12">
										<button class="btn btn-primary" type="button" id="procediBtn">Procedi</button>
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
											value: item.value
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
							return false;
						},

						change: function( event, ui ) {
							if(!$("#clienteSearchInput").val()){
								$('#clienteId').val('');
								return false;
							}
						}
					});
				</script>
			</main>
		</body>
</html>
