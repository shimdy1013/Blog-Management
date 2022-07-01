<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
    <%@include file="../main/header.jsp"%>
</head>
<body>
<!-- Navigation-->
<%@include file="../main/nav.jsp"%>
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/new-blog-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="page-heading">
                    <h1>Blog Detail</h1>
                    <span class="subheading"></span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<main class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <p>Want to get in touch? Fill out the form below to send me a message and I will get back to you as soon as possible!</p>
                <div class="my-5">
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- * * SB Forms Contact Form * *-->
                    <!-- * * * * * * * * * * * * * * *-->
                    <!-- This form is pre-integrated with SB Forms.-->
                    <!-- To make this form functional, sign up at-->
                    <!-- https://startbootstrap.com/solution/contact-forms-->
                    <!-- to get an API token!-->
                    <form id="contactForm">
                        <div class="form-floating">
                            <input class="form-control" name="email" value="${member.email}" id="email" type="text" placeholder="Enter your email..." data-sb-validations="required" />
                            <label for="email">Email address</label>
                            <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                            <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                        </div>
                        <div class="form-floating">
                            <input class="form-control" name="pw" value="${member.pw}" id="pw" type="pw" placeholder="Enter your password..." data-sb-validations="required,pw" />
                            <label for="email">Password</label>
                            <div class="invalid-feedback" data-sb-feedback="pw:required">Password is required.</div>
                        </div>
                        <div class="form-floating">
                            <input class="form-control" name="name" value="${member.name}" id="name" type="text" placeholder="Enter your name..." data-sb-validations="required,name" />
                            <label for="name">Name</label>
                            <div class="invalid-feedback" data-sb-feedback="name:required">An email is required.</div>
                        </div>
                        <div class="form-floating">
                            <input class="form-control" name="phone" value="${member.phone}" id="phone" type="text" placeholder="Enter phone..." data-sb-validations="required" />
                            <label for="phone">Phone</label>
                            <div class="invalid-feedback" data-sb-feedback="phone:required">Phone is required.</div>
                        </div>
                        <div class="form-floating">
                            <input class="form-control" name="address" value="${member.address}" id="address" type="text" placeholder="Enter address..."  data-sb-validations="required">
                            <label for="address">Address</label>
                            <div class="invalid-feedback" data-sb-feedback="content:required">Address is required.</div>
                        </div>
                        <br />
                        <!-- Submit success message-->
                        <!---->
                        <!-- This is what your users will see when the form-->
                        <!-- has successfully submitted-->
                        <div class="d-none" id="submitSuccessMessage">
                            <div class="text-center mb-3">
                                <div class="fw-bolder">Form submission successful!</div>
                                To activate this form, sign up at
                                <br />
                                <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                            </div>
                        </div>
                        <!-- Submit error message-->
                        <!---->
                        <!-- This is what your users will see when there is-->
                        <!-- an error submitting the form-->
                        <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                        <a class="btn btn-primary text-uppercase" href="update-form.do?id=${member.id}">수정</a>
                        <a class="btn btn-primary text-uppercase" href="delete.do?id=${member.id}">삭제</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<!-- Footer-->
<%@include file="../main/footer.jsp"%>
</body>
</html>
