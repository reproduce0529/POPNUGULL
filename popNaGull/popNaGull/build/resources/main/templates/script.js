let Time;
Time = setTimeInterval

function upper(){
    let detail_cnt_box = document.querySelector(".detail_cnt_box");
    detail_cnt_box.style.transform = "translateX(-50%) translateY(-320px)"
    document.querySelector(".upper_button").innerHTML = "<i class='fa-solid fa-chevron-down'></i>";
    document.querySelector(".upper_button").setAttribute("onclick", "downer()")
}

function downer() {
    let detail_cnt_box = document.querySelector(".detail_cnt_box");
    detail_cnt_box.style.transform = "translateX(-50%) translateY(-50px)"
    document.querySelector(".upper_button").innerHTML = "<i class='fa-solid fa-chevron-up'></i>";
    document.querySelector(".upper_button").setAttribute("onclick", "upper()")
}

function changeImage(){
    
    document.querySelector(".AllCnt").innerHTML = parseInt(document.querySelector(".AllCnt").innerHTML) + 1;
    document.querySelector(".thisisCat").setAttribute("src", "./open.png");
}
function changeImageRever(){
    document.querySelector(".thisisCat").setAttribute("src", "./close.png");
}
