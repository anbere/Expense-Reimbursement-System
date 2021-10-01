function update() {
    fetch('DashboardServlet', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
        .then(data => {
            if (data == null) {
                window.location.href = "index.html"
            }
            // document.getElementById("updateUser").innerHTML = data.username;
            document.getElementById("updateUser").innerHTML = data.username;
            document.getElementById("updateUser2").innerHTML = data.username;

        });
};

function populatePending() {
    // let type = { type: "PENDING" }

    fetch('DisplayPendingReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
            // body: JSON.stringify(pending)
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            let myString = '';
            for (row of data) {

                myString += JSON.stringify(row);
                console.log(JSON.stringify(row));

            }
            document.getElementById("pending_reimbursements").innerHTML = myString;

        });
}

function populateCompleted() {
    fetch('DisplayCompletedReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
            // body: JSON.stringify(pending)
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            let myString = '';
            for (row of data) {

                myString += JSON.stringify(row);
                console.log(JSON.stringify(row));

            }
            document.getElementById("completed_reimbursements").innerHTML = myString;

        });
}

function populateManagerPending() {
    fetch('DisplayAllPendingReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
            // body: JSON.stringify(pending)
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            let myString = '';
            for (row of data) {

                myString += JSON.stringify(row);
                console.log(JSON.stringify(row));

            }
            document.getElementById("all_pending_reimbursements").innerHTML = myString;

        });
}

function populateManagerCompleted() {
    fetch('DisplayAllCompletedReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
            // body: JSON.stringify(pending)
        }).then(response => response.json())
        .then(data => {
            console.log(data);
            let myString = '';
            for (row of data) {

                myString += JSON.stringify(row);
                console.log(JSON.stringify(row));

            }
            document.getElementById("all_completed_reimbursements").innerHTML = myString;

        });
}

function createRequest() {

    let reimbursementInfo = {
        r_type: document.getElementById("r_reason").value,
        r_amount: document.getElementById("r_amount").value,
        r_description: document.getElementById("description").value
    }

    fetch('ReimbursementServlet', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reimbursementInfo)
        }).then(response => response.json())
        .then(data => {
            data.success ? reimbursementCreated() = "Dashboard.html" : alert("Reimbursement was unable to be created.")
        })
}

function reimbursementCreated() {
    alert("Reimbursement succesfully created.")
    window.location.href = "Dashboard.html";
}

function openPage(pageName, elmnt, color) {
    // Hide all elements with class="tabcontent" by default */
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Remove the background color of all tablinks/buttons
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.backgroundColor = "";
    }

    // Show the specific tab content
    document.getElementById(pageName).style.display = "block";

    // Add the specific color to the button used to open the tab content
    elmnt.style.backgroundColor = color;
}

// Get the element with id="defaultOpen" and click on it
//document.getElementById("defaultOpen").click();

function signOut() {
    fetch('EndSessionServlet', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(window.location.href = "index.html")

}

//ManagerDashboardServlet