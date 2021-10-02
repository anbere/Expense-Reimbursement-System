var pendingOffset = 0;
var completedOffset = 0;

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
            // document.getElementById("updateUser2").innerHTML = data.username;
            for (e of document.getElementsByName("updateUsername")) {
                e.innerHTML = data.username;
            }

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
            console.log("data in populate pending: " + data);
            var empTable = document.getElementById('emp_pending_body');
            if (empTable.childElementCount === data.length)
                return;
            for (db_row of data) {
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var amount = row.insertCell(1);
                var type = row.insertCell(2);
                var description = row.insertCell(3);

                id.innerHTML = db_row.r_id;
                amount.innerHTML = db_row.r_amount;
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
            }

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
            console.log('data populateCompleted: ', data);
            var empTable = document.getElementById('emp_completed_body');
            if (empTable.childElementCount === data.length)
                return;
            for (db_row of data) {
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var amount = row.insertCell(1);
                var type = row.insertCell(2);
                var description = row.insertCell(3);

                id.innerHTML = db_row.r_id;
                amount.innerHTML = db_row.r_amount;
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
            }
        });
}

function populateManagerPending() {
    fetch('DisplayAllPendingReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pendingOffset)
        }).then(response => response.json())
        .then(data => {
            console.log('populateManagerPending', data);

            var empTable = document.getElementById('mng_pending_body');

            if (empTable.childElementCount - 1 === data.length)
                return;

            empTable.innerHTML = "";

            for (db_row of data) {
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var empUser = row.insertCell(1)
                var amount = row.insertCell(2);
                var type = row.insertCell(3);
                var description = row.insertCell(4);
                var status = row.insertCell(5);

                id.innerHTML = db_row.r_id;
                empUser.innerHTML = db_row.r_author;
                amount.innerHTML = db_row.r_amount;
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
                status.innerHTML = "<button class=\"btn_sml\" onclick='approveRequest(this)'>Approve</button> <button class=\"btn_sml\" onclick='denyRequest(this)'>Deny</button>"
            }
        });
}

function populateManagerCompleted() {
    fetch('DisplayAllCompletedReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(completedOffset)
        }).then(response => response.json())
        .then(data => {
            console.log("populate manager completed data: ", data);

            var empTable = document.getElementById('mng_completed_body');
            if (empTable.childElementCount === data.length)
                return;

            empTable.innerHTML = "";
            for (db_row of data) {
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var empUser = row.insertCell(1)
                var status = row.insertCell(2);
                var amount = row.insertCell(3);
                var type = row.insertCell(4);
                var description = row.insertCell(5);

                id.innerHTML = db_row.r_id;
                empUser.innerHTML = db_row.r_author;
                status.innerHTML = db_row.r_status;
                amount.innerHTML = db_row.r_amount;
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
            }

        });
}

function createRequest() {

    let reimbursementInfo = {
        r_type_id: document.getElementById("r_reason").value,
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

function approveRequest(that) {
    console.log("approve request called");
    let reimb_id = that.parentNode.parentElement.childNodes[0].innerHTML;

    fetch('ApproveReimbursementServlet', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(reimb_id)
    }).then(response => {
        if (response.ok) {
            pendingOffset -= (document.getElementById("mng_pending_body").childElementCount - 1) % 12 === 0 ? 1 : 0
            alert("Application has been approved!");
            populateManagerPending();
        }
    });

    // let data = Array.from(peepee);
    // console.log(data.map(childNodes => childNodes.innerHTML));
    // console.log("mapping peepee: ", peepee.map(childNode => childNode.innerHTML));
}

function denyRequest(that) {
    console.log("deny request called");
    let reimb_id = that.parentNode.parentElement.childNodes[0].innerHTML;

    fetch('DenyReimbursementServlet', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(reimb_id)
    }).then(response => {
        if (response.ok) {
            alert("Application has been denied!");
            populateManagerPending();
        }
    });

    // let data = Array.from(peepee);
    // console.log(data.map(childNodes => childNodes.innerHTML));
    // console.log("mapping peepee: ", peepee.map(childNode => childNode.innerHTML));
}

// function validAmount(elem) {

//     let RegExpr = new RegExp(/^(\d+(\.\d{0,2})?|\.?\d{1,2})$/); //For currency
//     let val = document.getElementById("r_amount").value;

//     if (RegExpr.test(elem.value)) {
//         console.log("regex matches")
//         val = elem.value;
//     } else {
//         console.log("regex no match")
//         elem.value = val;
//     }
// }

function openPage(pageName, elmnt, color) {
    //Hide all elements with class="tabcontent" by default */
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

    console.log('get element by id open page ', document.getElementById(pageName));

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