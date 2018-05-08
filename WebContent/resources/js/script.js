$(document).ready(function() {
	$("#banner").css({
		"height" : $(window).height() + "px"
	});

	var flag = false;
	var scroll;

	$(window).scroll(function() {
		scroll = $(window).scrollTop();

		if (scroll > 200) {
			if (!flag) {
				$("#logo").css({
					"margin-top" : "-5px",
					"margin-bottom" : "90px",
					"width" : "140px",
					"height" : "60px"
				});

				$("header").css({
					"background-color" : "#3C3C3C"
				})
				flag = true;
			}
		} else {
			if (flag) {
				$("#logo").css({
					"margin-top" : "150px",
					"width" : "520px",
					"height" : "203px"
				});
				$("header").css({
					"background-color" : "transparent"
				})
				flag = false;
			}
		}

	});
});

$(function() {
	$('a[href*=#]').on('click', function(e) {
		e.preventDefault();
		$('html, body').animate({
			scrollTop : $($(this).attr('href')).offset().top
		}, 500, 'linear');
	});
});
