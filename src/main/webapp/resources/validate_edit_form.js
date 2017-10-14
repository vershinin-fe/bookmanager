function validateForm() {
    var form = document.getElementById("editForm");
    var ret = validateTitle(form) & validateDescription(form) &
        validateIsbn(form) & validateYear(form);

    if(ret){
        return true;
    } else {
        return false;
    }
}