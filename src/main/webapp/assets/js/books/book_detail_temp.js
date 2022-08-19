let book_page = 1;
let total_page = 1;
let book_covers = new Array();
$(function () {
    $.ajax({
        url:"/api/book_detail/temp?seq="+bi_seq,
        type:"get",
        success:function(r){
            for(let i=0; i<r.coverList.length; i++){
                book_covers.push({seq:r.coverList[i].seq, book_covers:r.coverList[i].book_covers});
            }
            $(".book_add_header_item").html("");
            let header = '';
            header +=
            '<div class="book_header_left">'+
            '<p>책 표지 등록</p>'+
            '<form class="book_cover_form">'+
                '<input type="file" name="file" class="book_cover_add">'+
            '</form>'+
            '<div class="book_cover_area">';
            for(let i=0; i<r.coverList.length; i++){
                    header += 
                    '<div class="book_img" filename="'+book_covers[i].book_covers+'"  style="background-image:url(\'/images/book_cover/'+book_covers[i].book_covers+'\')">'+
                        '<button onclick="deleteImg('+book_covers[i].book_covers+', '+book_covers[i].seq+')">&times;</button>'+
                    '</div>'
                }
            header += 
            '</div>'+
            '<p>책 타이틀</p>'+
            '<input type="text" class="book_title" placeholder="책 제목을 입력하세요" value="'+r.bookList.bi_title+'">'+
            '<p>출판</p>'+
            '<input type="text" class="publisher_name" placeholder="출판사를 입력하세요" value="'+r.bookList.bi_pb_name+'">'+
            '<p>작가</p>'+
            '<select class="writer_info">';
                for(let i=0; i<r.writerList.length; i++){
                    header += 
                    '<option value="'+r.writerList[i].wri_seq+'">'+r.writerList[i].wri_name+'</option>'
                }
            header += 
            '</select>'+
            '<p>장르</p>'+
            '<select class="genre_info">';
            for(let i=0; i<r.genreList.length; i++){
                header += 
                '<option value="'+r.genreList[i].gr_seq+'">'+r.genreList[i].gr_name+'</option>'
            }
        header += 
            '</select>'+
        '</div>'+
        '<div class="book_header_right">'+
            '<button class="book_modify">수정하기</button>'+
            '<p>책 소개</p>'+
            '<textarea class="book_explain" cols="30" rows="10">'+r.bookList.bi_explain+'</textarea>'+
        '</div>';
        $(".book_add_header_item").append(header);
        $(".book_content").val(r.contentList);
        }
    })


    $(".book_cover_add").change(function () {
        let form = $(".book_cover_form");
        let formData = new FormData(form[0]);
        if ($(this).val() == '' || $(this).val() == null) return;

        $.ajax({
            url:"/images/upload/book_cover",
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(result) {
                if(!result.status) {
                    alert(result.message);
                    return;
                }
                let split = (result.file).split("\\");
                split = split[split.length - 1].split(".");
                let origin_file = split[0] + "." + split[1];

                let imgData = {
                    seq:book_seq,
                    book_covers:origin_file
                }
                $.ajax({
                    url:"/api/book_cover/add",
                    type:"put",
                    contentType:"application/json",
                    data:JSON.stringify(imgData),
                    success:function(r) {
                        let tag = 
                        '<div class="book_img" filename="' + origin_file + '"  style="background-image:url(/images/book_cover/'+origin_file+')">'+
                            '<button onclick="deleteImg(\''+ origin_file+'\', '+r.seq+')">&times;</button>'+
                        '</div>';
                        book_covers.push({
                            seq:r.seq,
                            book_covers:origin_file,
                            is_poster:0
                        });
                        $(".book_cover_area").append(tag);
                    }
                })
            }
        })
    })


    $(".book_modify").click(function(){
        if(!confirm("책을 수정 하시겠습니까?")) return;
        let data = {
            book_info:{
                bi_seq:book_seq,
                bi_title:$(".book_title").val(),
                bi_wri_seq:$(".writer_info option:selected").val(),
                bi_gr_seq:$(".genre_info option:selected").val(),
                bi_pb_name:$(".publisher_name").val(),
                bi_explain:$(".book_explain").val(),
                bi_end_page:total_page,
            },
            book_content_list:book_content_list,
            book_covers:book_covers
        }
            console.log(data);
        $.ajax({
            url:"/api/book_modify",
            type:"patch",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/books/book/list";
            }
        })
    })

})

function deleteImg(filename,seq){
    if(!confirm("해당 커버 이미지를 삭제하시겠습니까?\n(❗주의❗ : 삭제된 데이터는 되돌릴 수 없습니다.)")){
        return;
    }
    $.ajax({
        url:"/images/delete/book_cover/"+filename,
        type:"delete",
        success:function(result){
            if(result.status) {
                book_covers = book_covers.filter((img)=>filename != img.book_covers);
                $(".book_cover_area").html("");
            for(let i=0; i<book_covers.length; i++) {
                let tag = 
                '<div class="book_img" filename="' + book_covers[i].book_covers+ '","'+book_covers[i].seq+'" '+
                'style="background-image:url(\'/images/book_cover/'+book_covers[i].book_covers+'")">'+
                '<button onclick="deleteImg(\''+book_covers[i].book_covers+'\','+book_covers[i].seq+')">&times;</button>'+
                '</div>';
                $(".book_cover_area").append(tag);
            }
        }
        $.ajax({
            url:"/api/book_cover/delete?seq="+seq,
            type:"delete",
            success:function(r) {
                alert(r.message);
            }
        })
    }
})
}