let book_page = 1;
let total_page = 1;
let book_content_list = [{order:1, content:""}];
let book_covers = new Array();
$(function () {
    $(".book_cover_add").change(function () {
        let form = $(".book_cover_form");
        let formData = new FormData(form[0]);
        if ($(this).val() == '' || $(this).val() == null) return;

        $.ajax({
            url: "/images/upload/book_cover",
            type: "put",
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                alert(result.message);
                if (!result.status) {
                    alert(result.message);
                    return;
                }
                let split = (result.file).split("\\");
                split = split[split.length - 1].split(".");
                let origin_file = split[0] + "." + split[1];
                let tag =
                    '<div class="book_img" data-name="' + origin_file + '"  style="background-image:url(/images/book_cover/'+origin_file+')">'+
                    '<button onclick=deleteImg("' + origin_file + '")>&times;</button>'
                '</div>';
                book_covers.push({
                    book_covers:origin_file,
                    is_poster:0
                });
                $(".book_cover_area").append(tag);
            }
        })
    })

    $(".book_content").keyup(function(){
        book_content_list[book_page-1].content = $(this).val();
    });

    $(".book_content_next").click(function () {
        if(book_page == book_content_list.length)
        book_content_list.push({order:book_page+1, content:""});
    
        $(".book_content_prev").prop("disabled", false);
        $(".book_content").val("");
        book_page++;
        total_page = book_content_list.length;
        $(".book_content").val(book_content_list[book_page-1].content);
        $(".book_page").html(book_page);
        $(".total").html(total_page);
    })

    $(".book_content_prev").click(function () {
        book_page--;
        if(book_page == 1) $(this).prop("disabled", true);
        $(".book_content").val(book_content_list[book_page-1].content);
        $(".book_page").html(book_page);
    })




    $(".book_add").click(function(){
        if(!confirm("?????? ?????? ???????????????????")) return;
        let data = {
            book_info:{
                bi_title:$(".book_title").val(),
                bi_wri_seq:$(".writer_info option:selected").val(),
                bi_gr_seq:$(".genre_info option:selected").val(),
                bi_pb_name:$(".publisher_name").val(),
                bi_explain:$(".book_explain").val(),
                bi_end_page:total_page,
            },
            book_covers:book_covers,
            book_content_list:book_content_list
        }
            console.log(data);
        $.ajax({
            url:"/api/book_add",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/books/book/list";
            }
        })
    })

})

function deleteImg(filename){
    if(!confirm("?????? ?????? ???????????? ?????????????????????????\n(???????????? : ????????? ???????????? ????????? ??? ????????????.)")){
        return;
    }
$.ajax({
    url:"/images/delete/book_cover/"+filename,
    type:"delete",
    success:function(result){
        alert(result.message);
        if(result.status) {
            book_covers = book_covers.filter((img)=>filename != img);
            $(".book_cover_area").html("");
            for(let i=0; i<book_covers.length; i++) {
                let tag = 
                '<div class="book_img" data-name="' + book_covers[i] + '"  style="background-image:url(/images/book_cover/'+book_covers[i]+')">'+
                '<button onclick=deleteImg("' + book_covers[i] + '")>&times;</button>'
                '</div>';
                $(".book_cover_area").append(tag);
            }
        }
    }
})
}