function inizializzaRiepilogoOrdine(config)
{
    const ordineForm = document.getElementById(config.formId);
    const procediBtn = document.getElementById(config.procediBtnId);
    const confermaOrdineBtn = document.getElementById(config.confermaBtnId);
    const codiceInput = document.getElementById(config.codiceInputId);
    const dataOrdineInput = document.getElementById(config.dataOrdineInputId);
    const clienteInput = document.getElementById(config.clienteInputId);
    const clienteIdInput = document.getElementById("clienteId");
    const clientePromoInput = document.getElementById("clientePromo");
    const riepilogoPizze = document.getElementById("riepilogoPizze");
    const riepilogoCodice = document.getElementById("riepilogoCodice");
    const riepilogoDataOrdine = document.getElementById("riepilogoDataOrdine");
    const riepilogoCliente = document.getElementById("riepilogoCliente");
    const riepilogoTotaleBase = document.getElementById("riepilogoTotaleBase");
    const riepilogoScontoRow = document.getElementById("riepilogoScontoRow");
    const riepilogoTotaleFinaleRow = document.getElementById("riepilogoTotaleFinaleRow");
    const riepilogoScontoLabel = document.getElementById("riepilogoScontoLabel");
    const riepilogoSconto = document.getElementById("riepilogoSconto");
    const riepilogoTotale = document.getElementById("riepilogoTotale");

    const riepilogoOrdineModal = new bootstrap.Modal(document.getElementById("riepilogoOrdineModal"));

    function formatEuro(value) {
        return value.toLocaleString("it-IT", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }) + " €";
    }

    function formatDataOrdine(value) {
        if (!value) {
            return "-";
        }
        return value.replace("T", " ");
    }

    function getPercentualeSconto() {
        const livelloPromo = clientePromoInput ? (clientePromoInput.value || "").trim().toLowerCase() : "";

        if (livelloPromo === "gold") {
            return 20;
        }

        if (livelloPromo === "silver") {
            return 10;
        }

        return 0;
    }

    function estraiPizzeSelezionate() {
        return Array.from(document.querySelectorAll("input[name='pizzaIds']:checked")).map(function(checkbox) {
            const label = document.querySelector("label[for='" + checkbox.id + "']");

            const descrizione = label.querySelector("strong") ? label.querySelector("strong").textContent.trim() : "Pizza";

            const prezzoText = Array.from(label.querySelectorAll("span")).map(function(span) {
                return span.textContent.trim();
            }).find(function(el) {
                return el.indexOf("Prezzo:") === 0;
            }) || "Prezzo: 0€";

            const prezzo  = parseFloat(prezzoText.replace("Prezzo:","").replace("€","").replace(",", ".").trim()) || 0;

            return {
                descrizione: descrizione,
                prezzo: prezzo
            };
        });
    }

    function isOrdineCompilato() {
        return codiceInput.value.trim() !== ""
            && dataOrdineInput.value.trim() !== ""
            && clienteInput.value.trim() !== ""
            && clienteIdInput.value.trim() !== ""
            && estraiPizzeSelezionate().length > 0;
    }

    function aggiornaStatoProcedi() {
        procediBtn.disabled = !isOrdineCompilato();
    }

    function aggiornaRiepilogoOrdine() {
        const selectedPizze = estraiPizzeSelezionate();
        let totaleBase = 0;

        riepilogoCodice.textContent = codiceInput.value || "-";
        riepilogoDataOrdine.textContent = formatDataOrdine(dataOrdineInput.value);
        riepilogoCliente.textContent = clienteInput.value || "-";
        riepilogoPizze.innerHTML = "";

        selectedPizze.forEach(function(pizza) {
            totaleBase += pizza.prezzo;

            const item = document.createElement("li");
            item.className = "list-group-item d-flex justify-content-between align-items-center";
            item.innerHTML = "<span>" + pizza.descrizione + "</span><span>" + formatEuro(pizza.prezzo) + "</span>";
            riepilogoPizze.appendChild(item);
        });

        if (selectedPizze.length === 0) {
            const emptyItem = document.createElement("li");
            emptyItem.className = "list-group-item text-muted";
            emptyItem.textContent = "Nessuna pizza selezionata";
            riepilogoPizze.appendChild(emptyItem);
        }

        const percentualeSconto = getPercentualeSconto();
        const importoSconto = totaleBase * percentualeSconto / 100;
        const totaleFinale = totaleBase - importoSconto;

        riepilogoTotaleBase.textContent = formatEuro(totaleBase);
        riepilogoTotale.textContent = formatEuro(totaleFinale);

        if (percentualeSconto > 0) {
            const livelloPromo = clientePromoInput ? (clientePromoInput.value || "").toUpperCase() : "";
            riepilogoScontoLabel.textContent = "- Sconto " + livelloPromo;
            riepilogoSconto.textContent = "-" + formatEuro(importoSconto);
            riepilogoScontoRow.classList.remove("d-none");
            riepilogoTotaleFinaleRow.classList.remove("d-none");
        } else {
            riepilogoScontoLabel.textContent = "- Sconto";
            riepilogoSconto.textContent = formatEuro(0);
            riepilogoScontoRow.classList.add("d-none");
            riepilogoTotaleFinaleRow.classList.add("d-none");
        }
    }

    // Prima di aprire il modale, se manca un campo richiesto
    // il modale non si apre e mando errore.
    procediBtn.addEventListener("click", function() {
        if (!isOrdineCompilato() || !ordineForm.reportValidity()) {
            return;
        }

     //riepilogo
        aggiornaRiepilogoOrdine();
        riepilogoOrdineModal.show();
    });

    // Il submit vero parte solo dopo la conferma finale nel modale.
    confermaOrdineBtn.addEventListener("click", function() {
        ordineForm.submit();
    });

    ordineForm.addEventListener("input", aggiornaStatoProcedi);
    ordineForm.addEventListener("change", aggiornaStatoProcedi);
    aggiornaStatoProcedi();
}
