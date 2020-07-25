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
                username: $form.find("input#username").val(),
                email: $form.find("input#email").val(),
                password: $form.find("input#password").val(),
            }),
            responce => {
                console.log(responce);
                alert("更新が完了しました。");
                location.href = "./";
            },
            error => {
                console.log(error);
                alert("サーバー側でエラーが発生しました。");
            });
        return false;
    });

    $(".del_button").on("click", function() {
        fetchDelete(
            $form.attr("action"),
            "{}",
            responce => {
                console.log(responce);
                alert("削除が完了しました。");
                location.href = "./";
            },
            error => {
                console.log(error);
                alert("サーバー側でエラーが発生しました。");
            });
        return false;
    });
});
