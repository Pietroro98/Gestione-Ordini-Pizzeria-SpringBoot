function inizializzaRiepilogoOrdine(config)
{
    const ordineForm = document.getElementById(config.formId);
    const procediBtn = document.getElementById(config.procediBtnId);
    const confermaOrdineBtn = document.getElementById(config.confermaBtnId);
    const riepilogoPizze = document.getElementById("riepilogoPizze");
    const riepilogoCodice = document.getElementById("riepilogoCodice");
    const riepilogoDataOrdine = document.getElementById("riepilogoDataOrdine");
    const riepilogoCliente = document.getElementById("riepilogoCliente");
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

    function estraiPizzeSelezionate() {
        return Array.from(document.querySelectorAll("input[name='pizzaIds']:checked")).map(function(checkbox) {
            const label = document.querySelector("label[for='" + checkbox.id + "']");

            const descrizione = label.querySelector("strong") ? label.querySelector("strong").textContent.trim() : "Pizza";

            const prezzoText = Array.from(label.querySelectorAll("span")).map(function(span) {
                return span.textContent.trim();
            }).find(function(el) {
                return el.indexOf("Prezzo base:") === 0;
            }) || "Prezzo base: 0€";

            const prezzo  = parseFloat(prezzoText.replace("Prezzo base:","").replace("€","").replace(",", ".").trim()) || 0;

            return {
                descrizione: descrizione,
                prezzo: prezzo
            };
        });
    }

    function aggiornaRiepilogoOrdine() {
        const selectedPizze = estraiPizzeSelezionate();
        let totale = 0;

        riepilogoCodice.textContent = document.getElementById(config.codiceInputId).value || "-";
        riepilogoDataOrdine.textContent = formatDataOrdine(document.getElementById(config.dataOrdineInputId).value);
        riepilogoCliente.textContent = document.getElementById(config.clienteInputId).value || "-";
        riepilogoPizze.innerHTML = "";

        selectedPizze.forEach(function(pizza) {
            totale += pizza.prezzo;

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

        riepilogoTotale.textContent = formatEuro(totale);
    }

    // Prima di aprire il modale, se manca un campo richiesto
    // il modale non si apre e mando errore.
    procediBtn.addEventListener("click", function() {
        if (!ordineForm.reportValidity()) {
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
}
