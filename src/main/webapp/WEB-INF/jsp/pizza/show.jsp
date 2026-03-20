<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Dettaglio pizza</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container">
	<div class="card">
		<div class="card-header">Dettaglio pizza</div>
		<div class="card-body">
			<p><strong>ID:</strong> ${show_pizza_attr.id}</p>
			<p><strong>Descrizione:</strong> ${show_pizza_attr.descrizione}</p>
			<p><strong>Ingredienti:</strong> ${show_pizza_attr.ingredienti}</p>
			<p><strong>Prezzo base:</strong> ${show_pizza_attr.prezzoBase}</p>
			<p><strong>Attiva:</strong> ${show_pizza_attr.attivo}</p>
		</div>
	</div>
</main>
</body>
</html>
