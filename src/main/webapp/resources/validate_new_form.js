function validateForm() {
    var form = document.getElementById("addForm");
    var ret = validateTitle(form) & validateDescription(form) &
        validateAuthor(form) & validateIsbn(form) & validateYear(form);

    if(ret){
        return true;
    } else {
        return false;
    }
}
