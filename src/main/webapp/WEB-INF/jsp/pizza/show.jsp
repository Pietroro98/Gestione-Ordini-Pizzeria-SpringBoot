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
			<div class="card shadow-sm border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Dettaglio pizza</h4>
				</div>
				<div class="card-body p-4">
					<p><strong>ID:</strong> ${show_pizza_attr.id}</p>
					<p><strong>Descrizione:</strong> ${show_pizza_attr.descrizione}</p>
					<p><strong>Ingredienti:</strong> ${show_pizza_attr.ingredienti}</p>
					<p><strong>Prezzo base:</strong> ${show_pizza_attr.prezzoBase}</p>
					<p><strong>Attiva:</strong> ${show_pizza_attr.attivo}</p>
					<div class="text-end">
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze">Torna alla lista</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
</body>
</html>
