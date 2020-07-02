// this is first comment



(function() {

    /**
     * Variables
     */
    var user_id = '';
    var user_fullname = '';
    var lng = -118.22;
    var lat = 34.05;
    var planIId='';

    var listHeadings = $('list-headings');
    var userReserveHeadings = $('user-reservations-headings');
    var userItinerariesHeadings = $('user-itineraries-headings');
    var userAvailableRoomsHeadings = $('user-available-rooms');
    var managerAllUsersHeadings = $('manager-all-users');
    var managerAllItinerariesHeadings = $('manager-all-itineraries');
    var managerAllPOIHeadings = $('manager-all-pois');
    var managerAllRoomHeadings = $('manager-all-rooms');
    var managerInfoHeadings = $('manager-info');

    var sortButtons = $('sort-buttons');
    var userBtns = $('user-sort');
    var managerBtns = $('manager-sort');
    var sortAscItiBtn = $('sort-i-by-size-asc');
    var sortAscBtnU = $('sort-by-price-asc-u');
    var sortAscBtnM = $('sort-by-price-asc-m');
    var addPlanAction = $('add-plan');
    var createPlanAction = $('createPlan');
    var newPasswordForm = $('new-password-form');
    var loginForm = $('login-form');
    var registerForm = $('register-form');

    /**
     * Initialize
     */
    function init() {

        hideElement(newPasswordForm);
        hideElement(createPlanAction);
        hideElement(sortButtons);
        hideElement(listHeadings);
        hideElement(registerForm);
        hideElement(addPlanAction);

        // Register event listeners
        $('login-btn').addEventListener('click', login);
        $('link-register-btn').addEventListener('click', linkRegister);
        $('change-password').addEventListener('click', showNewPasswordForm);
        $('link-back-to-login-btn').addEventListener('click', showLoginForm);
        $('link-back-to-login-btn2').addEventListener('click', showLoginForm);
        $('new-password-save').addEventListener('click', changePassword);
        $('register-btn').addEventListener('click', register);
        $('plan-btn').addEventListener('click',createPlan);
        createPlanAction.addEventListener('click',createPlanLink);

        $('user-m').addEventListener('click', loadAllUsers);
        $('plan-m').addEventListener('click',loadAllPlans);
        $('poi-m').addEventListener('click',loadAllPOIs);
        $('room-m').addEventListener('click', loadAllROOMs);
        $('manager-m').addEventListener('click', loadAllMANAGERs);

        // user functions
        $('user-rooms').addEventListener('click', loadAvailableRooms);
        $('user-reservations').addEventListener('click', loadUserReservations);
        $('user-itineraries').addEventListener('click', loadUserItineraries);

        validateSession();
    }

    /**
     * Session
     */
    function validateSession() {
        // The request parameters
        var url = './login';
        var req = JSON.stringify({});

        // display loading message
        // showLoadingMessage('Validating session...');

        // make AJAX call
        ajax('GET', url, req,
            // session is still valid
            function(res) {
                var result = JSON.parse(res);

                if (result.status === 'OK') {
                    onSessionValid(result);
                }
            });
    }

    function onSessionValid(result) {
        user_id = result.user_id;
        user_fullname = result.name;

        var loginForm = $('login-form');
        var itemNav = $('item-nav');
        var itemList = $('item-list');
        var welcomeMsg = $('welcome-msg');
        var logoutBtn = $('logout-link');

        var userList = $('user-m');
        var planList = $('plan-m');
        var poi = $('poi-m');
        var rooms = $('room-m');
        var minfo = $('manager-m');

        var userRoom = $('user-rooms');
        var reservation = $('user-reservations');
        var userIt = $('user-itineraries');


        if(user_id == 'manager'){

            hideElement(addPlanAction);
            hideElement(loginForm);
            hideElement(sortButtons);

            showElement(itemNav);
            showElement(itemList);
            showElement(welcomeMsg);

            showElement(logoutBtn, 'inline-block');
            showElement(userList);
            showElement(planList);
            showElement(poi);
            showElement(rooms);
            showElement(minfo);

            hideElement(userRoom);
            hideElement(reservation);
            hideElement(userIt);

            if (window.location.href.indexOf('#all-itineraries') !== -1) {
                loadAllPlans();
            }

        } else {
            // activeBtn(userRoom);
            hideElement(addPlanAction);
            hideElement(loginForm);
            showElement(logoutBtn, 'inline-block');
            hideElement(sortButtons);
            showElement(itemNav);
            showElement(itemList);
            showElement(welcomeMsg);


            hideElement(userList);
            hideElement(planList);
            hideElement(poi);
            hideElement(rooms);
            hideElement(minfo);

            showElement(userRoom);
            showElement(reservation);
            showElement(userIt);

            if (window.location.href.indexOf('#your-reservations') !== -1) {
                loadUserReservations();
            }
        }





    }

    function onSessionInvalid() {
        var itemNav = $('item-nav');
        var itemList = $('item-list');
        var welcomeMsg = $('welcome-msg');
        var logoutBtn = $('logout-link');

        hideElement(itemNav);
        hideElement(sortButtons);
        hideElement(itemList);
        hideElement(logoutBtn);
        hideElement(welcomeMsg);
        hideElement(listHeadings);

        hideElement(registerForm);
        showElement(loginForm);
        hideElement(newPasswordForm);
    }

    // -----------------------------------
    // Login
    // -----------------------------------


    function login() {
        var username = $('username').value;
        var password = $('password').value;

        // The request parameters
        var url = './login';
        var params = 'user_id=' + username + '&password=' + password;
        var req = JSON.stringify({});

        ajax('POST', url + '?' + params, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);

                // successfully logged in
                if (result.status === 'OK') {
                    onSessionValid(result);
                }
            },
            // error
            function() {
                showLoginError();
            });
    }

    function showLoginError() {
        $('login-error').innerHTML = 'Invalid username or password. Please try again.';
    }
    function clearLoginError() {
        $('login-error').innerHTML = '';
    }
    // -----------------------------------
    // Link to register
    // -----------------------------------
    function linkRegister() {
        hideElement(loginForm);
        hideElement(newPasswordForm);
        showElement(registerForm);
    }

    // -----------------------------------
    // Register
    // -----------------------------------
    function register() {
        var username = $('reg_username').value;
        var password = $('reg_password').value;
        var firstname = $('reg_firstname').value;
        var lastname = $('reg_lastname').value;

        // The request parameters
        var url = './register';
        var params = 'user_id=' + username + '&password=' + password
            + '&first_name=' + firstname + '&last_name=' + lastname;
        var req = JSON.stringify({});

        ajax('POST', url + '?' + params, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);

                // successfully registered
                if (result.result === 'SUCCESS') {
                    hideElement(registerForm);
                    showElement(loginForm);
                }
            },
            // error
            function() {
                showRegisterError('The username has been registered. Please use another one!');
            });
    }
    function showRegisterError(innerText) {
        $('register-error').innerHTML = innerText;
    }
    function clearRegisterError() {
        $('register-error').innerHTML = '';
    }

    function showNewPasswordForm() {
        // alert('changing password!');


        hideElement(registerForm);
        hideElement(loginForm);
        showElement(newPasswordForm);

    }

    function showLoginForm() {
        console.log("show loin form")

        showElement(loginForm);
        hideElement(newPasswordForm);
        hideElement(registerForm);

    }

    function changePassword() {

        var username = $('exist_username').value;
        var newPassword = $('new_password').value;
        var oldPassword = $('old_password').value;

        // The request parameters
        var url = './changePassword';
        var params = 'user_id=' + username + '&new_password=' + newPassword + '&old_password=' + oldPassword;
        var req = JSON.stringify({});

        ajax('POST', url + '?' + params, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);

                // successfully registered
                if (result.result === 'SUCCESS') {
                    hideElement(newPasswordForm)
                    hideElement(registerForm);
                    showElement(loginForm);
                }
            },
            // error
            function() {
                showNewPasswordError("The username or old password is invalid"); //??? bug: the error message is not shown if we directly use $('new-password-error').innerHTML = innerText;
            });
    }

    function showNewPasswordError(innerText) {
        $('new-password-error').innerHTML = innerText;
    }

    function createPlan() {

        // var addPlanAction = $('add-plan');
        var planId = $('PlanID').value;
        var planCategory = $('PlanType').value;
        var planSize = $('PlanSize').value;
        var planPoi = $('PlanContent').value;

        // The request parameters
        var url = './managerAddPlan';
        var params = 'plan_id=' + planId + '&plan_size=' + planSize
            + '&plan_category=' + planCategory + '&plan_Poi=' + planPoi;
        var req = JSON.stringify({});

        ajax('POST', url + '?' + params, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);

                // successfully registered
                if (result.result === 'SUCCESS') {
                    hideElement(addPlanAction);
                }
            },
            // error
            function() {
                showRegisterError();
            });
    }




    // -----------------------------------
    // Helper Functions
    // -----------------------------------
    /**
     * A helper function that makes a navigation button active
     *
     * @param btnId -
     *            The id of the navigation button
     */
    function activeBtn(btnId) {
        var btns = document.getElementsByClassName('main-nav-btn');

        // deactivate all navigation buttons
        for (var i = 0; i < btns.length; i++) {
            btns[i].className = btns[i].className.replace(/\bactive\b/, '');
        }

        // active the one that has id = btnId
        var btn = $(btnId);
        btn.className += ' active';
    }
    function showLoadingMessage(msg) {
        var itemList = $('item-list');
        itemList.innerHTML = '<p class="notice"><i class="fa fa-spinner fa-spin"></i> '
            + msg + '</p>';
    }
    function showWarningMessage(msg) {
        var itemList = $('item-list');
        itemList.innerHTML = '<p class="notice"><i class="fa fa-exclamation-triangle"></i> '
            + msg + '</p>';
    }
    function showErrorMessage(msg) {
        var itemList = $('item-list');
        itemList.innerHTML = '<p class="notice"><i class="fa fa-exclamation-circle"></i> '
            + msg + '</p>';
    }
    /**
     * A helper function that creates a DOM element <tag options...>
     *
     * @param tag
     * @param options
     * @returns
     */
    function $(tag, options) {
        if (!options) {
            return document.getElementById(tag);
        }

        var element = document.createElement(tag);

        for ( var option in options) {
            if (options.hasOwnProperty(option)) {
                element[option] = options[option];
            }
        }

        return element;
    }

    function hideElement(element) {
        element.style.display = 'none';
    }

    function showElement(element, style) {
        var displayStyle = style ? style : 'block';
        element.style.display = displayStyle;
    }
    /**
     * AJAX helper
     *
     * @param method -
     *            GET|POST|PUT|DELETE
     * @param url -
     *            API end point
     * @param callback -
     *            This the successful callback
     * @param errorHandler -
     *            This is the failed callback
     */
    function ajax(method, url, data, callback, errorHandler) {
        var xhr = new XMLHttpRequest();

        xhr.open(method, url, true);

        xhr.onload = function() {
            switch (xhr.status) {
                case 200:
                    callback(xhr.responseText);
                    break;
                case 403:
                    onSessionInvalid();
                    break;
                case 401:
                    errorHandler();
                    break;
            }
        };

        xhr.onerror = function() {
            console.error("The request couldn't be completed.");
            errorHandler();
        };

        if (data === null) {
            xhr.send();
        } else {
            xhr.setRequestHeader("Content-Type",
                "application/json;charset=utf-8");
            xhr.send(data);
        }
    }



    function loadAllUsers(){
        hideElement(createPlanAction);
        hideElement(addPlanAction);
        hideElement(sortButtons);

        showElement(listHeadings);
        showElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('user-m');
        var url = './managerViewUsers'
        var req = JSON.stringify({});
        showLoadingMessage('Loading all users../.');
        ajax('GET', url, req, function(res) {
            var users = JSON.parse(res);
            if (!users || users.length === 0) {
                showWarningMessage('No stored users.');
            } else {
                listUsers(users);
            }
        }, function() {
            showErrorMessage('Cannot load users.');
        });

    }

    function createPlanLink(){
        hideElement(createPlanAction);
        showElement(addPlanAction);
    }

    function loadAllPlans(){
        hideElement(sortButtons);

        showElement(createPlanAction);
        hideElement(addPlanAction);

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        showElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);


        activeBtn('plan-m');
        var url = './managerViewPlan';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all Plan../.');
        ajax('GET', url, req, function(res) {
            var plans = JSON.parse(res);
            if (!plans || plans.length === 0) {
                showWarningMessage('No stored itineraries.');
            } else {
                listPlans(plans);
            }
        }, function() {
            showErrorMessage('Cannot load itineraries.');
        });

    }

    function loadAllPOIs(){
        hideElement(createPlanAction);
        hideElement(addPlanAction);
        hideElement(sortButtons);

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        showElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('poi-m');
        var url = './managerViewPIs';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all pois...');
        ajax('GET', url, req, function(res) {
            var pois = JSON.parse(res);
            if (!pois || pois.length === 0) {
                showWarningMessage('No stored points of interest.');
            } else {
                listPOIs(pois);
            }
        }, function() {
            showErrorMessage('Cannot load points of interest.');
        });

    }


    function loadNumBookeRoom(){
        // hideElement(sortButtons)
        var num = 0;
        // activeBtn('user-m');
        var url = './managerViewTotalNumOfBookedRoom'
        var req = JSON.stringify({});
        ajax('GET', url, req, function(res) {
            var re = JSON.parse(res);
            if (!re) {
                showWarningMessage('No stored rooms.');
            } else {
                num = re;
            }
        }, function() {
            showErrorMessage('Cannot load users.');
        });
        return num;
    }

    function loadAllROOMs(){
        hideElement(createPlanAction);
        hideElement(addPlanAction);
        // var sortRooms = $('sort-rooms');
        showElement(sortButtons);
        // var userBtns = $('user-sort');
        hideElement(userBtns);
        showElement(managerBtns);

        loadRoomSortingButtons();

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        showElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('room-m');
        var url = './managerViewROOMs';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all rooms../.');
        ajax('GET', url, req, function(res) {
            var rooms = JSON.parse(res);
            if (!rooms || rooms.length === 0) {
                console.log(rooms)
                showWarningMessage('No stored rooms.');
            } else {
                listROOMs(rooms);
            }
        }, function() {
            showErrorMessage('Cannot load rooms.');
        });

    }

    function loadAllMANAGERs(){
        hideElement(createPlanAction);
        hideElement(addPlanAction);
        hideElement(sortButtons);

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        showElement(managerInfoHeadings);

        activeBtn('manager-m');
        var url = './managerViewMANAGERs';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all managerinformation../.');
        ajax('GET', url, req, function(res) {
            var managers = JSON.parse(res);
            if (!managers || managers.length === 0) {
                showWarningMessage('No stored managers.');
            } else {
                listMANAGERs(managers);
            }
        }, function() {
            showErrorMessage('Cannot load managers.');
        });

    }







    /**
     * List users
     *
     * @param items -
     *            An array of item JSON objects
     */
    function listUsers(users) {
        var userList = $('item-list');
        userList.innerHTML = '';

        for (var i = 0; i < users.length; i++) {
            addUser(userList,users[i]);
        }
    }

    function listPlans(plans) {
        var planList = $('item-list');
        planList.innerHTML = '';

        for (var i = 0; i < plans.length; i++) {
            addPlan(planList,plans[i]);
        }
    }



    function listPOIs(pois) {
        var poiList = $('item-list');
        poiList.innerHTML = '';

        for (var i = 0; i < pois.length; i++) {
            addPoi(poiList,pois[i]);
        }
    }


    function listROOMs(rooms) {
        var roomList = $('item-list');
        roomList.innerHTML = '';

        var roomnum = document.createElement("span");

        // roomnum.innerHTML = loadNumBookeRoom();


        // roomList.appendChild(roomnum);

        for (var i = 0; i < rooms.length; i++) {
            addRoom(roomList,rooms[i]);
        }
    }


    function listMANAGERs(managers) {
        var managerList = $('item-list');
        managerList.innerHTML = '';

        for (var i = 0; i < managers.length; i++) {
            addManager(managerList,managers[i]);
        }
    }



    function addUser(userList, user) {
        var userId = user.CustomerID;
        var userName = user.FullName;
        // create the <li> tag and specify the id and class attributes
        var li = $('li', {
            id : 'user-' + userId,
            className : 'item'
        });
        // set the data attribute
        li.dataset.userId = userId;
        // section


        var section = $('div', {});
        var title = $('a', {
            target : '_blank',
            className : 'item-name'
        });
        title.innerHTML = userName;
        section.appendChild(title);
        li.appendChild(section);

        var bookAllRooms = $('span',{});
        if (user.hasBookAllPlans == "true") {
            var bookedAllIcon = $('i', {className: 'fas fa-bookmark'});
            bookAllRooms.appendChild(bookedAllIcon);
        }
        // bookAllRooms.innerHTML = user.hasBookAllPlans;
        li.appendChild(bookAllRooms);

        var address = $("span",{});
        address.innerHTML = user.Address;
        li.appendChild(address);

        var phone = document.createElement("span");
        phone.innerHTML = user.PhoneNumber;
        li.appendChild(phone);

        var iconSpan = document.createElement("span");
        if (user.Points != null){
            var icon = $('i',{className: 'fas fa-star'});
            var points = document.createElement("span");
            points.style = "font-family: Open Sans, sans-serif";
            points.innerHTML = " " + user.Points;
            icon.appendChild(points);
            iconSpan.appendChild(icon);
        }

        li.appendChild(iconSpan);

        userList.appendChild(li);
    }

    function deletePlan(planIId){
        var url = './managerViewPlan'+'?plan_id='+planIId;
        var req = JSON.stringify({
            plan_id : planIId
        });
        ajax('DELETE', url, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);
                if (result.result === 'SUCCESS') {
                    alert("Plan Deleted!");
                    window.location = window.location.href + "#all-itineraries";
                    window.location.reload();
                    // result.status === 'OK'
                }
            });
    }


    function addPlan(planList, plan) {

        planIId = plan.ItineraryID;
        var itineraryID = plan.ItineraryID;
        var planC = plan.Category;
        var planP = plan.PartySize;
        var pis = plan.pis;

        // alert(JSON.stringify(pis));

        var li = $('li', {
            id : planIId,
            className : 'item'
        });
        // set the data attribute
        li.dataset.planId = planIId;
        // section

        var planId = document.createElement("a");
        planId.style.flex = "1";
        planId.innerHTML = 'ID '+planIId;
        li.appendChild(planId);

        var planCategory = document.createElement("span");
        planCategory.style.flex = "1";
        planCategory.innerHTML = planC;
        li.appendChild(planCategory);

        var planPartySize = document.createElement("span");
        planPartySize.style.flex = "1";
        planPartySize.innerHTML = planP+' people';
        li.appendChild(planPartySize);

        var pointOfI = document.createElement("span");
        pointOfI.style.flex = "1";

        for (var i = 0; i < pis.length; i++) {
            // alert(JSON.stringify(pis[i]));
            pointOfI.innerHTML = pis[i].pName;
            li.appendChild(pointOfI);
        }

        var numOfBookedPeople = document.createElement("span");
        numOfBookedPeople.style.flex = "1";
        numOfBookedPeople.innerHTML = plan.NumOfBookedUser + ' groups';
        li.appendChild(numOfBookedPeople);

        var deleteIcon = document.createElement("i");
        deleteIcon.className = 'fas fa-times-circle';
        deleteIcon.id = planIId;
        var deleteBtn = document.createElement("a");
        deleteBtn.id = "delete-hover";
        deleteBtn.appendChild(deleteIcon);

        deleteBtn.onclick =function(){
            deletePlan(itineraryID);
        };

        var deleteDiv = document.createElement("span");
        deleteDiv.style.flex = "1";
        deleteDiv.style.textAlign = "center";
        deleteDiv.appendChild(deleteBtn);

        li.appendChild(deleteDiv);

        planList.appendChild(li);

    }


    function addPoi(poiList, poi) {
        var poiId = poi.PID;
        var poiName = poi.pName;
        // create the <li> tag and specify the id and class attributes
        var li = $('li', {
            id : 'poi-' + poiId,
            className : 'item'
        });
        // set the data attribute
        li.dataset.poiId = poiId;
        // section

        var pName = document.createElement("span");
        pName.innerHTML = poi.pName;
        li.appendChild(pName);


        var pDuration = document.createElement("span");
        pDuration.innerHTML = poi.pDuration + " hours";
        li.appendChild(pDuration);

        var pPrice= document.createElement("span");;
        pPrice.innerHTML = "$" + poi.pPrice;
        li.appendChild(pPrice);

        poiList.appendChild(li);
    }

    function addRoom(roomList, room) {
        var id = room.roomId;
        var li = $('li', {
            id : 'room-' + id,
            className : 'item'
        });
        li.dataset.roomId = id;
        // section

        var roomId = document.createElement("span");
        roomId.style.flex = "1";
        roomId.innerHTML = room.roomId;
        li.appendChild(roomId);

        var roomType = document.createElement("span");
        roomType.style.flex = "1";
        roomType.innerHTML = room.roomType;
        li.appendChild(roomType);

        var price = document.createElement("span");
        price.style.flex = "1";
        price.innerHTML = "$" + room.Price.toString();
        li.appendChild(price);

        var status = document.createElement("span");
        status.style.flex = "1";
        status.innerHTML = room.CustomerID;
        li.appendChild(status);

        if (room.StaffID != "Service Staff ID: null") {
            var StaffID = document.createElement("span");
            StaffID.style.flex = "1";
            StaffID.innerHTML = room.StaffID;
            li.appendChild(StaffID);
        }

        var bookRoomButton = document.createElement('button');
        bookRoomButton.type = "button";
        bookRoomButton.className = "btn btn-warning btn-sm";
        bookRoomButton.innerHTML = "Book Room";
        bookRoomButton.onclick = function () {
            showRoomBookingForm(this, room.roomId);
            // createRoomBooking('1', room.roomId);
            // alert("ROOM BOOKED: " + room.roomId);
        };

        var bookRoomDiv = document.createElement("div");
        bookRoomDiv.style.flex = "1";
        bookRoomDiv.appendChild(bookRoomButton);

        if (room.CustomerID == "Available" && user_id != 'manager') {
            li.appendChild(bookRoomDiv);
        }

        console.log("Room #" + room.roomId + " at " + room.Price.toString());

        roomList.appendChild(li);
    }

    function showRoomBookingForm(node, roomID) {
        // alert("This node is: " + node.innerHTML);
        // alert("Room #" + roomID);

        var mainForm = document.createElement("form");
        // mainForm.style.width = "10%";

        var checkInInput = document.createElement("input");
        checkInInput.className = "form-control";
        checkInInput.type = "text";
        checkInInput.id = "checkInDate";

        var checkInLabel = document.createElement("label");
        checkInLabel.className = "login-labels";
        checkInLabel.innerHTML = "Check-in Date (YYYY-MM-DD)";

        var checkOutInput = document.createElement("input");
        checkOutInput.className = "form-control";
        checkOutInput.type = "text";
        checkOutInput.id = "checkOutDate";

        var checkOutLabel = document.createElement("label");
        checkOutLabel.className = "login-labels";
        checkOutLabel.innerHTML = "Check-in Date (YYYY-MM-DD)";

        var bookRoomButton = document.createElement('button');
        bookRoomButton.id = "book-button";
        bookRoomButton.className = "btn btn-success btn-sm";
        bookRoomButton.innerHTML = "Book Room";
        bookRoomButton.onclick = function () {
            var checkInDate = checkInInput.value;
            var checkOutDate = checkOutInput.value;
            // alert(checkInDate + " to " + checkOutDate);
            createRoomBooking('1', roomID, checkInDate, checkOutDate);
        };

        mainForm.appendChild(checkInLabel)
        mainForm.appendChild(checkInInput);
        mainForm.appendChild(checkOutLabel);
        mainForm.appendChild(checkOutInput);
        mainForm.appendChild(bookRoomButton);

        node.parentNode.replaceChild(mainForm, node);
        // node.parentNode.replaceChild(mainForm, node);
    }

    function createRoomBooking(userID, roomNumber, checkInDate, checkOutDate) {
        // console.log(checkInDate + " to " + checkOutDate);

        var url = './userAddReservation';
        var params = 'user_id=' + userID + '&room_id=' + roomNumber + '&checkin_date=' + checkInDate + '&checkout_date=' + checkOutDate;
        var req = JSON.stringify({});

        ajax('POST', url + '?' + params, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);

                // successfully loaded
                if (result.result === 'SUCCESS') {
                    alert("Room Booked!");
                    window.location = window.location.href + "#your-reservations";
                    window.location.reload();
                }
            },
            // error
            function() {
                showRegisterError();
            });
    }

    function addManager(managerList, manager) {
        var staffId = manager.staffId;
        //var poiName = room.pName;
        // create the <li> tag and specify the id and class attributes
        var li = $('li', {
            id : 'manager-' + staffId,
            className : 'item'
        });
        // set the data attribute
        li.dataset.staffId = staffId;
        // section

        var staffName = document.createElement("span");
        staffName.innerHTML = manager.staffName;
        li.appendChild(staffName);


        // var staffPassword = document.createElement("p");
        // staffPassword.innerHTML = manager.staffPassword;
        // li.appendChild(staffPassword);

        var staffPhone= document.createElement("span");
        staffPhone.innerHTML = manager.staffPhone;
        li.appendChild(staffPhone);

        managerList.appendChild(li);
    }

    function loadAvailableRooms(){
        showElement(sortButtons);
        hideElement(managerBtns);
        showElement(userBtns);
        hideElement(sortAscItiBtn);
        showElement(sortAscBtnU);

        loadRoomSortingButtons();

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        showElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('user-rooms');
        var url = './userViewROOMs';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all rooms../.');
        ajax('GET', url, req, function(res) {
            var rooms = JSON.parse(res);
            if (!rooms || rooms.length === 0) {
                showWarningMessage('No stored rooms.');
            } else {
                listROOMs(rooms);
            }
        }, function() {
            showErrorMessage('Cannot load rooms.');
        });

    }

    function loadRoomSortingButtons() {
        sortAscBtnU.onclick = function () {
            sortByPriceAscU();
        };

        sortAscBtnM.onclick = function () {
            sortByPriceAscM();
            console.log("doing the sorting!");
        };
    }

    function loadItinerarySortingButtons() {
        sortAscItiBtn.onclick = function () {
            console.log("doing itinerary sorting!");
            sortItiBySizeU();
        };
    }

    function sortItiBySizeU() {
        var url = './userViewItinerariesBySize';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all itineraries...');
        ajax('GET', url, req, function(res) {
            var plan = JSON.parse(res);
            if (!plan || plan.length === 0) {
                showWarningMessage('No stored itineraries.');
            } else {
                listPlansUser(plan);
                console.log("Loaded things!");
            }
        }, function() {
            showErrorMessage('Cannot load itineraries.');
        });
    }

    function sortByPriceAscM() {
        var url = './managerViewRoomsPriceAsc';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all rooms../.');
        ajax('GET', url, req, function(res) {
            var rooms = JSON.parse(res);
            if (!rooms || rooms.length === 0) {
                showWarningMessage('No stored rooms.');
            } else {
                listROOMs(rooms);
            }
        }, function() {
            showErrorMessage('Cannot load rooms.');
        });
    }

    function sortByPriceAscU() {
        var url = './userViewRoomsPriceAsc';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all rooms../.');
        ajax('GET', url, req, function(res) {
            var rooms = JSON.parse(res);
            if (!rooms || rooms.length === 0) {
                showWarningMessage('No stored rooms.');
            } else {
                listROOMs(rooms);
                // alert("Got new room list");
            }
        }, function() {
            showErrorMessage('Cannot load rooms.');
        });
    }

    function loadUserReservations() {
        var userID = user_id;
        hideElement(sortButtons);

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        showElement(userReserveHeadings);
        hideElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('user-reservations');

        var url = './userViewReservations';
        var params = 'user_id=' + userID;
        var req = JSON.stringify({});

        showLoadingMessage('Loading all user reservations../.');
        ajax('GET', url + '?' + params, req, function(res) {
            var reservations = JSON.parse(res);
            if (!reservations || reservations.length === 0) {
                showWarningMessage('No stored reservations.');
            } else {
                listReservations(reservations);
            }
        }, function() {
            showErrorMessage('Cannot load reservations.');
        });
    }

    function listReservations(reservations) {
        var reservationList = $('item-list');
        reservationList.innerHTML = '';

        for (var i = 0; i < reservations.length; i++) {
            addReservation(reservationList,reservations[i]);
        }
    }

    function addReservation(reservationList, reservation) {
        var id = reservation.roomNo;
        var li = $('li', {
            id : 'reservation-' + id,
            className : 'item'
        });
        // set the data attribute
        li.dataset.roomNo = id;
        // section

        var roomNo = document.createElement("span");
        roomNo.style.flex = "1";
        roomNo.innerHTML = reservation.roomNo;
        li.appendChild(roomNo);

        var roomType = document.createElement("span");
        roomType.style.flex = "1";
        roomType.innerHTML = reservation.roomType;
        li.appendChild(roomType);

        var price = document.createElement("span");
        price.style.flex = "1";
        price.innerHTML = "$" + reservation.roomPrice.toString();
        li.appendChild(price);

        var checkInDate = document.createElement("span");
        checkInDate.style.flex = "1";
        checkInDate.innerHTML = reservation.checkInDate;
        li.appendChild(checkInDate);

        var checkOutDate = document.createElement("span");
        checkOutDate.style.flex = "1";
        checkOutDate.innerHTML = reservation.checkOutDate;
        li.appendChild(checkOutDate);

        reservationList.appendChild(li);
    }

    function loadUserItineraries(){
        showElement(sortButtons);
        hideElement(managerBtns);
        showElement(userBtns);
        hideElement(sortAscBtnU);
        showElement(sortAscItiBtn);

        loadItinerarySortingButtons();

        showElement(listHeadings);
        hideElement(managerAllUsersHeadings);
        hideElement(userReserveHeadings);
        showElement(userItinerariesHeadings);
        hideElement(userAvailableRoomsHeadings);
        hideElement(managerAllItinerariesHeadings);
        hideElement(managerAllPOIHeadings);
        hideElement(managerAllRoomHeadings);
        hideElement(managerInfoHeadings);

        activeBtn('user-itineraries');

        var url = './userViewItineraries';
        var req = JSON.stringify({});
        showLoadingMessage('Loading all itineraries ...');
        ajax('GET', url, req, function(res) {
            var plans = JSON.parse(res);
            if (!plans || plans.length === 0) {
                showWarningMessage('No stored itineraries.');
            } else {
                listPlansUser(plans);
            }
        }, function() {
            showErrorMessage('Cannot load itineraries.');
        });

    }

    function listPlansUser(plans) {
        var planList = $('item-list');
        planList.innerHTML = '';
        for (var i = 0; i < plans.length; i++) {
            addPlanUser(planList,plans[i]);
        }
    }

    function addPlanUser(planList, plan) {
        planIId = plan.ItineraryID;
        var planC = plan.Category;
        var planP = plan.PartySize;
        var pis = plan.pis;

        var li = $('li', {
            id : planIId,
            className : 'item'
        });
        // set the data attribute
        li.dataset.planId = planIId;
        // section

        var planId = document.createElement("span");
        planId.style.flex = "1";
        planId.innerHTML = 'ID '+planIId;
        li.appendChild(planId);

        var planCategory = document.createElement("span");
        planCategory.style.flex = "1";
        planCategory.innerHTML = planC;
        li.appendChild(planCategory);

        var planPartySize = document.createElement("span");
        planPartySize.style.flex = "1";
        planPartySize.innerHTML = planP + ' people';
        li.appendChild(planPartySize);

        var pointOfI = document.createElement("span");
        pointOfI.style.flex = "1";

        for (var i = 0; i < pis.length; i++) {
            // alert(JSON.stringify(pis[i]));
            pointOfI.innerHTML = pis[i].pName;
            li.appendChild(pointOfI);
        }

        planList.appendChild(li);

    }


    init();

})();

// END