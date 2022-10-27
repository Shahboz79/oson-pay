window.onload = function () {
    if ($(window).width() < 769) {
        $('.wrapper').removeClass('toggled');
    }

    $('.select2').select2();

    $.get('/admin/suspect_transaction/list/count', function (data) {
        $('#suspect_trn_count').text(data);
    });

    resetActiveSidebar($('#active_sidebar_number').val());
}

//toggle sidebar
$('#toggle-sidebar').click(function () {
    $('.wrapper').toggleClass('toggled');
});

$('.pagination li:not(.active) a').click(function (event) {
    $('#input_page').val(event.currentTarget.innerText);
    $('#form_filter').submit();
});

$('.btn-reset').click(function () {
    var form = $(this).parents('form');
    form.find('input').val("");
    form.find('select').prop('selectedIndex', 0);
})

$('.content').click(function () {
    if ($(window).width() < 769 && $('.wrapper').hasClass('toggled')) {
        $('.wrapper').removeClass('toggled');
    }
})

$('form#form_filter button[type="submit"]').click(function(e){
    e.preventDefault();
    var filterForm = $(this).parents('form');
    filterForm.find('input[name="page"]').val(1);
    filterForm.submit();
})

function resetActiveSidebar(activeSidebar) {
    switch (activeSidebar) {
        case '1':
            $('#first_li').addClass('active');
            $('#first_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '2':
            $('#second_li').addClass('active');
            $('#second_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '3':
            $('#third_li').addClass('active');
            $('#third_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '4':
            $('#fourth_li').addClass('active');
            $('#fourth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '5':
            $('#fifth_li').addClass('active');
            $('#fifth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '6':
            $('#sixth_li').addClass('active');
            $('#sixth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '7':
            $('#seventh_li').addClass('active');
            $('#seventh_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '8':
            $('#eighth_li').addClass('active');
            $('#eighth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '9':
            $('#ninth_li').addClass('active');
            $('#ninth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        case '10':
            $('#tenth_li').addClass('active');
            $('#tenth_li i.fa-circle-o').removeClass('fa-circle-o').addClass('fa-circle');
            break;
        default: console.log('no active sidebar')
    }
}