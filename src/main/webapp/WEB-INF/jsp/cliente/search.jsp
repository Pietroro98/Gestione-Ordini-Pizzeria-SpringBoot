<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca Clienti</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />
<main class="container py-5">
	<div class="row justify-content-center">
		<div class="col-12 col-md-10 col-lg-8 col-xl-6">
			<div class="card shadow-lg p-3 mb-5 bg-white rounded border-0">
				<div class="card-header bg-danger text-white">
					<h4 class="mb-0">Ricerca Clienti</h4>
				</div>
				<div class="card-body p-4">
					<form method="post" action="list" class="row g-3">
						<div class="col-12">
							<label class="form-label" for="nome">Nome</label>
							<input class="form-control" id="nome" name="nome">
						</div>
						<div class="col-12">
							<label class="form-label" for="cognome">Cognome</label>
							<input class="form-control" id="cognome" name="cognome">
						</div>
						<div class="col-12">
							<label class="form-label" for="indirizzo">Indirizzo</label>
							<input class="form-control" id="indirizzo" name="indirizzo">
						</div>
						<div class="col-12">
							<label class="form-label" for="attivo">Attivo</label>
							<select class="form-select" id="attivo" name="attivo">
								<option value="">Tutti</option>
								<option value="true">Solo attivi</option>
								<option value="false">Solo disattivi</option>
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
