<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Liste des tournois</title>
  </head>
  <body>
<%@ include file="menu.jsp" %>
<%@ include file="bienvenue.jsp" %>
<main role="main" class="container">
<form class="form-inline my-2 my-lg-0" action="Listtournoi" method="post">
      <input class="form-control mr-sm-2" type="text" name="txttournoi" placeholder="Search" aria-label="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action" value="Rechercher">Rechercher</button>
    </form>
  <div class="starter-template">
    <h1>Liste des tournois</h1>
    <p class="lead">Bienvenue .... Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor.</p>
  </div>
</main><!-- /.container -->
<div class="container">
<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
<a class="btn btn-primary" href="/Joueur/AjouterTournoi" role="button">Ajouter un tournoi</a> 
</div>
<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:15%">#</th>
      <th scope="col" style="width:35%">Nom</th>
      <th scope="col" style="width:35%">Code</th>

    </tr>
  </thead>
  <tbody>
  <c:if test="${tournois.size()==0}">
  	<td colspan="5" style="text-align:center; font-size:28px;">Aucune occurence trouv&eacute;e</td>
  </c:if>
  <c:if test="${tournois.size() !=0}">
  <c:forEach var="tournoi" items="${tournois}">
  <tr>
  	
  	<td><c:out value="${tournoi.id }" /></td>
  	<td><c:out value="${tournoi.nom }" /></td>
  	<td><c:out value="${tournoi.code }" /></td>

  	<td>
	    <a type="submit" class="btn btn-outline-primary" href="/Joueur/ModifierTournoi?id=${ tournoi.id }" role="button"  >Modifier</a>
		<a type="button" class="btn btn-outline-warning" href="/Joueur/SupprimerTournoi?id=${ tournoi.id }" role="button" >Supprimer</a>
  	</td>
	</tr>
   </c:forEach>
   </c:if>
  </tbody>
</table>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>