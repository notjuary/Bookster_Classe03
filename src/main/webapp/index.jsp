<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it-IT">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href="resources/img/Book.svg" type="image/x-icon">
    <title>Bookster</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/index.css">

    <script>
        let indice = 1;
        mostraSlide(indice);

        function cambiaFoto(n){
            mostraSlide(indice += n);
        }

        function slideCorrente(n){
            mostraSlide(indice = n);
        }

        function mostraSlide(n){
            console.log(indice);
            let i;
            let slides = document.getElementsByClassName("slide");
            let dots = document.getElementsByClassName("dot");

            if(n > slides.length){
                indice = 1; //vai all'inizio
            }

            if(n < 1){
                indice = slides.length; //vai alla fine
            }

            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }

            for(i=0; i < dots.length; i++){
                dots[i].className = dots[i].className.replace(" activePhoto", "")
            }

            slides[indice-1].style.display = "block";
            dots[indice-1].className += " activePhoto";
        };
    </script>
</head>
<body onload="slideCorrente(1)">
    <%@ include file="header.jsp"%>

    <main>
        <div class="advertise">
            <div class="containerFlexDescription">
                <div class="advInfo">
                    <div class="titleAdv">
                        <h1>La trilogia del silo</h1>
                    </div>
                    <div class="descriptionBox">
                        <p>
                            <b>Fuori, l'aria è letale. <br> Dentro le regole uccidono.</b><br>
                            La Trilogia del Silo è una serie di 9 romanzi di fantascienza dello scrittore statunitense Hugh Howey,
                            raccolti successivamente in tre libri: Wool, Shift e Dust.
                        </p>
                    </div>
                </div>
            </div>

            <div class="containerFlexGallery">
                <div class="gallerySection">
                    <div class="buttonPrev">
                        <div id="prev" onclick="cambiaFoto(-1)">&#10094;</div>
                    </div>

                    <div class="containerGalleria">
                        <div class="slide transition">
                            <img src="resources/img/adv/wool.jpg" alt="Wool" style="width:100%">
                        </div>
                        <div class="slide transition">
                            <img src="resources/img/adv/shift.jpg" alt="Shift" style="width:100%">
                        </div>
                        <div class="slide transition">
                            <img src="resources/img/adv/dust.jpg" alt="Dust" style="width:100%">
                        </div>
                    </div>

                    <div class="buttonNext">
                        <div id="next" onclick="cambiaFoto(1)">&#10095;</div>
                    </div>

                    <div class="dotsView">
                        <span class="dot" onclick="slideCorrente(1)"></span>
                        <span class="dot" onclick="slideCorrente(2)"></span>
                        <span class="dot" onclick="slideCorrente(3)"></span>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp"%>
</body>
</html>

