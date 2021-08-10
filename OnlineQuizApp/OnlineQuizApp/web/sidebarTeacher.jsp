<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon">
            <i class="fas fa-chalkboard-teacher"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Teacher</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href="dashboardteacher.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Learn
    </div>

    <!-- Nav Item - Courses Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCourses"
           aria-expanded="true" aria-controls="collapseCourses">
            <i class="fas fa-fw fa-book"></i>
            <span>Courses</span>
        </a>
        <div id="collapseCourses" class="collapse" aria-labelledby="headingCourses" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Courses:</h6>
                <a class="collapse-item" href="all-course.html">All Courses</a>
                <a class="collapse-item" href="course.jsp">My Courses</a>
                <a class="collapse-item" href="class.jsp">My Classes</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Create:</h6>
                <a class="collapse-item" href="courseAdd.jsp">Create Course</a>
                <a class="collapse-item" href="classAdd.jsp">Create Class</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Quizzes Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseQuizzes"
           aria-expanded="false" aria-controls="collapseQuizzes">
            <i class="fas fa-fw fa-edit"></i>
            <span>Quizzes</span>
        </a>
        <div id="collapseQuizzes" class="collapse" aria-labelledby="headingQuizzes"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Quizzes:</h6>
                <a class="collapse-item" href="question.jsp">All Questions</a>
                <a class="collapse-item" href="all-quiz.jsp">All Quizzes</a>
                <a class="collapse-item" href="quiz.jsp">My Quizzes</a>

                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Create:</h6>
                <a class="collapse-item" href="quizAdd.jsp">Create Quiz</a>
                <a class="collapse-item" href="questionAdd.jsp">Create Question</a>
                <a class="collapse-item" href="QuizCreateController?check=quiz2">Add Question to Quiz</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Students -->
    <li class="nav-item">
        <a class="nav-link" href="#">
            <i class="fas fa-fw fa-user-graduate"></i>
            <span>Students</span></a>
    </li>

    <!-- Nav Item - Marks -->
    <li class="nav-item">
        <a class="nav-link" href="score.jsp">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Marks</span></a>
    </li>

    <!-- Nav Item - Category Collapse Menu -->

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCategories"
           aria-expanded="false" aria-controls="collapseCategories">
            <i class="fas fa-fw fa-list"></i>
            <span>Categories</span>
        </a>
        <div id="collapseCategories" class="collapse" aria-labelledby="headingQuizzes"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Categories:</h6>
                <a class="collapse-item" href="all-category.jsp">All Categories</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Create:</h6>
                <a class="collapse-item" href="categoryAdd.jsp">Create Quiz Category</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Community
    </div>

    <!-- Nav Item - Blogs Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBlogs"
           aria-expanded="false" aria-controls="collapseBlogs">
            <i class="fas fa-fw fa-users"></i>
            <span>Blogs</span>
        </a>
        <div id="collapseBlogs" class="collapse" aria-labelledby="headingBlogs"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Blogs:</h6>
                <a class="collapse-item" href="all-blog.html">All Blogs</a>
                <a class="collapse-item" href="blog.jsp">My Blogs</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Create:</h6>
                <a class="collapse-item" href="create-blog.jsp">Create Blog</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="#">
            <i class="fas fa-fw fa-trophy"></i>
            <span>Ranking</span></a>
    </li>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="tables.html">
            <i class="fas fa-fw fa-table"></i>
            <span>Tables</span></a>
    </li>


    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Others
    </div>

    <!-- Nav Item - Billing -->
    <li class="nav-item">
        <a class="nav-link" href="billing.html">
            <i class="fas fa-fw fa-receipt"></i>
            <span>Billing</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->