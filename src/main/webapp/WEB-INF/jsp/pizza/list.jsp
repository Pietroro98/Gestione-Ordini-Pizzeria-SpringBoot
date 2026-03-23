<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pizze</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container">
	<div class="d-flex justify-content-center align-items-center py-3 position-relative">
		<h2>LISTA PIZZE</h2>
		<div class="position-absolute end-0">
			<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/search">Ricerca</a>
			<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/insert">Nuova pizza</a>
		</div>
	</div>

	<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
		${successMessage}
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
	</div>

	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover align-middle shadow-sm table-light">
			<thead class="table-responsive table-borderless table-danger">
			<tr>
				<th scope="col">Descrizione</th>
				<th scope="col">Ingredienti</th>
				<th scope="col">Prezzo base</th>
				<th scope="col">Attiva</th>
				<th scope="col" class="col-actions">Azioni</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${pizza_list_attribute}" var="pizza">
				<tr>
					<td>${pizza.descrizione}</td>
					<td>${pizza.ingredienti}</td>
					<td>${pizza.prezzoBase}</td>
					<td>${pizza.attivo}</td>
								<td class="text-center align-middle text-nowrap col-actions">
						<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/pizzeria/pizze/show/${pizza.id}" aria-label="Dettaglio">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16" aria-hidden="true">
								<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13.1 13.1 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13.1 13.1 0 0 1 14.828 8q-.184.264-.39.517C13.422 9.74 11.007 12.5 8 12.5S2.578 9.74 1.562 8.517A13 13 0 0 1 1.173 8"/>
								<path d="M8 5.5A2.5 2.5 0 1 0 8 10.5A2.5 2.5 0 0 0 8 5.5M4.5 8a3.5 3.5 0 1 1 7 0a3.5 3.5 0 0 1-7 0"/>
							</svg>
						</a>
						<a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/pizzeria/pizze/edit/${pizza.id}" aria-label="Modifica">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16" aria-hidden="true">
								<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10L3 14l.146-2.854zM11.207 2 4 9.207V12h2.793L14 4.793z"/>
								<path fill-rule="evenodd" d="M1 13.5V16h2.5l7.373-7.373-2.5-2.5z"/>
							</svg>
							</a>
							<c:choose>
								<c:when test="${pizza.attivo}">
									<form method="post" action="${pageContext.request.contextPath}/pizzeria/pizze/disattiva/${pizza.id}" class="d-inline">
										<button class="btn btn-sm btn-outline-danger" type="submit">Disattiva</button>
									</form>
								</c:when>
								<c:otherwise>
									<form method="post" action="${pageContext.request.contextPath}/pizzeria/pizze/attiva/${pizza.id}" class="d-inline">
										<button class="btn btn-sm btn-outline-success" type="submit">Attiva</button>
									</form>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</main>
</body>
</html>
