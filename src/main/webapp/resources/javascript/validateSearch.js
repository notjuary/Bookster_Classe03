function validateFormSearch() {

    let selectionInput = document.getElementById('selectionInput').value;
    let searchInput = document.getElementById('searchInput').value;

    if (!["isbn", "author", "title"].includes(selectionInput)) {
        alert('Errore selection input');
        return false;
    }

    switch (selectionInput) {
        case "isbn":
            if (!/^\d{10}$|^\d{13}$/.test(searchInput)) {
                alert("Errore isbn")
                return false;
            }
            break;
        case "author":
            if (!/^[a-zA-Z\s]{1,100}$/.test(searchInput)) {
                alert("Errore autore")
                return false;
            }
            break;
        case "title":
            if (!/^[a-zA-Z\s0-9]{1,30}$/.test(searchInput)) {
                alert("Errore titolo")
                return false;
            }
            break;
        default:
            return false;
    }

    document.getElementById("myForm").submit();
}