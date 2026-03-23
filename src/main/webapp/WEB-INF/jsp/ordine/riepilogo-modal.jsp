<div class="modal fade" id="riepilogoOrdineModal" tabindex="-1" aria-labelledby="riepilogoOrdineModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="riepilogoOrdineModalLabel">Riepilogo ordine</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="mb-3">
					<div><strong>Codice:</strong> <span id="riepilogoCodice">-</span></div>
					<div><strong>Data ordine:</strong> <span id="riepilogoDataOrdine">-</span></div>
					<div><strong>Cliente:</strong> <span id="riepilogoCliente">-</span></div>
					<div><strong>Fattorino:</strong> <span> Pocu Tiempu</span></div>
				</div>
				<div class="mb-3">
					<strong>Pizze selezionate</strong>
					<ul class="list-group mt-2" id="riepilogoPizze"></ul>
				</div>
				<div class="alert alert-info border mb-0">
					<strong>Costo tot. ordine:</strong> <span id="riepilogoTotale">0.00 €</span>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Annulla</button>
				<button type="button" class="btn btn-primary" id="confermaOrdineBtn">Conferma ordine</button>
			</div>
		</div>
	</div>
</div>
