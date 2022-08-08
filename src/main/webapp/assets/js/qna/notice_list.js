$(function () {
    $(".del_notice").click(function () {
        if (!confirm("공지사항을 삭제하시겠습니까?")) return;

        $.ajax({
            url: "/api/notice/delete?seq=" + $(this).attr("data-seq"),
            type: "delete",
            success: function (r) {
                alert(r.message);
                location.reload();
            }
        })
    })
})