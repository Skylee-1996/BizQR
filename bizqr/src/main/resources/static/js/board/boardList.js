/* search bar */
$(document).ready(function () {
    $(".default_option").click(function () {
        $(".dropdown ul").addClass("active");
    });

    $(".dropdown ul li").click(function () {
        var text = $(this).text();
        $(".default_option").text(text);
        $(".dropdown ul").removeClass("active");
    });
});

/* pageNation */
window.onload = function () {
    var paginationPage = parseInt($('.cdp').attr('actpage'), 10);
    $('.cdp_i').on('click', function () {
        var go = $(this).attr('href').replace('#!', '');
        if (go === '+1') {
            paginationPage++;
        } else if (go === '-1') {
            paginationPage--;
        } else {
            paginationPage = parseInt(go, 10);
        }
        $('.cdp').attr('actpage', paginationPage);
    });
};

var detailImage = document.getElementById('detailImage');

detailImage.addEventListener('click', function () {
    var bno = detailImage.getAttribute('data-bno');

    if (bno) {
        window.location.href = '/board/detail/' + bno;
    }
});

/* head */
function changeColorToHead() {
    let selectBox = document.getElementById("Brackets");
    let selectedValue = selectBox.options[selectBox.selectedIndex].value;
    let tagElement = document.querySelector(".tag");

    tagElement.className = "tag";

    switch (selectedValue) {
        case "head1":
            tagElement.classList.add("tag-teal");
            tagElement.textContent = "말머리1";
            break;
        case "head2":
            tagElement.classList.add("tag-purple");
            tagElement.textContent = "말머리2";
            break;
        case "head3":
            tagElement.classList.add("tag-green");
            tagElement.textContent = "말머리3";
            break;
        case "head4":
            tagElement.classList.add("tag-yellow");
            tagElement.textContent = "말머리4";
            break;
        case "head5":
            tagElement.classList.add("tag-blue");
            tagElement.textContent = "말머리5";
            break;
        default:
            tagElement.textContent = "";
            break;
    }
}



