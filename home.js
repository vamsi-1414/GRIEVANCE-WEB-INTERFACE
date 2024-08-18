let flag=0;
function display(){
    let b1=document.getElementById("menuhidden");
    
    if(!flag){
        flag=1;
    b1.style.display="flex";
}
    else{
        flag=0;
        b1.style.display="none";
    }
}

function issue(){
    document.getElementById("reports").style.display="none";
    document.getElementById("issuereport").style.display="flex";    
}

function clicked( value){
     document.getElementById("issuereport").style.display="none";    
    document.getElementById("sectorpass").value=value;
    document.getElementById("issued").style.display="block";
    document.getElementById("iss").style.display="block";
   
}




