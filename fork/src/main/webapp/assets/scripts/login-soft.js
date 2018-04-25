var Login = function() {

    var handleLogin = function() {
    	$("#identity_select2").select2();
        $('.login-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            rules : {
                username : {
                    required : true
                },
                password : {
                    required : true
                },
                rand : {
                    required : true
                },
               remember : {
                    required : false
                }
            },

            messages : {
                loginName : {
                    required : "用户名不能为空."
                },
                rand : {
                    required : "验证码不能为空."
                },
                password : {
                    required : "密码不能为空."
                }
           },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler : function(form) {
                var passwordInput = $('[name="pwd"]');
                //alert(passwordInput.val());
                //alert(sha256_digest(passwordInput.val()));
                passwordInput.val(sha256_digest(passwordInput.val()));
                form.submit();
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit();
                }
                return false;
            }
        });
    }

    return {
        // main function to initiate the module
        init : function() {
            //console.log(12);
            handleLogin();
            //handleForgetPassword();
           // handleRegister();

            $.backstretch([ "assets/img/bg/1.jpg", "assets/img/bg/2.jpg",
                    "assets/img/bg/3.jpg", "assets/img/bg/4.jpg", "assets/img/bg/5.jpg", 
                    "assets/img/bg/6.jpg", "assets/img/bg/7.jpg", "assets/img/bg/8.jpg", 
                    "assets/img/bg/9.jpg" ], {
                fade : 1000,
                duration : 5000
            });
        }

    };

}();