//This script gets the name of the song we pressed on
(function($) {

    $(".songid").click(function() {
        var urlString = "/api/play/" + $(this).attr('id');
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
    });

    function search() {
        //This method gets called when user enters search
        var song = document.getElementById("song");
        var artist = document.getElementById("artist");
        var year = document.getElementById("year");

        //TODO send query to database
        //TODO for each entery append list
        deleteList();
        fillList(song, artist, year);
    }

    function deleteList() {
        //This method deletes all entries from table
        var table = document.getElementById("table");
        for (i = table.rows.length - 1; i > 1; i--) {
            table.deleteRow(i);
        }
    }

    function fillList(song, artist, year) {
        //This method adds entries to table


        var table = document.getElementById("table");

        for (i = 0; i < table.rows.length; i++) {
            // Create a new row
            var newRow = table.insertRow(table.length);
            for (var j = 0; j < 3; j++) {
                // Create a new cell
                var cell = newRow.insertCell(j);

                //if(document.getElementById("song").value.contains(song)) // TODO is a test can be removed
                //{

                //}

                var test = 0;
                var musicList = document.getElementById("music");
                for (song in musicList) {
                    // add value to the cell
                    if (test == 0) {
                        cell.innerHTML = song.title;
                        test = 1;
                    }
                }

            }
        }
    }
})(jQuery);