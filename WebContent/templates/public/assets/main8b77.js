function getWidthBrowser() {
	var a;
	return "number" == typeof window.innerWidth ? a = window.innerWidth : document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight) ? a = document.documentElement.clientWidth : document.body && (document.body.clientWidth || document.body.clientHeight) && (a = document.body.clientWidth), a
}

function preventDefault(a) {
	a = a || window.event, a.preventDefault && a.preventDefault(), a.returnValue = !1
}

function preventDefaultForScrollKeys(a) {
	if (keys[a.keyCode]) return preventDefault(a), !1
		}

function disableScroll() {
	window.addEventListener && window.addEventListener("DOMMouseScroll", preventDefault, !1), window.onwheel = preventDefault, window.onmousewheel = document.onmousewheel = preventDefault, window.ontouchmove = preventDefault, document.onkeydown = preventDefaultForScrollKeys
}

function enableScroll() {
	window.removeEventListener && window.removeEventListener("DOMMouseScroll", preventDefault, !1), window.onmousewheel = document.onmousewheel = null, window.onwheel = null, window.ontouchmove = null, document.onkeydown = null
}
$(document).ready(function() {
	if ($('#gotoTop').length) {
		var scrollTrigger = 100, // px
			backToTop = function () {
				var scrollTop = $(window).scrollTop();
				if (scrollTop > scrollTrigger) {
					$('#gotoTop').addClass('show');
				} else {
					$('#gotoTop').removeClass('show');
				}
			};
		backToTop();
		$(window).on('scroll', function () {
			backToTop();
		});
		$('#gotoTop').on('click', function (e) {
			e.preventDefault();
			$('html,body').animate({
				scrollTop: 0
			}, 700);
		});
	}

	$("#owl-demo-slide").owlCarousel({
		pagination: !0,
		navigation: !0,
		autoPlay: !0,
		navigationText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
		true: !0,
		slideSpeed: 300,
		paginationSpeed: 400,
		singleItem: !0
	}), $("#owl-demo-people-say").owlCarousel({
		pagination: !1,
		navigation: !1,
		autoPlay: !0,
		slideSpeed: 300,
		paginationSpeed: 400,
		items: 1,
		itemsDesktop: [1200, 1],
		itemsDesktopSmall: [991, 1],
		itemsTablet: [767, 1],
		itemsMobile: [375, 1]
	}), $("#people-say-pre").click(function() {
		return $("#owl-demo-people-say").trigger("owl.prev"), !1
	}), $("#people-say-next").click(function() {
		return $("#owl-demo-people-say").trigger("owl.next"), !1
	}), $(".btn_cl_newtab").click(function() {
		document.location.href = $(this).attr("href")
	}), $("#brand-pre").click(function() {
		return $("#owl-demo-brand").trigger("owl.prev"), !1
	}), $("#brand-next").click(function() {
		return $("#owl-demo-brand").trigger("owl.next"), !1
	}), $("#owl-demo-brand").owlCarousel({
		pagination: !1,
		navigation: !1,
		items: 5,
		itemsDesktop: [1200, 5],
		itemsDesktopSmall: [991, 3],
		itemsTablet: [767, 2],
		itemsMobile: [375, 1]
	}), setTimeout(function() {
		var a = window.location.href;
		a.indexOf("contact_error_notification") > -1 || a.indexOf("contact_posted=true") > -1 && ($(".notification_contact").fadeIn(400).delay(3e3).fadeOut(), $(".overlayopacity").fadeIn(400).delay(3e3).fadeOut(), disableScroll(), setTimeout(function() {
			enableScroll()
		}, 3e3))
	}, 1e3)
}), $(window).resize(function() {});
var keys = {
	37: 1,
	38: 1,
	39: 1,
	40: 1
};
jQuery(function(a) {
	"use strict";
	a(".bars-navigation").click(function() {
		return a(".mobimenu").is(":hidden") ? a(".mobimenu").slideDown("fast") : a(".mobimenu").slideUp("fast"), !1
	}), a(".category").accordion({
		accordion: !1,
		speed: 300,
		closedSign: "+",
		openedSign: "-"
	}), a(".mobile-menu").accordion({
		accordion: !1,
		speed: 300,
		closedSign: "+",
		openedSign: "-"
	})
}),
	function(a) {
	a.fn.extend({
		accordion: function(b) {
			var c = {
				accordion: "true",
				speed: 300,
				closedSign: "[-]",
				openedSign: "[+]"
			}, d = a.extend(c, b),
				e = a(this);
			e.find("li").each(function() {
				0 != a(this).find("ul").size() && (a(this).find("a:first").after("<em>" + d.closedSign + "</em>"), "#" == a(this).find("a:first").attr("href") && a(this).find("a:first").click(function() {
					return !1
				}))
			}), e.find("li em").click(function() {
				0 != a(this).parent().find("ul").size() && (d.accordion && (a(this).parent().find("ul").is(":visible") || (parents = a(this).parent().parents("ul"), visible = e.find("ul:visible"), visible.each(function(b) {
					var c = !0;
					parents.each(function(a) {
						if (parents[a] == visible[b]) return c = !1, !1
							}), c && a(this).parent().find("ul") != visible[b] && a(visible[b]).slideUp(d.speed, function() {
						a(this).parent("li").find("em:first").html(d.closedSign)
					})
				}))), a(this).parent().find("ul:first").is(":visible") ? a(this).parent().find("ul:first").slideUp(d.speed, function() {
					a(this).parent("li").find("em:first").delay(d.speed).html(d.closedSign)
				}) : a(this).parent().find("ul:first").slideDown(d.speed, function() {
					a(this).parent("li").find("em:first").delay(d.speed).html(d.openedSign)
				}))
			})
		}
	})
}(jQuery), $(".small_about_thumbnail .ov").click(function() {
	var a = $(this).find("img").attr("src");
	return $(".home_about_big_banner img").attr({
		src: a
	}), !1
});

equalheight = function(container){

	var currentTallest = 0,
		currentRowStart = 0,
		rowDivs = new Array(),
		$el,
		topPosition = 0;
	$(container).each(function() {

		$el = $(this);
		$($el).height('auto')
		topPostion = $el.position().top;

		if (currentRowStart != topPostion) {
			for (currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
				rowDivs[currentDiv].height(currentTallest);
			}
			rowDivs.length = 0; // empty the array
			currentRowStart = topPostion;
			currentTallest = $el.height();
			rowDivs.push($el);
		} else {
			rowDivs.push($el);
			currentTallest = (currentTallest < $el.height()) ? ($el.height()) : (currentTallest);
		}
		for (currentDiv = 0 ; currentDiv < rowDivs.length ; currentDiv++) {
			rowDivs[currentDiv].height(currentTallest);
		}
	});
}

$(window).load(function() {
	equalheight('.home_knh .home_knh_items');
});
$(window).resize(function(){
	equalheight('.home_knh .home_knh_items');
});