<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Dettaglio ordine</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Dettaglio ordine</h4>
				</div>
				<div class="card-body p-4">
					<p><strong>ID:</strong> ${show_ordine_attr.id}</p>
					<p><strong>Codice:</strong> ${show_ordine_attr.codice}</p>
					<p><strong>Data ordine:</strong> ${show_ordine_attr.dataOrdine}</p>
					<p><strong>Cliente:</strong> ${show_ordine_attr.cliente.nome} ${show_ordine_attr.cliente.cognome}</p>
					<p><strong>Fattorino:</strong> Pocu Tiempu</p>
					<p><strong>Totale:</strong> ${show_ordine_attr.costoTotale} €</p>
					<p><strong>Chiuso:</strong> ${show_ordine_attr.closed}</p>
					<p><strong>Pizze:</strong></p>
					<ul>
						<c:forEach items="${show_ordine_attr.pizze}" var="pizza">
							<li>${pizza.descrizione}</li>
						</c:forEach>
					</ul>
					<div class="text-end">
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/ordine">Torna alla lista</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
</body>
</html>
