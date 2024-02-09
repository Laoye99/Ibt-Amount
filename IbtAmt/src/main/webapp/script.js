var lowerBound = document.getElementById("lower_bound");
var upperBound = document.getElementById("upper_bound");

function validateFigure() {
    var lowerBound = document.getElementById("lower_bound").value;
    var upperBound = document.getElementById("upper_bound").value;
    if(upperBound <= lowerBound){
        alert("Upper Bound Value is Invalid")
    }
}