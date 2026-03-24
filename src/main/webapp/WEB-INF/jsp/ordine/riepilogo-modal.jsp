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
					<div class="d-flex justify-content-between align-items-center">
						<strong>Totale</strong>
						<span id="riepilogoTotaleBase">0.00 €</span>
					</div>
					<div class="d-flex justify-content-between align-items-center mt-2 d-none" id="riepilogoScontoRow">
						<span id="riepilogoScontoLabel">- Sconto</span>
						<span id="riepilogoSconto">-0.00 €</span>
					</div>

					<div id="riepilogoTotaleFinaleRow" class="d-none">
						<hr class="my-2">
						<div class="d-flex justify-content-between align-items-center fw-bold">
							<span>Totale finale</span>
							<span id="riepilogoTotale">0.00 €</span>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Annulla</button>
				<button type="button" class="btn btn-primary" id="confermaOrdineBtn">Conferma ordine</button>
			</div>
		</div>
	</div>
</div>
