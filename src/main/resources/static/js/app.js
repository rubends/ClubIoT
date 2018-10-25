//This script gets the name of the song we pressed on
var table = document.getElementById("table");
if (table != null) {
    for (var i = 2; i < table.rows.length; i++) {
        for (var j = 0; j < table.rows[i].cells.length; j++)
            table.rows[i].cells[j].onclick = function () {
                tableText(this);
            };
    }
}

//TODO Can be removed
function tableText(tableCell) {
    alert(tableCell.innerHTML);
}

function search() {
    //This method gets called when user enters search
    var song = document.getElementById("song");
    var artist = document.getElementById("artist");
    var year = document.getElementById("year");

    //TODO send query to database
    //TODO for each entery append list
    deleteList();
    fillList(song,artist,year);
}
function deleteList(){
    //This method deletes all entries from table
    var table = document.getElementById("table");
    for(i = table.rows.length - 1; i > 1; i--)
    {
        table.deleteRow(i);
    }
}
function fillList(song,artist,year){
    //This method adds entries to table
    //testdata
    //TODO remove testdata
    var array = [["A1", "B1", "C1"],
        ["A2", "B2", "C2"],
        ["A3", "B3", "C3"],
        ["A4", "B4", "C4"],
        ["A5", "B5", "C5"],
        ["A1", "B1", "C1"],
        ["A2", "B2", "C2"],
        ["A3", "B3", "C3"],
        ["A4", "B4", "C4"],
        ["A5", "B5", "C5"]];

    if(document.getElementById("song").value === "C") // TODO is a test can be removed
    {
        array[0][1] = "Call Out My Name";
    }

    var table = document.getElementById("table");

    for(i = 0; i < array.length; i++)
    {
        // Create a new row
        var newRow = table.insertRow(table.length);
        for(var j = 0; j < array[i].length; j++)
        {
            // Create a new cell
            var cell = newRow.insertCell(j);

            // add value to the cell
            cell.innerHTML = array[i][j];
        }
    }
}