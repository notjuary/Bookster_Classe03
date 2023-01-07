function validateForm() {

    var name = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;

    var dateOfBirth = document.getElementById('birthday').value;

    var email = document.getElementById('email').value;

    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;

    if (name.length < 2 || name.length > 30 || lastName.length < 2 || lastName.length > 30) {
        alert('La lunghezza del campo nome e cognome deve essere compresa tra 2 e 30 caratteri');
        return false;
    }

    if (dateOfBirth === '') {
        alert('Devi selezionare una data di nascita');
        return false;
    }

    var currentYear = new Date().getFullYear();
    var birthYear = new Date(dateOfBirth).getFullYear();
    var age = currentYear - birthYear;

    if (age < 12) {
        alert('Devi avere almeno 12 anni');
        return false;
    }

    function isValidEmail(email) {
        // Utilizza una espressione regolare per verificare che l'email soddisfi il formato di un indirizzo email valido
        const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        return emailRegex.test(email);
    }

    if (!isValidEmail(email)) {
        alert('Indirizzo email non valido');
        return false;
    }

    if (password.length < 8 || password.length > 20) {
        alert('La lunghezza della password deve essere compresa tra 8 e 20 caratteri');
        return false;
    }

    function hasUppercaseLetter(password) {
        for (var i = 0; i < password.length; i++) {
            if (password[i] >= 'A' && password[i] <= 'Z') {
                return true;
            }
        }
        return false;
    }

    if (!hasUppercaseLetter(password)) {
        alert('La password deve contenere almeno una lettera maiuscola');
        return false;
    }

    function hasNumber(password) {
        for (var i = 0; i < password.length; i++) {
            if (password[i] >= '0' && password[i] <= '9') {
                return true;
            }
        }
        return false;
    }

    if (!hasNumber(password)) {
        alert('La password deve contenere almeno un numero');
        return false;
    }

    function hasSpecialCharacter(password) {
        const specialCharacters = '$!-_#*';
        for (var i = 0; i < password.length; i++) {
            if (specialCharacters.indexOf(password[i]) !== -1) {
                return true;
            }
        }
        return false;
    }

    if (!hasSpecialCharacter(password)) {
        alert('La password deve contenere almeno uno dei seguenti caratteri speciali: “$ ! - _ # *”');
        return false;
    }

    if (password !== confirmPassword) {
        alert('Le password non coincidono');
        return false;
    }

    document.getElementById("myForm").submit();
}