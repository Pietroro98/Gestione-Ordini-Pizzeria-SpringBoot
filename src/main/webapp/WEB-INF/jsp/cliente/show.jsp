<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Dettaglio pizza</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Dettaglio Cliente</h4>
				</div>
				<div class="card-body p-4">
					<p><strong>ID:</strong> ${show_cliente_attr.id}</p>
					<p><strong>Nome:</strong> ${show_cliente_attr.nome}</p>
					<p><strong>Cognome:</strong> ${show_cliente_attr.cognome}</p>
					<p><strong>Indirizzo:</strong> ${show_cliente_attr.indirizzo}</p>
					<p><strong>Attivo:</strong> ${show_cliente_attr.attivo}</p>
					<div class="text-end">
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/clienti">Torna alla lista</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
</body>
</html>
