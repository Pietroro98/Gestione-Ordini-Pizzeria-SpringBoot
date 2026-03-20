<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
		<!-- Custom styles per le features di bootstrap 'Columns with icons' -->
	   <link href="${pageContext.request.contextPath}/assets/css/features.css" rel="stylesheet">
	   <style>
	   	.home-carousel {

	   		margin: 0 auto 2rem;
	   		border: 6px solid #ffffff;
	   		border-radius: 18px;
	   		overflow: hidden;
	   		box-shadow: 0 10px 24px rgba(0, 0, 0, 0.18);
	   	}

	   	.home-carousel .carousel-item img {
	   		height: 400px;
	   		object-fit: cover;
	   	}
	   </style>
	   
	   <title>🍕Pizzeria Sicilia 🍕</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   		
	   		<!-- #####################################  -->
	   		<!-- elementi grafici per le features in basso  -->
	   		<!-- #####################################  -->
	   		<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
			  <symbol id="people-circle" viewBox="0 0 16 16">
			    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
			    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			  </symbol>
			  <symbol id="collection" viewBox="0 0 16 16">
			    <path d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-11zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1h-7zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6v7zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13z"/>
			  </symbol>
			  <symbol id="toggles2" viewBox="0 0 16 16">
			    <path d="M9.465 10H12a2 2 0 1 1 0 4H9.465c.34-.588.535-1.271.535-2 0-.729-.195-1.412-.535-2z"/>
			    <path d="M6 15a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm0 1a4 4 0 1 1 0-8 4 4 0 0 1 0 8zm.535-10a3.975 3.975 0 0 1-.409-1H4a1 1 0 0 1 0-2h2.126c.091-.355.23-.69.41-1H4a2 2 0 1 0 0 4h2.535z"/>
			    <path d="M14 4a4 4 0 1 1-8 0 4 4 0 0 1 8 0z"/>
			  </symbol>
			  <symbol id="chevron-right" viewBox="0 0 16 16">
			    <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
			  </symbol>
			</svg>
			<!-- ############## end ###################  -->
	   
	   
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="container">
				<div class="p-5 mb-4 bg-light rounded-3">
					<h1 class="display-6">Gestione Pizzeria 🍕</h1>
					<p class="lead">Gestionale pizzeria moderno con soft delete su clienti, pizze attive e ordini con totale calcolato lato service.</p>
					<div class="d-flex gap-2">
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/clienti/search">Gestione Clienti</a>
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/pizze/search">Gestione Pizze</a>
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/ordini/search">Gestione Ordini</a>
						<a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/pizzeria/statistiche/search">Statistiche varie</a>
					</div>
				</div>

				<div id="carouselExampleSlidesOnly" class="carousel slide home-carousel" data-bs-ride="carousel" data-bs-interval="4000">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="https://www.cibotoday.it/~media/horizontal-mid/50662807504151/la-pizza-in-teglia-di-gabriele-bonci-2.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="https://associazionepizzerieitaliane.it/wp-content/uploads/2021/03/foto5.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="https://scuolaartebianca.it/wp-content/uploads/2019/07/corso-pizza-in-teglia-alla-romana-1024x683.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="https://foodtourrome.com/wp-content/uploads/2021/02/PIZZARIUM-ROME-BEST-PIZZA-AL-TAGLIO-e1614078716874-1.jpg" class="d-block w-100" alt="...">
						</div>
					</div>
				</div>
			</main>

			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>
