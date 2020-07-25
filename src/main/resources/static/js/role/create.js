$(function() {
    var $form = $("form.card-body");
    $form.on("submit", function() {

        if ($form.find(":invalid").length > 0) {
            alert("入力項目にエラーがあります。");
            return false;
        }

        fetchPost(
            $form.attr("action"),
            JSON.stringify({
                code: $form.find("input#code").val(),
                name: $form.find("input#name").val(),
            }),
            responce => {
                console.log(responce);
                alert("登録が完了しました。");
                location.href = "./";
            },
            error => {
                console.log(error);
                alert("サーバー側でエラーが発生しました。");
            });
        return false;
    });
})
