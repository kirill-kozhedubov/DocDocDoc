(function (window, $, undefined) {
    var page;
    $(function () {//init
        page = $(this);
        page.find("#js-authorize-user").bind("click", authorize);
        page.find("#js-register-user").bind("click", register);
    });

    function authorize() {
        var email = page.find("#email").val();
        var password = page.find("#password").val();
        var wind = window.location + "/sso/login/request";

        $.ajax({
            type: "POST",
            url: "http://" + location.host + "/sso/login/request",
            data: {email: email, password: password},
            success: function (result) {
                alert("yyeeee bob", result);
            },
            error: function (e) {
                alert("nooooo bob", e);
            }
        });


    }

    function register() {
        var firstName = page.find(".js-input-first-name").val();
        var lastName = page.find(".js-input-last-name").val();
        var email = page.find(".js-input-email").val();
        var password1 = page.find(".js-input-password1").val();
        var password2 = page.find(".js-input-password2").val();
        var isValid = validateRegister(firstName, lastName, email, password1, password2);
        if (isValid) {
            $.ajax({
                type: "POST",
                url: "http://" + location.host + "/sso/registration/request",
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: password1
                },
                success: function (result) {
                    if (result.redirectURL) {
                        window.location.href = "http://" + location.host + result.redirectURL;
                    }
                },
                error: function (e) {
                    alert("nooooo bob", e);
                }
            });
        }
    }

    function validateRegister(first, last, email, password1, password2) {
        var isValidData =
            first.length > 0 &&
            last.length > 0 &&
            email.length > 0 &&
            password1.length > 0 &&
            password2.length > 0 &&
            password1 === password2;
        return isValidData;
    }


}(window, jQuery));