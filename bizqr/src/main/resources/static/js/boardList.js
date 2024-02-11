/* search bar */
$(document).ready(function(){
    $(".default_option").click(function(){
        $(".dropdown ul").addClass("active");
    });

    $(".dropdown ul li").click(function(){
        var text = $(this).text();
        $(".default_option").text(text);
        $(".dropdown ul").removeClass("active");
    });
});

/* pageNation */
window.onload = function(){
    var paginationPage = parseInt($('.cdp').attr('actpage'), 10);
    $('.cdp_i').on('click', function(){
        var go = $(this).attr('href').replace('#!', '');
        if (go === '+1') {
            paginationPage++;
        } else if (go === '-1') {
            paginationPage--;
        }else{
            paginationPage = parseInt(go, 10);
        }
        $('.cdp').attr('actpage', paginationPage);
    });
};

var detailImage = document.getElementById('detailImage');

detailImage.addEventListener('click', function() {
    var bno = detailImage.getAttribute('data-bno');

    if (bno) {
        window.location.href = '/board/detail/' + bno;
    }
});

