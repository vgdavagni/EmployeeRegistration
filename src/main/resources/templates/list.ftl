<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Employee </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.employee.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="fname">First Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.firstName" id="fname" class="employeename form-control input-sm" placeholder="Enter your first name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                 <div class="row">
                    	                    <div class="form-group col-md-12">
                    	                        <label class="col-md-2 control-lable" for="lname">Last Name</label>
                    	                        <div class="col-md-7">
                    	                            <input type="text" ng-model="ctrl.employee.lastName" id="lname" class="employeename form-control input-sm" placeholder="Enter your last name" required ng-minlength="3"/>
                    	                        </div>
                    	                    </div>
                    	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="gender">Gender</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.gender" id="gender" class="form-control input-sm" placeholder="Enter your Gender." required ng-minlength="1"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="department">Department</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.department" id="department" class="form-control input-sm" placeholder="Enter your Department." required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
                    	                    <div class="form-group col-md-12">
                    	                        <label class="col-md-2 control-lable" for="dob">Date Of Birth</label>
                    	                        <div class="col-md-7">
                    	                            <input type="date" ng-model="ctrl.employee.dob" id="dob" class="form-control input-sm" placeholder="Enter Date Of Birth in YYYY-mm-dd format" required ng-maxlength="10"/>
                    	                        </div>
                    	                    </div>
                    	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.employee.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Employees </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>FIRST NAME</th>
		                <th>LAST NAME</th>
		                <th>GENDER</th>
		                <th>DEPARTMENT</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllEmployees()">
		                <td>{{u.id}}</td>
		                <td>{{u.firstName}}</td>
		                <td>{{u.lastName}}</td>
		                <td>{{u.gender}}</td>
		                <td>{{u.department}}</td>
		                <td><button type="button" ng-click="ctrl.editEmployee(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeEmployee(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>