function validateForm() {
    let email = document.getElementById("email").value;
    let resume = document.getElementById("resume").value;
    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    
    if (!email.match(emailPattern)) {
        alert("Invalid email");
        return false;
    }
    
    if (!resume.match(/\.(pdf|doc|docx)$/i)) {
        alert("Only PDF/DOC/DOCX allowed");
        return false;
    }
    
    alert("Registration successful!");
    return true;
}
