$(function(){

	$('.advertismentForm').submit(function(e){
		e.preventDefault();

		var $alertDiv = $('.alert-danger'),
			adId = $('#advertismentId').val();

		$alertDiv.text('')
				 .hide();

		$.get('https://api.tamedia.cloud/homegate/v1/rs/real-estates/' + adId, {
			apikey: 'aac2c78bdfd7487d998f7ae679b55c9b'
		}).done(function(data) {
			// console.log(data);

			$('.equipment').empty();

			$('.imgCont').css('background-image', 'url('+data.picFilename1Medium.split('/s/')[0]+')');
			$('.title').html('<div class="col-md-12"><h2>'+data.title+'</h2><span>'+data.propertyCityname+', '+data.propertyCountryLabel+'</span></div>');

			$('.labels').html('<div class="col-md-12"><span class="label label-info">'+data.offerType+'</span><span class="label label-default">'+data.objectTypeLabel+'</span><span class="label label-default">'+data.yearBuilt+'</span></div>');

			$('.details').html('<div class="col-md-12"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>Bedrooms: '+data.numberRooms+'</div><div class="col-md-6"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>Surface Living: '+data.surfaceLiving+'</div><div class="col-md-6"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>Surface Property: '+data.surfaceProperty+'</div>');

			data.equipment.forEach(function(equipment){
				$('.equipment').append('<div class="col-md-6"><span class="glyphicon glyphicon-check" aria-hidden="true"></span>'+equipment.label+'</div>');
			});

			$('.btn_price').html('<div class="col-md-6"><a href="'+data.objectUrl+'" class="btn btn-success" target="_blank">Homegate <span aria-hidden="true">&rarr;</span></a></div><div class="col-md-6"><h1>'+data.sellingPrice.toLocaleString("en")+data.currency+'</h1></div>');

			$('.homeList').show();

			$('html,body').animate({scrollTop: $('.homeList').offset().top},2000);

		}).fail(function(xhr, status, error) {
			var respError = $.parseJSON(xhr.responseText);
			$alertDiv.text(respError.message)
					 .show();
		});
	});

});