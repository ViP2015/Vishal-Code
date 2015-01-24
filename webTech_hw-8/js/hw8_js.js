
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
        $("#fbButton").click(function(e){
        e.preventDefault();
        FB.ui(
        {
            method: 'feed',
            name: $("#captionUrl").text(),
            picture: $("#imgSrc").val(),
            caption: 'Property Information from Zillow.com',
            description: 'Last Sold Price: '+ $('#lastSoldPrice').text() + ', 30 Days Overall Change: ' + $('#hiddenSign').val() + $('#thirtOverallChange').text(),
            message: ''
        }, function(response){
                if (response && !response.error_code) {
                  alert('Posted Successfully.');
                } else {
                }
            });
        }); 
    });
    
    $(function() { 

        $( "#submitForm" ).submit(function( event ) {
                event.preventDefault();
        }).validate({
            messages: {
                streetAddress: "This is required field",
                city: "This is required field",
                state: "This is required field",
            },
            
            submitHandler: function(form) {
                var streetAddress = $('#streetAddress').val();
                var city = $('#city').val();
                var state = $('#state').val();
            
                if(streetAddress && city && state) {
                    $.ajax({
                        url: 'http://vishalpwebtechzillow-env.elasticbeanstalk.com/',
                        // this is the parameter list
                        data: $.param({ streetAddress:streetAddress, city:city, state:state}),
                        type: 'GET',
                        success: function(output) {
                            try{
                                var json_obj = $.parseJSON(output);
                                //setting up the result for the first tab- Basic Info
                                $("#captionUrl").html('<a style="text-decoration: none;" href="'+json_obj.result.homedetails+'">'+ json_obj.result.street
                                     +", "+ json_obj.result.city +", " + json_obj.result.state + "-" + json_obj.result.zipcode +'</a>');
                                $("#propType").html(json_obj.result.useCode);
                                $("#lastSoldPrice").html(json_obj.result.lastSoldPrice);
                                $("#yearBuilt").html(json_obj.result.yearBuilt);
                                $("#lastSoldDate").html(json_obj.result.lastSoldDate);
                                $("#lotSize").html(json_obj.result.lotSize);
                                $("#zestDate").html(json_obj.result.zestLastUpdatedDate);
                                $("#zestEstimate").html(json_obj.result.zestPropertyEstimate);
                                $("#finishedArea").html(json_obj.result.finishedArea);
                                $("#thirtOverallChange").html(json_obj.result.thirtyDaysOverallArrow + " " + json_obj.result.thirtyDaysOverallValue);
                                $("#bathrooms").html(json_obj.result.bathrooms);
                                $("#allTimeRange").html(json_obj.result.allTimePropRange);
                                $("#bedrooms").html(json_obj.result.bedrooms);
                                $("#rentZestDate").html(json_obj.result.rentZestLastDate);
                                $("#rentZest").html(json_obj.result.rentZestValuation);
                                $("#taxAssessYear").html(json_obj.result.taxAssessmentYear);
                                $("#thirtyOverallChange").html(json_obj.result.thirtyDaysRentArrow + " " + json_obj.result.thirtyDaysRentValue);
                                $("#taxAssess").html(json_obj.result.taxAssessment);
                                $("#allTimeRentChange").html(json_obj.result.allTimeRentChange);
                                $("#hiddenSign").val(json_obj.result.estimateValueChangeSign);
                                
                                if(json_obj.chart["1year"] != null && json_obj.chart["1year"] != "") {
                                    var imgSrc1 = json_obj.chart["1year"];
                                }
                                else {
                                    var imgSrc1 = "http://dummyimage.com/600x400/000/fff&text=Sorry+No+Chart+Available";
                                }
                                if(json_obj.chart["5years"] != null && json_obj.chart["5years"] != "") {
                                    var imgSrc5 = json_obj.chart["5years"];
                                }
                                else {
                                    var imgSrc5 = "http://dummyimage.com/600x400/000/fff&text=Sorry+No+Chart+Available";
                                }
                                if(json_obj.chart["10years"] != null && json_obj.chart["10years"] != "") {
                                    var imgSrc10 = json_obj.chart["10years"];
                                }
                                else {
                                    var imgSrc10 = "http://dummyimage.com/600x400/000/fff&text=Sorry+No+Chart+Available";
                                }

                                $("#imgSrc").val(imgSrc1);

                                $("#cr-cap-1").html("<p>"+ json_obj.result.street
                                     +", "+ json_obj.result.city +", " + json_obj.result.state + "-" + json_obj.result.zipcode + "</p>");

                                $("#cr-cap-2").html("<p>"+ json_obj.result.street
                                     +", "+ json_obj.result.city +", " + json_obj.result.state + "-" + json_obj.result.zipcode + "</p>");

                                $("#cr-cap-3").html("<p>"+ json_obj.result.street
                                     +", "+ json_obj.result.city +", " + json_obj.result.state + "-" + json_obj.result.zipcode + "</p>");

                                $("#past1Year").attr('src', imgSrc1);
                                $("#past5Year").attr('src', imgSrc5);
                                $("#past10Year").attr('src', imgSrc10);

                                $("#tabData").show();
                                $("#messageSpan").hide();

                            }catch(e){
                                $("#messageSpan").html('<font size="3" color="#DF0101" style="font-weight: bold">No Exact match found -- Varify that the given address is correct.</font>');
                                $("#messageSpan").show();
                                $("#tabData").hide();
                            }
                        },
                        error: function(){
                            $("#messageSpan").html("there is error in AJAX call");
                        }
                    })
                }
            },
            invalidHandler: function(form, validator){
            
            }
        });
    });