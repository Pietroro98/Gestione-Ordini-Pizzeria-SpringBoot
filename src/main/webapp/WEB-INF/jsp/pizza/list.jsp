<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pizze</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="d-flex justify-content-between align-items-center py-5">
		<h2>LISTA PIZZE</h2>
		<div>
			<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/search">Ricerca</a>
			<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/insert">Nuova pizza</a>
		</div>
	</div>
	<div class="alert alert-success ${successMessage == null ? 'd-none' : ''}">${successMessage}</div>
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover align-middle shadow-sm">
			<thead class="table-responsive table-borderless table-danger">
			<tr>
				<th scope="col">Descrizione</th>
				<th scope="col">Ingredienti</th>
				<th scope="col">Prezzo base</th>
				<th scope="col">Attiva</th>
				<th scope="col">Azioni</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${pizza_list_attribute}" var="pizza">
				<tr>
					<td>${pizza.descrizione}</td>
					<td>${pizza.ingredienti}</td>
					<td>${pizza.prezzoBase}</td>
					<td>${pizza.attivo}</td>
					<td class="text-end">
						<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/pizzeria/pizze/show/${pizza.id}">Dettaglio</a>
						<a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/pizzeria/pizze/edit/${pizza.id}">Modifica</a>
						<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/delete/${pizza.id}">Disattiva</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>
