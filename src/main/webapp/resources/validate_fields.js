function validateTitle(form) {
    var label = document.getElementById("titleError");
    if(form["title"].value=="" || form["title"].value.length > 100) {
        label.innerHTML = '<font color="red">Can contain from 1 to 100 symbols</font>';
        return false;
    } else {
        label.innerHTML = '';
        return true;
    }
}

function validateDescription(form) {
    var label = document.getElementById("descriptionError");
    if(form["description"].value=="" || form["description"].value.length > 255) {
        label.innerHTML = '<font color="red">Can contain from 1 to 255 symbols</font>';
        return false;
    } else {
        label.innerHTML = '';
        return true;
    }
}

function validateAuthor(form) {
    var label = document.getElementById("authorError");
    if(form["author"].value=="" || form["author"].value.length > 100) {
        label.innerHTML = '<font color="red">Can contain from 1 to 100 symbols</font>';
        return false;
    } else {
        label.innerHTML = '';
        return true;
    }
}

function validateIsbn(form) {
    var label = document.getElementById("isbnError");
    if(form["isbn"].value=="" || form["isbn"].value.length > 20) {
        label.innerHTML = '<font color="red">Can contain from 1 to 20 symbols</font>';
        return false;
    } else {
        label.innerHTML = '';
        return true;
    }
}

function validateYear(form) {
    var label = document.getElementById("yearError");
    if(form["printYear"].value=="" || form["printYear"].value < 1 ||
        form["printYear"].value > (new Date()).getFullYear()) {
        label.innerHTML = '<font color="red">Incorrect year</font>';
        return false;
    } else {
        label.innerHTML = '';
        return true;
    }
}