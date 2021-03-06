type = ['','info','success','warning','danger'];


demo = {
    initPickColor: function(){
        $('.pick-class-label').click(function(){
            var new_class = $(this).attr('new-class');
            var old_class = $('#display-buttons').attr('data-class');
            var display_div = $('#display-buttons');
            if(display_div.length) {
            var display_buttons = display_div.find('.btn');
            display_buttons.removeClass(old_class);
            display_buttons.addClass(new_class);
            display_div.attr('data-class', new_class);
            }
        });
    },

    initChartist: function(){

        var dataSales = {
          labels: ['9:00AM', '12:00AM', '3:00PM', '6:00PM', '9:00PM', '12:00PM', '3:00AM', '6:00AM'],
          series: [
             [287, 385, 490, 562, 594, 626, 698, 895, 952],
            [67, 152, 193, 240, 387, 435, 535, 642, 744],
            [23, 113, 67, 108, 190, 239, 307, 410, 410]
          ]
        };

        var optionsSales = {
          lineSmooth: false,
          low: 0,
          high: 1000,
          showArea: true,
          height: "245px",
          axisX: {
            showGrid: false,
          },
          lineSmooth: Chartist.Interpolation.simple({
            divisor: 3
          }),
          showLine: true,
          showPoint: false,
        };

        var responsiveSales = [
          ['screen and (max-width: 640px)', {
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];

        Chartist.Line('#chartHours', dataSales, optionsSales, responsiveSales);


        var data = {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          series: [
            [542, 543, 520, 680, 653, 753, 326, 434, 568, 610, 756, 895],
            [230, 293, 380, 480, 503, 553, 600, 664, 698, 710, 736, 795]
          ]
        };

        var options = {
            seriesBarDistance: 10,
            axisX: {
                showGrid: false
            },
            height: "245px"
        };

        var responsiveOptions = [
          ['screen and (max-width: 640px)', {
            seriesBarDistance: 5,
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];

        Chartist.Line('#chartActivity', data, options, responsiveOptions);

        var dataPreferences = {
            series: [
                [25, 30, 20, 25]
            ]
        };

        var optionsPreferences = {
            donut: true,
            donutWidth: 40,
            startAngle: 0,
            total: 100,
            showLabel: false,
            axisX: {
                showGrid: false
            }
        };

        Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);

        Chartist.Pie('#chartPreferences', {
          labels: ['62%','32%','6%'],
          series: [62, 32, 6]
        });
    },

    initGoogleMaps: function(){
        var myLatlng = new google.maps.LatLng(40.748817, -73.985428);
        var mapOptions = {
          zoom: 13,
          center: myLatlng,
          scrollwheel: false, //we disable de scroll over the map, it is a really annoing when you scroll through page
          styles: [{"featureType":"water","stylers":[{"saturation":43},{"lightness":-11},{"hue":"#0088ff"}]},{"featureType":"road","elementType":"geometry.fill","stylers":[{"hue":"#ff0000"},{"saturation":-100},{"lightness":99}]},{"featureType":"road","elementType":"geometry.stroke","stylers":[{"color":"#808080"},{"lightness":54}]},{"featureType":"landscape.man_made","elementType":"geometry.fill","stylers":[{"color":"#ece2d9"}]},{"featureType":"poi.park","elementType":"geometry.fill","stylers":[{"color":"#ccdca1"}]},{"featureType":"road","elementType":"labels.text.fill","stylers":[{"color":"#767676"}]},{"featureType":"road","elementType":"labels.text.stroke","stylers":[{"color":"#ffffff"}]},{"featureType":"poi","stylers":[{"visibility":"off"}]},{"featureType":"landscape.natural","elementType":"geometry.fill","stylers":[{"visibility":"on"},{"color":"#b8cb93"}]},{"featureType":"poi.park","stylers":[{"visibility":"on"}]},{"featureType":"poi.sports_complex","stylers":[{"visibility":"on"}]},{"featureType":"poi.medical","stylers":[{"visibility":"on"}]},{"featureType":"poi.business","stylers":[{"visibility":"simplified"}]}]

        }
        var map = new google.maps.Map(document.getElementById("map"), mapOptions);

        var marker = new google.maps.Marker({
            position: myLatlng,
            title:"Hello World!"
        });

        // To add the marker to the map, call setMap();
        marker.setMap(map);
    },

	showNotification: function(from, align){
    	color = Math.floor((Math.random() * 4) + 1);

    	$.notify({
        	icon: "ti-gift",
        	message: "Welcome to <b>Paper Dashboard</b> - a beautiful freebie for every web developer."

        },{
            type: type[color],
            timer: 4000,
            placement: {
                from: from,
                align: align
            }
        });
	}
}

$('#show-list-topic').click(function(event) {
  if ($('#list-topic').is(':hidden')) {
	    $('#list-topic').show();
  } else {
	    $('#list-topic').hide();
  }
});

/**
 * Show message if database has not data or search not found in page list
 */
var countElements = $('#table-contain tbody tr').length;
console.log(countElements);
if (countElements == 1) {// 1 is tr of element $('.no-result-search') not of record table
    $('.no-result-search').show();
}

/**
 * remove image when click button remove image
 */
$('.link-xoa').bind('click',function(e){
    e.preventDefault();
    var form = $('#form-xoa');
    var title = $(this).attr('data-title');
    var body = '<i>' + $(this).attr('data-confirm') + '</i>';
    $('#title-content').html(title);
    $('#body-content').html(body);
    $('#confirm').modal('show');
    $('#delete-btn').one('click', function(){
        form.submit();
        console.log(form);
        $('#confirm').modal('hide');
    })
});

// active link in left bar
$(document).ready(function() {
	// get current URL path and assign 'active' class
	var pathName = window.location.pathname;
	var pathParent = pathName.slice(0, pathName.lastIndexOf("/"));
	$('.nav > li > a[href="'+pathName+'"]').parent().addClass('active');
	$('.nav > li > a[href="'+pathParent+'/index"]').parent().addClass('active');
})


// show list deatai follow giangvien
$('#giangvien-hopdong').change(function(event) {
//	alert("ss");
        var uid = $(this).val();
        $.ajax({
                url: "http://localhost:8080/DoAnCNPM_SE03/admin/hopdong/detai-follow-giangvien?uid="+uid,
                type: 'GET',
                success: function( msg ) {
                    $('#list-detai-follow-giangvien').html(msg);
                }
            });
    });

// show date time picker bootstrap

$(function () {
    $('.datetime-choose').datetimepicker({
        format: 'yyyy-mm-dd HH:ii:ss'
    });
});

$('.edit-capdetai').click(function(event) {
	var idcdt = $(this).data('idcdt');
	var namecdt = $(this).data('namecdt');
	var url = $(this).data('url');
	$("#" + idcdt).html('<td>'+idcdt+'</td><td colspan="2"><form action="' + url + '" method="post" style="display:inline;"> <input name = "name-cdt" value="'+ namecdt +'" style="margin-right:26%;"><button class="btn btn-info" type="submit">Save</button> </form></td>');
});

/**
 * Show image when choose image
 */
$('#picture-avt').change( function(event) {
    var select_input_file = $(this).val();
    if (select_input_file) {
        var imgpath = URL.createObjectURL(event.target.files[0]);
        $("#preview-image").fadeIn("fast").attr('src',imgpath);
    } else {
        $("#preview-image").fadeOut("fast");
    }
});
/**
 * edit time dky ỏ thuyet minh
 */
$('.change-time-action').click(function(event) {
	var action = $(this).data('action');
	$("#change-time").show();
	$("#change-time").attr('action', action);
});
