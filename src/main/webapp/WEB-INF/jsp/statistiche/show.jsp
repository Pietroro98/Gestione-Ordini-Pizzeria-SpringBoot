<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
    <jsp:include page="../header.jsp" />
    <title>Statistiche varie</title>
</head>
<body>
<jsp:include page="../navbar.jsp" />

<main class="container py-5">
    <div class="row justify-content-center">
        <div class="col-12 col-lg-10">
            <div class="card shadow-lg border-0">
                <div class="card-header bg-danger text-white">
                    <h4 class="mb-0">Statistiche varie</h4>
                </div>

                <div class="card-body p-4">
                    <form method="post" action="${pageContext.request.contextPath}/pizzeria/statistiche/report" class="row g-3">
                        <div class="col-md-5">
                            <label class="form-label" for="dataDa">Data da</label>
                            <input class="form-control" type="date" id="dataDa" name="dataDa" value="${dataDa}">
                        </div>

                        <div class="col-md-5">
                            <label class="form-label" for="dataA">Data a</label>
                            <input class="form-control" type="date" id="dataA" name="dataA" value="${dataA}">
                        </div>

                        <div class="col-md-2 d-flex align-items-end">
                            <button class="btn btn-outline-danger w-100" type="submit">Cerca</button>
                        </div>
                    </form>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger mt-4 mb-0">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <c:if test="${ricaviTotali ne null}">
                        <hr class="my-4">

                        <h5 class="mb-3">Riepilogo dati</h5>

                        <div class="row g-3">
                            <div class="col-md-6">
                                <div class="border rounded p-3 h-100">
                                    <strong>Ricavi totali nell'intervallo selezionato:</strong>
                                    <div class="fs-5">${ricaviTotali} €</div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="border rounded p-3 h-100">
                                    <strong>Costi totali nell'intervallo selezionato:</strong>
                                    <div class="fs-5">${costiTotali} €</div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="border rounded p-3 h-100">
                                    <strong>Numero ordini totali nell'intervallo selezionato:</strong>
                                    <div class="fs-5">${numeroOrdiniTotali}</div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="border rounded p-3 h-100">
                                    <strong>Numero pizze totali ordinate nell'intervallo selezionato:</strong>
                                    <div class="fs-5">${numeroPizzeTotali}</div>
                                </div>
                            </div>
                        </div>

                        <div class="mt-4">
                            <h5 class="mb-3">Clienti virtuosi</h5>

                            <c:choose>
                                <c:when test="${not empty clientiVirtuosi}">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover align-middle">
                                            <thead class="table-danger">
                                            <tr>
                                                <th>Nome</th>
                                                <th>Cognome</th>
                                                <th>Indirizzo</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${clientiVirtuosi}" var="clienteItem">
                                                <tr>
                                                    <td>${clienteItem.nome}</td>
                                                    <td>${clienteItem.cognome}</td>
                                                    <td>${clienteItem.indirizzo}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-info mb-0">
                                        Nessun cliente virtuoso trovato nell'intervallo selezionato.
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>