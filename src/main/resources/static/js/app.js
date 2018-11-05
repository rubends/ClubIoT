//This script gets the name of the song we pressed on
(function($) {

    //$(".songList").on("click", ".songid", function() {


    function playSong(){
        var urlString = "api/play/" + $(this).attr('id');
        console.log("click");
        $.ajax({
            url: urlString,
            dataType: 'text',
            type: 'post'
        }).done(function(data) {
            var song = $.parseJSON(data)
            console.log(song);
            $(".song").text(song['artist'] + " - " + song['title']);
        });
    }

    $(".songid").click(playSong);

    search = function() {
        //This method gets called when user enters search
        //var song = document.getElementById("song");
        //var artist = document.getElementById("artist");

        //TODO send query to database
        //TODO for each entry append list
        var searchString = "";

        if(!($("#song").val()=="" || $("#song").val()==undefined )){
            // "Song" searchbar is not empty

            searchString = document.getElementById("song").value;
        }else if(!($("#artist").val()=="" || $("#artist").val()==undefined)){
            // "artist" searchbar is not empty

            searchString = document.getElementById("artist").value;
        }else{
            // "Song" or "artist" searchbar was empty
            $("#table").empty();
            $("#table").append("Not correctly searching");
        }

        console.log("Search Text: ",searchString);
        if(searchString != "")
        {
            $.ajax({
                type: "get",
                url: "api/search",
                data: {"song": searchString},
                dataType: "text",
                success: function (data) {
                    //var searchList = $.parseJSON(data);
                    // Check if response data is not empty
                    var song = $.parseJSON(data);
                    console.log(song);

                    //console.log("Song 1",song.data[1].artist);
                    if (data)
                    {
                        // Empty table
                        $("#table").empty();

                        // Add all the objects from the jsonarray to the table
                        var tr;
                        tr = $('<tr/>');
                        tr.append("<td> <a href='.'><button>Clear search</button></a></td>");
                        $("table").append(tr);
                        for(var i=0;i<song.data.length;i++)
                        {
                            tr = $('<tr/>');
                            tr.append("<td>" + song.data[i].title + "</td>");
                            tr.append("<td>" + song.data[i].artist + "</td>");
                            tr.append("<td>" + song.data[i].year + "</td>");
                            //tr.append("<td>" + song.data[i].id + "</td>");
                            tr.append("<td class=\"songid\" id=" + song.data[i].id + "\>" +  "play" + "</td>");

                            $("table").append(tr); // TODO Fix this
                            $(".songid").click(playSong);
                        }
                    }
                }
            });

        }
    }

})(jQuery);