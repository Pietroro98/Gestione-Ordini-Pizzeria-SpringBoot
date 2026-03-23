<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
	<title>Modifica ordine</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Modifica ordine</h4>
				</div>
				<div class="card-body p-4">
					<spring:hasBindErrors name="edit_ordine_attr">
						<div class="alert alert-danger">Sono presenti errori di validazione</div>
					</spring:hasBindErrors>
					<form:form method="post" modelAttribute="edit_ordine_attr" action="../update" class="row g-3" id="ordineForm">
						<form:hidden path="id" />
						<form:hidden path="closed" />
						<div class="col-md-4">
							<label class="form-label" for="codice">Codice</label>
							<form:input path="codice" cssClass="form-control" id="codice" />
							<form:errors path="codice" cssClass="text-danger" />
						</div>
						<div class="col-md-4">
							<label class="form-label" for="dataOrdine">Data ordine</label>
							<input
								class="form-control"
								id="dataOrdine"
								name="dataOrdine"
								type="datetime-local"
								value="${empty edit_ordine_attr.dataOrdine ? '' : fn:substring(edit_ordine_attr.dataOrdine, 0, 16)}" />
							<form:errors path="dataOrdine" cssClass="text-danger" />
						</div>
						<div class="col-md-6">
							<label class="form-label" for="clienteSearchInput">Cliente</label>
							<input class="form-control" id="clienteSearchInput" value="${edit_ordine_attr.cliente.nome} ${edit_ordine_attr.cliente.cognome}">
							<input type="hidden" name="cliente.id" id="clienteId" value="${edit_ordine_attr.cliente.id}">
							<form:errors path="cliente" cssClass="text-danger" />
						</div>
						<div class="col-12">
							<label class="form-label">Pizze disponibili</label>
							<div class="list-group list-group-flush border rounded">
								<c:forEach items="${pizza_list_attribute}" var="pizza">
									<div class="list-group-item">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" name="pizzaIds" value="${pizza.id}" id="pizza_${pizza.id}"
												${edit_ordine_attr.pizzaIds.contains(pizza.id) ? 'checked' : ''}>
											<label class="form-check-label w-100" for="pizza_${pizza.id}">
												<strong>${pizza.descrizione}</strong>
												<span class="d-block text-muted small">${pizza.ingredienti}</span>
												<span class="d-block">Prezzo: ${pizza.prezzoBase + (pizza.prezzoBase * 20 / 100)}€</span>
											</label>
										</div>
									</div>
								</c:forEach>
							</div>
							<form:errors path="pizzaIds" cssClass="text-danger" />
						</div>
						<div class="col-12 text-end">
							<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/ordine">Torna alla lista</a>
							<button class="btn btn-outline-danger" type="button" id="procediBtn">Procedi</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

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

			select: function(event, ui) {
				$('#clienteId').val(ui.item.value);
				return false;
			},

			change: function(event, ui) {
				if (!$("#clienteSearchInput").val()) {
					$('#clienteId').val('');
					return false;
				}
			}
		});
	</script>
</main>
</body>
</html>
