<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica pizza</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container">
	<spring:hasBindErrors name="edit_pizza_attr">
		<div class="alert alert-danger">Sono presenti errori di validazione</div>
	</spring:hasBindErrors>
	<form:form method="post" modelAttribute="edit_pizza_attr" action="../update" class="row g-3">
		<form:hidden path="id" />
		<form:hidden path="attivo" />
		<div class="col-md-4">
			<label class="form-label" for="descrizione">Descrizione</label>
			<form:input path="descrizione" cssClass="form-control" id="descrizione" />
			<form:errors path="descrizione" cssClass="text-danger" />
		</div>
		<div class="col-md-4">
			<label class="form-label" for="ingredienti">Ingredienti</label>
			<form:input path="ingredienti" cssClass="form-control" id="ingredienti" />
			<form:errors path="ingredienti" cssClass="text-danger" />
		</div>
		<div class="col-md-4">
			<label class="form-label" for="prezzoBase">Prezzo base</label>
			<form:input path="prezzoBase" cssClass="form-control" id="prezzoBase" type="number" step="0.01" />
			<form:errors path="prezzoBase" cssClass="text-danger" />
		</div>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">Aggiorna</button>
		</div>
	</form:form>
</main>
</body>
</html>
