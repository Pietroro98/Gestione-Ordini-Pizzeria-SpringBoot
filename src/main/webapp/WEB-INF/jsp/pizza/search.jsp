<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca pizze</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container">
	<div class="card">
		<div class="card-header">Ricerca pizze</div>
		<div class="card-body">
			<form method="post" action="list" class="row g-3">
				<div class="col-md-4">
					<label class="form-label" for="descrizione">Descrizione</label>
					<input class="form-control" id="descrizione" name="descrizione">
				</div>
				<div class="col-md-4">
					<label class="form-label" for="ingredienti">Ingredienti</label>
					<input class="form-control" id="ingredienti" name="ingredienti">
				</div>
				<div class="col-md-4">
					<label class="form-label" for="prezzoBase">Prezzo base</label>
					<input class="form-control" id="prezzoBase" name="prezzoBase" type="number" step="0.01">
				</div>
				<div class="col-md-4">
					<label class="form-label" for="attivo">Attiva</label>
					<select class="form-select" id="attivo" name="attivo">
						<option value="">Tutte</option>
						<option value="true">Solo attive</option>
						<option value="false">Solo disattive</option>
					</select>
				</div>
				<div class="col-12">
					<button class="btn btn-outline-danger" type="submit">Cerca</button>
				</div>
			</form>
		</div>
	</div>
</main>
</body>
</html>
