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
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Modifica Cliente</h4>
				</div>
				<div class="card-body p-4">
					<spring:hasBindErrors name="edit_cliente_attr">
						<div class="alert alert-danger">Sono presenti errori di validazione</div>
					</spring:hasBindErrors>
					<form:form method="post" modelAttribute="edit_cliente_attr" action="../update" class="row g-3">
						<form:hidden path="id" />
						<form:hidden path="attivo" />
						<div class="col-12">
							<label class="form-label" for="nome">Nome</label>
							<form:input path="nome" cssClass="form-control" id="nome" />
							<form:errors path="nome" cssClass="text-danger" />
						</div>
						<div class="col-12">
							<label class="form-label" for="cognome">Cognome</label>
							<form:input path="cognome" cssClass="form-control" id="cognome" />
							<form:errors path="cognome" cssClass="text-danger" />
						</div>
						<div class="col-12">
							<label class="form-label" for="indirizzo">Indirizzo</label>
							<form:input path="indirizzo" cssClass="form-control" id="indirizzo" />
							<form:errors path="indirizzo" cssClass="text-danger" />
						</div>
						<div class="col-12 text-end">
							<button class="btn btn-outline-danger" type="submit">Aggiorna</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</main>
</body>
</html>
