var pendingOffset = 0;
var completedOffset = 0;
var pendingEmpOffset = 0;
var completedEmpOffset = 0;

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

function updateSignout() {
    fetch('ReturnUserDataServlet', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => response.json())
        .then(data => {
            if (data == null) {
                window.location.href = "index.html"
            }
            console.log("data: ", data);
            document.getElementById("cardUsername").innerHTML = data.username;
            document.getElementById("cardName").innerHTML = data.first_name + " " + data.last_name;
            document.getElementById("cardEmail").innerHTML = data.email;

            document.getElementById("updatedFirstName").value = data.first_name;
            document.getElementById("updatedLastName").value = data.last_name;
            document.getElementById("updatedEmail").value = data.email;
        });


}

function populatePending() {
    // let type = { type: "PENDING" }
    fetch('DisplayPendingReimb', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pendingEmpOffset)
        }).then(response => response.json())
        .then(data => {
            if (pendingEmpOffset <= 0)
                document.getElementById("pendEmployeePrevBtn").className = 'btn_sml hideButton';
            else
                document.getElementById("pendEmployeePrevBtn").className = 'btn_sml showButton';

            if (data.length <= 12) {
                document.getElementById("pendEmployeeNextBtn").className = 'btn_sml hideButton';
            } else {
                document.getElementById("pendEmployeeNextBtn").className = 'btn_sml showButton';
            }

            var empTable = document.getElementById('emp_pending_body');

            if (empTable.childElementCount === data.length)
                return;

            empTable.innerHTML = "";
            var tableLength = data.length <= 12 ? data.length : 12;
            for (let i = 0; i < tableLength; i++) {
                let db_row = data[i];
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var amount = row.insertCell(1);
                var type = row.insertCell(2);
                var description = row.insertCell(3);

                id.innerHTML = db_row.r_id;
                amount.innerHTML = formatter.format(db_row.r_amount / 100);
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
            },
            body: JSON.stringify(completedEmpOffset)
        }).then(response => response.json())
        .then(data => {
            if (completedEmpOffset <= 0)
                document.getElementById("compEmployeePrevBtn").className = 'btn_sml hideButton';
            else
                document.getElementById("compEmployeePrevBtn").className = 'btn_sml showButton';

            if (data.length <= 12) {
                document.getElementById("compEmployeeNextBtn").className = 'btn_sml hideButton';
            } else {
                document.getElementById("compEmployeeNextBtn").className = 'btn_sml showButton';
            }

            var empTable = document.getElementById('emp_completed_body');

            if (empTable.childElementCount === data.length)
                return;

            empTable.innerHTML = "";
            var tableLength = data.length <= 12 ? data.length : 12;
            for (let i = 0; i < tableLength; i++) {
                let db_row = data[i];
                // let current = JSON.stringify(db_row);
                var row = empTable.insertRow(-1);
                var id = row.insertCell(0);
                var amount = row.insertCell(1);
                var type = row.insertCell(2);
                var description = row.insertCell(3);
                var status = row.insertCell(4);

                id.innerHTML = db_row.r_id;
                amount.innerHTML = formatter.format(db_row.r_amount / 100);
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
                status.innerHTML = db_row.r_status;
            }
        });
}

function pendingEmployeeNext() {
    pendingEmpOffset++;
    populatePending();
}

function pendingEmployeePrev() {
    if (pendingEmpOffset > 0) {
        pendingEmpOffset--;
    }
    populatePending();
}

function completedEmployeePrev() {
    if (completedEmpOffset > 0) {
        completedEmpOffset--;
    }
    populateCompleted();
}

function completedEmployeeNext() {
    completedEmpOffset++;
    populateCompleted();
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

            if (pendingOffset <= 0)
                document.getElementById("pendManagerPrevBtn").className = 'btn_sml hideButton';
            else
                document.getElementById("pendManagerPrevBtn").className = 'btn_sml showButton';

            if (data.length <= 12) {
                document.getElementById("pendManagerNextBtn").className = 'btn_sml hideButton';
            } else {
                document.getElementById("pendManagerNextBtn").className = 'btn_sml showButton';
            }

            var empTable = document.getElementById('mng_pending_body');

            if (empTable.childElementCount === data.length)
                return;

            empTable.innerHTML = "";
            var tableLength = data.length <= 12 ? data.length : 12;
            for (let i = 0; i < tableLength; i++) {
                let db_row = data[i];
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
                amount.innerHTML = formatter.format(db_row.r_amount / 100);
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
                status.innerHTML = "<button class=\"btn_sml\" onclick='approveRequest(this)'>Approve</button> <button class=\"btn_sml\" onclick='denyRequest(this)'>Deny</button>"
            }
        });
}

function pendingManagerNext() {
    pendingOffset++;
    populateManagerPending();
}

function pendingManagerPrev() {
    if (pendingOffset > 0) {
        pendingOffset--;
    }
    populateManagerPending();
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

            if (completedOffset <= 0)
                document.getElementById("compManagerPrevBtn").className = 'btn_sml hideButton';
            else
                document.getElementById("compManagerPrevBtn").className = 'btn_sml showButton';

            if (data.length <= 12) {
                document.getElementById("compManagerNextBtn").className = 'btn_sml hideButton';
            } else {
                document.getElementById("compManagerNextBtn").className = 'btn_sml showButton';
            }

            var empTable = document.getElementById('mng_completed_body');

            if (empTable.childElementCount === data.length)
                return;

            empTable.innerHTML = "";

            var tableLength = data.length <= 12 ? data.length : 12;
            for (let i = 0; i < tableLength; i++) {
                let db_row = data[i];
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
                amount.innerHTML = formatter.format(db_row.r_amount / 100);
                type.innerHTML = db_row.r_type;
                description.innerHTML = db_row.r_description;
            }

        });
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
}

function completedManagerNext() {
    completedOffset++;
    populateManagerCompleted();
}

function completedManagerPrev() {
    if (completedOffset > 0) {
        completedOffset--;
    }
    populateManagerCompleted();
}

function createRequest() {

    if (!validAmount(document.getElementById("r_amount").value)) {
        alert("Invalid amount")
        return;
    }

    if (!validDescription(document.getElementById("description").value)) {
        alert("Descrption must be under 250 characters.")
        return;
    }


    let reimbursementInfo = {
        r_type_id: document.getElementById("r_reason").value,
        r_amount: document.getElementById("r_amount").value * 100,
        r_description: document.getElementById("description").value
    }

    console.log("was valid amount")

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

function validAmount(amount) {

    let RegExpr = new RegExp(/^(\d+(\.\d{0,2})?|\.?\d{1,2})$/); //For currency

    return (RegExpr.test(amount));
}

function validDescription(desc) {
    return (desc.length < 250);
}

function updateUserInfo() {
    let first = document.getElementById("updatedFirstName").value.trim();
    let last = document.getElementById("updatedLastName").value.trim();
    let email = document.getElementById("updatedEmail").value.trim();

    if (/\s/.test(first)) {
        // It has any kind of whitespace
        alert("First name must not contain spaces")
        return;
    } else if (/\s/.test(last)) {
        // It has any kind of whitespace
        alert("Last name must not contain spaces")
        return;
    }

    if (!validEmail(email)) {
        alert("Invalid Email")
        return;
    }

    console.log(first + " " + last + " " + email);
}

function validFirstName() {
    if (/\s/.test(fi)) {
        // It has any kind of whitespace
    }
}

function validLastName() {

}

function validEmail(email) {
    let RegExpr = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    return (RegExpr.test(email));
}

function reimbursementCreated() {
    alert("Reimbursement succesfully created.")
    window.location.href = "Dashboard.html";
}


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
document.getElementById("defaultOpen").click();

function signOut() {
    fetch('EndSessionServlet', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(window.location.href = "index.html")

}

var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',

    // These options are needed to round to whole numbers if that's what you want.
    //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
    //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
});

//ManagerDashboardServlet