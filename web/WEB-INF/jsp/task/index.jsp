<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="../body-open.jsp" />

    <a href="addTask" class="btn btn-primary">
        <i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;New task
    </a>

    <br /> <br />

    <table class="table table-bordered table-hover table-responsive table-striped">
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Description</th>
                <th>Status</th>
                <th>Date of achievement</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="task" items="${tasks}">

                <tr>
                    <td>
                        <c:if test="${task.achieved eq false}">
                            <button type="button" 
                                    id="task-${task.id}" 
                                    class="btn btn-xs btn-success my-task" 
                                    value="${task.id}"
                                    data-toggle="tooltip" 
                                    title="Complete task" 
                                    data-placement="bottom">
                                <i class="fa fa-check" aria-hidden="true"></i>
                            </button>
                        </c:if>
                        
                        <a href="editTask?id=${task.id}" class="btn btn-xs btn-primary" 
                           data-toggle="tooltip" title="Edit" data-placement="bottom">
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                        </a>
                        <a href="deleteTask/${task.id}" class="btn btn-xs btn-danger" 
                           data-toggle="tooltip" title="Delete" data-placement="bottom">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </a>
                    </td>
                    
                    <td>${task.id}</td>
                    <td>${task.description}</td>
                    <td id="status-of-task-${task.id}">${task.achieved ? "Complete" : "To be done"}</td>
                    <td id="date-of-achievement-for-task-${task.id}">
                        <c:if test="${not empty task.dateOfAchievement}">
                            <fmt:formatDate value="${task.dateOfAchievement.time}" pattern="dd/MM/yyyy" />
                        </c:if>
                    </td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
            
<c:import url="../body-close-tasks.jsp" />
