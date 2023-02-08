function validateForm() {

    let name = document.getElementById('firstName').value;
    let lastName = document.getElementById('lastName').value;

    let dateOfBirth = document.getElementById('birthday').value;

    let email = document.getElementById('email').value;

    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;

    let phone = document.getElementById('phone').value;

    let username = document.getElementById('username').value;

    let currentYear = new Date().getFullYear();
    let birthYear = new Date(dateOfBirth).getFullYear();
    let age = currentYear - birthYear;

    if (name.length < 2 || name.length > 30 || lastName.length < 2 || lastName.length > 30) {
        alert('La lunghezza del campo nome e cognome deve essere compresa tra 2 e 30 caratteri');
        return false;
    }

    if (dateOfBirth === '') {
        alert('Devi selezionare una data di nascita');
        return false;
    }

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

    function validatePhoneNumber(phoneNumber) {
        const phoneRegex = /^\d{1,20}$/;
        return phoneRegex.test(phoneNumber);
    }

    if (!validatePhoneNumber(phone)) {
        alert('Numero di telefono non valido');
        return false;
    }

    function validateUsername(userName) {
        const usernameRegex = /^[a-zA-Z0-9]{1,20}$/;
        return usernameRegex.test(userName);
    }

    if (!validateUsername(username)) {
        alert('Username non valido');
        return false;
    }

    function hasNumber(password) {
        for (let i = 0; i < password.length; i++) {
            if (password[i] >= '0' && password[i] <= '9') {
                return true;
            }
        }
        return false;
    }

    if (password.length < 8 || password.length > 20) {
        alert('La lunghezza della password deve essere compresa tra 8 e 20 caratteri');
        return false;
    }

    function hasUppercaseLetter(password) {
        for (let i = 0; i < password.length; i++) {
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
zz
    if (!hasNumber(password)) {
        alert('La password deve contenere almeno un numero');
        return false;
    }

    function hasSpecialCharacter(password) {
        const specialCharacters = '$!-_#*';
        for (let i = 0; i < password.length; i++) {
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