$(function(){
    //인기 검색에 글자 롤링 애니메이션
      const $word = $(".popular-word ul li")
      const len = $word.length;
      let index = 0;
      const textInTimer = 3000;
      const textOutTimer = 3000;

    function animationText() {
        $word.removeClass("text-in text-out");
        $word.eq(index).addClass("text-in")
        //2.8초에 한 번씩 실행하는 함수
        setTimeout(function(){
            $word.eq(index).addClass("text-out");
        }, textOutTimer);
        setTimeout(function(){
            //인덱스번호가 1 2 3 4 5 6 7 8 9 10이 되면 다시 0으로 초기화
            index = (index + 1) % len;
            animationText();
        }, textInTimer )
    }
    //전체 메뉴
    animationText();



    let total_menu_sw = 0;
    $(".total-menu").click(function(){
        if (total_menu_sw == 0) {
            total_menu_sw = 1;
            $(".total-menu-area").show();
            $(".total-menu-area img").fadeIn();
        } else {
            total_menu_sw = 0;
            $(".total-menu-area").hide();
            $(".total-menu-area img").hide();
        }
    });

    //main swiper
    let swiper1 = new Swiper(".swiper1", {
        loop: true,
        autoplay:{
            delay:5000
        }
    });
    let swiper2 = new Swiper(".swiper2", {
        loop: true,
        navigation:{
            prevEl:".s1 .swiper-button-prev",
            nextEl:".s1 .swiper-button-next"
        }
    });
    swiper1.controller.control = swiper2;
    swiper2.controller.control = swiper1;

    //.s2 swiper slide
    let swiper3 = new Swiper(".swiper3", {
        slidesPerView: 2.7,
        spaceBetween: 24,
        navigation: {
            prevEl: ".s2 .swiper-button-prev",
            nextEl: ".s2 .swiper-button-next"
        }
    });

    //.s3 swiper slide
    let swiper4 = new Swiper(".swiper4", {
            loop: true,
           navigation: {
            prevEl: ".s3 .swiper-button-prev",
            nextEl: ".s3 .swiper-button-next"
        }
    });

    //.s4 swiper slide
    let swiper5 = new Swiper(".swiper5", {
           loop: true,
           slidesPerView: 2,
           navigation: {
            prevEl: ".s4 .swiper-button-prev",
            nextEl: ".s4 .swiper-button-next"
        }
    });

    //.s5 swiper slide
    let swiper6 = new Swiper(".swiper6", {
           loop: true,
           slidesPerView: 5,
           spaceBetween: 50,
           centeredSlides: true
    });


    //footer family site
    $(".family > a").click(function(e){
        e.preventDefault();
        $('.family-list').slideToggle();
        $(this).find('span').toggleClass('active');
    });




});