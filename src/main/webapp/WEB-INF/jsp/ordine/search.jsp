<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca ordini</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Ricerca ordini</h4>
				</div>
				<div class="card-body p-4">
					<form method="post" action="list" class="row g-3">
						<div class="col-12">
							<label class="form-label" for="codice">Codice</label>
							<input class="form-control" id="codice" name="codice">
						</div>
						<div class="col-12">
							<label class="form-label" for="dataOrdine">Data ordine</label>
							<input class="form-control" id="dataOrdine" name="dataOrdine" type="datetime-local">
						</div>
						<div class="col-12">
							<label class="form-label" for="closed">Chiuso</label>
							<select class="form-select" id="closed" name="closed">
								<option value="">Tutti</option>
								<option value="true">Solo chiusi</option>
								<option value="false">Solo aperti</option>
							</select>
						</div>
						<div class="col-12 text-end">
							<button class="btn btn-outline-danger" type="submit">Cerca</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</main>
</body>
</html>
