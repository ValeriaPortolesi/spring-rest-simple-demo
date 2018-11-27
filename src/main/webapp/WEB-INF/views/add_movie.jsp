<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
             content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
       <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Movie Index</title>

    <%@ include file="parts/css.jsp" %>

</head>
<body>
    <div class="container">

        <div class="row">
            <div class="col">
                <h1><c:out value="${titolo}" /></h1>
            </div>
        </div>
        
        <div class="row">
            <div class="col">
                
<form action="/api/movie/" class="form-horizontal" method="POST">
  <div class="form-group">
    <label class="control-label col-xs-4" for="title">Movie Title</label> 
    <div class="col-xs-8">
      <input id="title" name="title" placeholder="Add your title here" type="text" class="form-control" required="required">
    </div>
  </div>
  <div class="form-group">
    <label for="director" class="control-label col-xs-4">Movie Director</label> 
    <div class="col-xs-8">
      <input id="director" name="director" placeholder="Add director here" type="text" class="form-control">
    </div>
  </div>
  <div class="form-group">
    <label for="year" class="control-label col-xs-4">Movie Year</label> 
    <div class="col-xs-8">
      <input id="year" name="year" placeholder="Add year here" type="text" class="form-control">
    </div>
  </div> 
  <div class="form-group row">
    <div class="col-xs-offset-4 col-xs-8">
      <button name="submit" type="button" class="btn btn-primary" id="btn-save">Save</button>
    </div>
  </div>
</form>
            </div>
        </div>


<table>
</table>

<%@ include file="parts/footer.jsp"%>
<%@ include file="parts/footerjs.jsp"%>

<script>
    const btnSave = document.getElementById("btn-save");
    btnSave.addEventListener("click",doSave)
    
    function doSave() {
        event.srcElement.disabled = true; //evito click multipli
        console.log("doSave",event);
        let titolo = document.getElementById("title").value;
        let regista = document.getElementById("director").value;
        let anno = document.getElementById("year").value;

        if(isNaN(anno) || anno<1900 || anno>2036){
            alert("Anno non valido, numero tra 1900 e 2036.");
            return false;
        }
//Le chiavi sono i calori della entity/oggetto entrante nel metodo Java
        let movie = {
            "title":titolo,
            "director":regista,
            "year": anno
        };

//FETCH Funzione javascript che vi permette di andare a recuperare risorse remote. Anche sullo stesso server. 
        fetch("/api/movie/",{
                method: 'POST',
                headers: {
                    'Accept': 'application/json',       //ciò che accetta in risposta (in formato json)
                    'Content-Type': 'application/json'  //invio (in formato json)
                },
                body: JSON.stringify(movie)             //prende l'oggetto in formato json e lo converte in formato stringa
            })
                .then(risp=>risp.json())       //prende quello che mi arriva in formato testo e lo converte in json
                .then(r=>{
                    if(r.hasOwnProperty("mid") && r.mid!=null && !isNaN(r.mid)){
                        alert("Film inserito")
                        location.href = "/site/elenco-film"
                    }
                }
)
                
                .catch(err=>console.error(err));
    }

    
</script>

</body>


</html>
